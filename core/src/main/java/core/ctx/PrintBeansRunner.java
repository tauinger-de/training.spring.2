package core.ctx;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.boot.CommandLineRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.System.out;

public class PrintBeansRunner implements CommandLineRunner {

    private final ListableBeanFactory factory;

    public PrintBeansRunner(ListableBeanFactory listableBeanFactory) {
        this.factory = listableBeanFactory;
    }

    @Override
    public void run(String... args) {
        out.printf("The context contains %d bean(s):\n", factory.getBeanDefinitionCount());
        List<String> beanNames = Arrays.asList(factory.getBeanDefinitionNames());
        Collections.sort(beanNames);
        for (String name : beanNames) {
            out.printf("  - %s : \u001B[36m%s\u001B[0m\n", name, factory.getType(name).getName());
        }
    }
}
