package ua.com.alevel;

import ua.com.alevel.util.ResourcesUtil;

import java.util.Map;

public class DIFrameworkApplication {

    private static Map<String, String> properiesMap;

    public static void start(Class<?> mainClass) {
        properiesMap = ResourcesUtil.getResources(mainClass.getClassLoader());
        DIFrameworkContext context = new DIFrameworkContext(mainClass);
        BeanFactory beanFactory = new BeanFactory(context.getServiceInterfaces(), context.getSearcher().getScanner());
        beanFactory.configure();
        DIFrameworkStarter frameworkStarter = new DIFrameworkStarter();
        frameworkStarter.start();
    }

    public static Map<String, String> getProperiesMap() {
        return properiesMap;
    }
}
