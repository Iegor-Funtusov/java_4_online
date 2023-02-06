package ua.com.alevel;

public class DIFrameworkApplication {

    public static void start(Class<?> mainClass) {
        DIFrameworkContext context = new DIFrameworkContext(mainClass);
        BeanFactory beanFactory = new BeanFactory(context.getServiceInterfaces(), context.getSearcher().getScanner());
        beanFactory.configure();
        DIFrameworkStarter frameworkStarter = new DIFrameworkStarter();
        frameworkStarter.start();
    }
}
