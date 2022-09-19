package task2;

import java.util.ArrayList;
import java.util.Arrays;

/*2. Написать метод, который преобразует массив в ArrayList;*/
public class ExecuteConvertedToArrayList<E> {


    public ArrayList<E> ConvertedToArrayList(E[] inArray){
        ArrayList<E> outArrayList = new ArrayList<>(Arrays.asList(inArray));
        return outArrayList;


    }

}
