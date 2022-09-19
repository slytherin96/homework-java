package task1;

/*1. Написать метод, который меняет два элемента массива местами.(массив может быть любого ссылочного типа);*/

public class ExecuteRearrangeArray<E>{

    public E[] rearrangeArray(E[] inArray, int i, int j){
        E temp = inArray[j];
        inArray[j]= inArray[i];
        inArray[i] = temp;
        return  inArray;
    }

}
