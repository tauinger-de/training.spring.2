package core.h2;

import org.h2.tools.RunScript;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;
import java.io.InputStreamReader;
import java.sql.SQLException;

/**
 * Starts an H2 database, which accepts remote connections on port specified port (default 9092).
 * Provides support for executing SQL scripts.
 */
public class H2Server implements AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(H2Server.class);

    private Server server;

    private int port = 9092;

    public int getPort() {
        return port;
    }

    public H2Server setPort(int port) {
        this.port = port;
        return this;
    }

    public H2Server start() {
        LOG.debug("Launching H2 TCP Server on port {}", port);
        try {
            server = Server.createTcpServer("-tcp", "-ifNotExists", "-tcpPort", String.valueOf(port)).start();
        } catch (SQLException e) {
            var rootCause = NestedExceptionUtils.getRootCause(e);
            if (rootCause != null && rootCause.getMessage().equals("Address already in use: bind")) {
                LOG.warn("Server seems to be running already (maybe in some other context?)");
            } else {
                throw new IllegalStateException("Failed to launch H2 TCP server", e);
            }
        }
        return this;
    }

    public void executeScript(DataSource dataSource, Resource scriptResource) {
        try {
            LOG.info("Running schema script: {}", scriptResource.getURI());
            RunScript.execute(
                    dataSource.getConnection(),
                    new InputStreamReader(scriptResource.getInputStream()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void stop() {
        if (this.server != null) {
            LOG.debug("Stopping H2 TCP Server");
            this.server.stop();
        }
    }

    @Override
    public void close() {
        stop();
    }
}
