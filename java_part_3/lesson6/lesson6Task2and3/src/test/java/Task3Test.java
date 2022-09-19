import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.stream.Stream;

public class Task3Test {
    private Task3 task3;

    @BeforeEach
    void startUp() {
        System.out.println("начинается тест");
        task3 = new Task3();
    }
    @AfterEach
    void afterEach() {
        System.out.println("завершается тест");
    }
    @DisplayName("Проверка массива")
    @Test
    void testAdd1() {
        Assertions.assertEquals(false,task3.setArray(new int[]{4,4,1,2,4,1,4,4}));
    }

    @DisplayName("Проверка таймаута")
    @Test
    void testTimeout() {
        Assertions.assertTimeout(Duration.ofSeconds(1), () ->  task3.setArray(new int[]{4,4,1,2,4,1,4,4}));
    }

    @DisplayName("Параметризированный тест массива и таймаута")
    @ParameterizedTest
    @MethodSource("data")
    void paramTest(boolean expected, int[] a) {
        Assertions.assertTimeout(Duration.ofSeconds(1), () -> {
            Assertions.assertEquals(expected, task3.setArray(a));
        });
    }

    static Stream<Arguments> data() {
        return Stream.of(
                Arguments.arguments(true,new int[]{4,4,1,4,4,1,4,4}),
                Arguments.arguments(false, new int[]{4,4,1,2,4,1,4,4}),
                Arguments.arguments(false, new int[]{4,4,1,1,4,1,4,2})
        );
    }

}
