package aletca.parameterized;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParameterizedWithFakerTest {

        @DisplayName("Сумма двух массивов")
        @ParameterizedTest(name = "{index}: ({0} + {1}) => {2})")
        @MethodSource("localParameters")
        void test(int first, int second, int sum) {
            assertEquals(sum, first + second);
        }

        static Stream<Arguments> localParameters() {
            return Stream.of(
                    Arguments.of(5, 4, 9),
                    Arguments.of(2, 3, 5),
                    Arguments.of(1, 7, 8)
            );
        }
    }

