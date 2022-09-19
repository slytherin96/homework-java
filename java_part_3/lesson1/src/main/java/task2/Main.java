package task2;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String[] array = {"df", "df", "re", "ft", "gg"};
        ExecuteConvertedToArrayList<String> executeConvertedToArrayList = new ExecuteConvertedToArrayList<>();
        ArrayList<?> newArryList = executeConvertedToArrayList.ConvertedToArrayList(array);
        System.out.println(newArryList.get(3));
    }
}
