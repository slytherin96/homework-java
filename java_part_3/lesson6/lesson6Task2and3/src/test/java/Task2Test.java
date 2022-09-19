import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.stream.Stream;

public class Task2Test {
    private Task2 task2;

    @BeforeEach
    void startUp() {
        System.out.println("начинается тест");
        task2 = new Task2();
    }
    @AfterEach
    void afterEach() {
        System.out.println("завершается тест");
    }
    @DisplayName("Проверка исключения")
    @Test
    void testException() {
        Assertions.assertThrows(RuntimeException.class, () -> task2.newArray(new int[]{5, 6, 7, 7, 3, 1, 3, 6, 3}));
    }
    @DisplayName("Получение массива")
    @Test
    void testAdd1() {
        Assertions.assertArrayEquals(new int[]{2, 6, 3},task2.newArray(new int[]{5, 6, 7, 4, 2, 4, 2, 6, 3}));
    }
    @DisplayName("Проверка таймаута")
    @Test
    void testTimeout() {
        Assertions.assertTimeout(Duration.ofSeconds(1), () ->  task2.newArray(new int[]{5, 6, 7, 4, 2, 4, 2, 6, 3}));
    }

    @DisplayName("Параметризированный тест массива и таймаута")
    @ParameterizedTest
    @MethodSource("data")
    void paramTest(int[] expected, int[] a) {
        Assertions.assertTimeout(Duration.ofSeconds(1), () -> {
            Assertions.assertArrayEquals(expected, task2.newArray(a));
        });
    }

    static Stream<Arguments> data() {
        return Stream.of(
                Arguments.arguments(new int[]{2, 6, 3},new int[]{5, 6, 7, 4, 2, 4, 2, 6, 3}),
                Arguments.arguments(new int[]{2, 2, 2, 6, 3}, new int[]{5, 6, 7, 4, 2, 2, 2, 6, 3}),
                Arguments.arguments(new int[]{3}, new int[]{5, 4, 7, 4, 2, 4, 2, 4, 3})
        );
    }
}
