package ua.com.alevel;

import ua.com.alevel.annotations.Id;
import ua.com.alevel.config.HibernateConfig;
import ua.com.alevel.entity.User;

import java.lang.reflect.Field;
import java.util.Date;

public class HibernateMain {
    public static void main(String[] args) {
//        User user = new User();
//
//        Class<? extends User> userClass = user.getClass();
//        Field[] declaredFields = userClass.getDeclaredFields();
//
//        StringBuilder stringBuilder = new StringBuilder();
//
//        stringBuilder.append("create table ");
//        stringBuilder.append(userClass.getSimpleName().toLowerCase());
//        stringBuilder.append("s ");
//        stringBuilder.append("(");
//
//        for (Field declaredField : declaredFields) {
//            System.out.println("declaredField = " + declaredField);
//            if (declaredField.isAnnotationPresent(Id.class)) {
//                stringBuilder.append(declaredField.getName());
//                if (declaredField.getType().isAssignableFrom(Long.class)) {
//                    stringBuilder.append(" bigint auto_increment primary key, ");
//                }
//            }
//        }
//        for (Field declaredField : declaredFields) {
//            if (declaredField.getType().isAssignableFrom(String.class)) {
//                stringBuilder.append(declaredField.getName());
//                stringBuilder.append(" varchar(255), ");
//            }
//            if (declaredField.getType().isAssignableFrom(Date.class)) {
//                stringBuilder.append(declaredField.getName());
//                stringBuilder.append(" datetime(6), ");
//            }
//        }
//        stringBuilder.append(")");
//
//        System.out.println("stringBuilder = " + stringBuilder.toString());

        HibernateCrud crud = new HibernateCrud();
        crud.crud();
    }
}
