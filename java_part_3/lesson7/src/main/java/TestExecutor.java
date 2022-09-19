import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class TestExecutor {
    private static Map<Integer, ArrayList<Method> > arrayTests;
    private static ArrayList<Method> methodTests;
    private static Method beforeTest;
    private static Method afterTest;

    public static void start(Class inClass) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        beforeTest = null;
        afterTest = null;
        arrayTests = new HashMap<>();

        Constructor<?> constructor = inClass.getConstructor();
        classTests objectTest = (classTests) constructor.newInstance();

        Method[] methods = inClass.getDeclaredMethods();

        for (Method method : methods) {

            if (method.isAnnotationPresent(Test.class)) {
                int priority = method.getAnnotation(Test.class).priority();

                methodTests = new ArrayList<>();
                if (arrayTests.containsKey(priority)){
                    methodTests.addAll(arrayTests.get(priority));
                }
                methodTests.add(method);
                arrayTests.put(priority,methodTests);
            }

            if (method.isAnnotationPresent(AfterSuite.class)){
                if (afterTest != null){
                    throw new RuntimeException("Не может быть несколько методов с аннотацией AfterSuite");
                }
                afterTest = method;
            }

            if (method.isAnnotationPresent(BeforeSuite.class)){
                if (beforeTest != null){
                    throw new RuntimeException("Не может быть несколько методов с аннотацией BeforeSuite");
                }
                beforeTest = method;
            }
        }

        if (beforeTest != null){
            beforeTest.setAccessible(true);
            beforeTest.invoke(objectTest);
        }

        for (int i=1; i<=10; i++){
            if (arrayTests.containsKey(i)) {
                for (int j = 0; j < arrayTests.get(i).size(); j++) {
                    System.out.print( arrayTests.get(i).get(j).getName()+"(приоритет " + i + ") : ");
                    arrayTests.get(i).get(j).setAccessible(true);
                    arrayTests.get(i).get(j).invoke(objectTest);
                }
            }
        }

        if (afterTest != null){
            afterTest.setAccessible(true);
            afterTest.invoke(objectTest);
        }
    }

}
