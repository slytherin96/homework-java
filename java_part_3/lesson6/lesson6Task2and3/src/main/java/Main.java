import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arrayTask2 = {5,6,7,1,4,1,3,6,3};
        Task2 task2 = new Task2();
        System.out.println(Arrays.toString(task2.newArray(arrayTask2)));
        Task3 task3 = new Task3();
        int[] arrayTask3 = {4,4,1,4,1,1,1,4,4,4};
        System.out.println(task3.setArray(arrayTask3));
    }
}
