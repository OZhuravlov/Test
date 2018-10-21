package com.study.reflection;

import java.lang.reflect.Field;
import java.util.Random;

public class ReflectionTest {
    public static void main(String[] args) throws Exception {
        MessagePrinter messagePrinter = new MessagePrinter("hello reflection!");

        injectRandomIntProcessor(messagePrinter);

        messagePrinter.print();
    }

    // F7 step into, F8 step over, F9 resume
    static void injectRandomIntProcessor(Object obj) throws Exception {
        Random random = new Random();
        Class clazz = obj.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            InjectRandomInt annotation = field.getAnnotation(InjectRandomInt.class);
            if (annotation != null) {
                int min = annotation.min();
                int max = annotation.max();

                int randomValue = random.nextInt(max - min + 1) + min;
                System.out.println("randomValue: " + randomValue);
                field.setAccessible(true);
                field.setInt(obj, randomValue);
                field.setAccessible(false);
            }
        }
    }
}
