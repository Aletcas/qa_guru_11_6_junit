package aletca.junitCore;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationsTests {
    /* lookup classes with annotation @BeforeEach
     для поиска классов с аннотацией существуют библиотеки. Одна из них AGraph
    Исходим из того, что мы знаем о существовании только одного класса. SimpleTest
    a) из любого класса можно создать объект через new
    б) у него есть метаинформация об этом классе - объект типа Class
    У любого объекта есть метод get.class и получить информацию о классе из которого этот объект создан
    Либо, если у нас нет объекта, но есть класс, то можно этот класс положить в переменную.
    Class clazz = SimpleTest.class;
    Таким образом мы можем получить информацию о том, какие в нем есть методы, какие поля, конструкторы.
    Аннотации тоже хранятся в метоинформации о классах, если выполнено одно условие - область существования аннотаций.
    Их существует три.
    Они тоже создаются аннотацией, которая стоит над аннотацией.
    @Retention: SOURCE (означает, что ее не существует во время выполнения кода, она только для визуализации кода, например
    @Override),
     CLASS(промежуточное состояние, нам оно не понадобится),
      RUNTIME - основная аннотация, которая позволяет читать информацию.
        for (Method method : clazz.getDeclaredMethod())
 идем циклом по всем методами, имеющимся в этом классе
getDeclaredMethod - возвращает массив методов, которые есть в этом классе.


     */

    //  run all methods with @BeforeEach
    //  print results

    public static void main(String[] args) throws Exception {

        Class clazz = SimpleTest.class;
        // достали информацию о тестовом классе
        for (Method method : clazz.getDeclaredMethods()) {
            //  дай информацию о всех своих методах
            Test methodAnnotation = method.getAnnotation(Test.class);
            // получили информацию об аннотации @test, если она там есть
            if (methodAnnotation != null) {
                // если эта аннотация там есть,
                // теперь дальше запускаем конкретный метод
                try {
                    method.invoke(clazz.getConstructor().newInstance());
                    // invoke - метод, который запускает этот метод
                    //  у него есть  два аргумента: первый - это тот объект, метод которого мы вызываем
                    // все нестатические методы вызываются от объекта. Поэтому вызываем конструктор и дальше новый инстанс
                    // таким образом мы запустили тест.
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