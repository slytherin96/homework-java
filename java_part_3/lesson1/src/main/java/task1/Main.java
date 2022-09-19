package task1;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        String[] array = {"df", "df", "re", "ft", "gg"};
        ExecuteRearrangeArray<String> executeRearrangeArray = new ExecuteRearrangeArray<String>();
        array = executeRearrangeArray.rearrangeArray(array,2,3);
        System.out.println(Arrays.toString(array));


        }

}
