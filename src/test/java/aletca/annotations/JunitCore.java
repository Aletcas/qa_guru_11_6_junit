package aletca.annotations;

import aletca.junitCore.SimpleTest;
import org.junit.jupiter.api.BeforeEach;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JunitCore {
    public static void main(String[] args) throws Exception {

        Class clazz = SimpleTest.class;
        for (Method method : clazz.getDeclaredMethods()) {
            BeforeEach methodAnnotation = method.getAnnotation(BeforeEach.class);
            if (methodAnnotation != null) {
                try {
                    method.invoke(clazz.getConstructor().newInstance());
                } catch (InvocationTargetException e) {
                    if (e.getCause() instanceof AssertionError) {
                        System.out.println("Test failed: " + method.getName());
                        continue;
                    } else {
                        System.out.println("Test broken: " + method.getName());
                        continue;
                    }
                }
                System.out.println("Test passed: " + method.getName());
            }
        }
    }
}