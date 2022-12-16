package core.ctx;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.boot.CommandLineRunner;

import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class PrintBeansRunner implements CommandLineRunner {

    private final ListableBeanFactory factory;
    private final String applicationPackagePrefix;

    public PrintBeansRunner(ListableBeanFactory listableBeanFactory) {
        this(listableBeanFactory, null);
    }

    public PrintBeansRunner(ListableBeanFactory listableBeanFactory, String applicationPackagePrefix) {
        this.factory = listableBeanFactory;
        this.applicationPackagePrefix = applicationPackagePrefix;
    }

    @Override
    public void run(String... args) {
        var allBeansById = factory.getBeansOfType(Object.class);
        if (applicationPackagePrefix == null || applicationPackagePrefix.isBlank()) {
            printBeans(allBeansById, "bean(s)", (obj) -> true);
        } else {
            Predicate<Object> applicationClassPredicate =
                    (bean) -> bean.getClass().getPackageName().startsWith(applicationPackagePrefix);
            printBeans(allBeansById, "Spring bean(s)", Predicate.not(applicationClassPredicate));
            printBeans(allBeansById, "application bean(s)", applicationClassPredicate);
        }
    }

    private void printBeans(Map<String, Object> beansById, String beanOrigin, final Predicate<Object> beanPredicate) {
        var sortedBeanNames = beansById.entrySet().stream()
                .filter(entry -> beanPredicate.test(entry.getValue()))
                .map(entry -> entry.getKey())
                .sorted()
                .collect(Collectors.toList());

        out.printf("The context contains %d %s in total:\n", sortedBeanNames.size(), beanOrigin);
        for (String name : sortedBeanNames) {
            out.printf("  - %s : \u001B[36m%s\u001B[0m\n", name, factory.getType(name).getName());
        }
    }

}
