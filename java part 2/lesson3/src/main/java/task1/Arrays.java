package task1;

import java.util.ArrayList;
import java.util.Collections;

public class Arrays {
    private static String value;
    private static int count;
    private static ArrayList<String> array;

    public static void main(String[] args) {
        array = new ArrayList<>();
        Collections.addAll(array, "dom", "cat", "top", "dom", "wert", "cat", "top", "cat", "dom", "cat", "top", "dom", "wert");
        Collections.sort(array);
        value=array.get(0);
        for (String word : array) {
            if (!value.equals(word) ) {
                System.out.println(String.format("%s:%s",value,count));
                count=0;
            }
            value=word;
            count+=1;
        }
        System.out.println(String.format("%s:%s",value,count));
    }
}
