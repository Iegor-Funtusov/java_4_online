package ua.com.alevel;

import ua.com.alevel.annotations.Controller;
import ua.com.alevel.annotations.Start;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.stream.Collectors;

public class DIFrameworkStarter {

    public void start() {
        Set<Object> controllers = BeanFactory.getBeanMap().values()
                .stream()
                .filter(bean -> bean.getClass().isAnnotationPresent(Controller.class))
                .collect(Collectors.toSet());
        controllers.forEach(controller -> {
            for (Method method : controller.getClass().getMethods()) {
                if (method.isAnnotationPresent(Start.class)) {
                    try {
                        method.invoke(controller);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }
}
