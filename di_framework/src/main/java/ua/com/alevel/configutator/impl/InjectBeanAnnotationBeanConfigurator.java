package ua.com.alevel.configutator.impl;

import ua.com.alevel.BeanFactory;
import ua.com.alevel.annotations.InjectBean;
import ua.com.alevel.configutator.BeanConfigurator;

import java.lang.reflect.Field;

public class InjectBeanAnnotationBeanConfigurator implements BeanConfigurator {

    @Override
    public void configure(Object bean) {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(InjectBean.class)) {
                Object dependency = BeanFactory.getBeanMap().get(field.getType());
                field.setAccessible(true);
                try {
                    field.set(bean, dependency);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
