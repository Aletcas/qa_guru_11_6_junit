package aletca.annotations;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class SimpleTest {
        @BeforeAll
        static void beforeAll() {
            System.out.println("Этот метод выполняется перед всеми тестами!");
        }

        @BeforeEach
        void before() {
            System.out.println("    Этот метод выполняется перед каждым тестом!");
        }

        @AfterEach
        void after() {
            System.out.println("    Этот метод выполняется после каждого теста!");
        }

        @AfterAll
        static void afterAll() {
            System.out.println("Этот метод выполняется после всех тестов!");
        }
    }
