import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        try {
            TestExecutor.start(classTests.class);
        } catch (InvocationTargetException|IllegalAccessException|NoSuchMethodException|InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}
