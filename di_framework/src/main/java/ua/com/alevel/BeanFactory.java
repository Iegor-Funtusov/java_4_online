package ua.com.alevel;

import org.reflections.Reflections;
import ua.com.alevel.annotations.BeanClass;
import ua.com.alevel.configutator.BeanConfigurator;
import ua.com.alevel.configutator.impl.InjectBeanAnnotationBeanConfigurator;
import ua.com.alevel.configutator.impl.ValueAnnotationBeanConfigurator;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class BeanFactory {

    private static Map<Class<?>, Object> beanMap = new HashMap<>();
    private List<BeanConfigurator> beanConfigurators = Arrays.asList(
            new InjectBeanAnnotationBeanConfigurator(),
            new ValueAnnotationBeanConfigurator());

    public BeanFactory(Set<Class<?>> interfaces, Reflections scanner) {
        initBeanMap(interfaces, scanner);
    }

    private void initBeanMap(Set<Class<?>> interfaces, Reflections scanner) {
        for (Class<?> anInterface : interfaces) {
            Set<Class<?>> subTypesOf = (Set<Class<?>>) scanner.getSubTypesOf(anInterface);
            Optional<Class<?>> mainSubType = subTypesOf.stream()
                    .filter(subType -> subType.isAnnotationPresent(BeanClass.class))
                    .findFirst();
            if (mainSubType.isPresent()) {
                Class<?> mainSubImpl = mainSubType.get();
                try {
                    Object impl = mainSubImpl.getDeclaredConstructor().newInstance();
                    beanMap.put(anInterface, impl);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void configure() {
        beanMap.forEach((k, v) -> {
            beanConfigurators.forEach(beanConfigurator -> beanConfigurator.configure(v));
        });
    }

    public static Map<Class<?>, Object> getBeanMap() {
        return beanMap;
    }
}
