public class Task3 {
    public boolean setArray(int[] inArray){
        for (int i=0; i < inArray.length; i++){
            if (inArray[i] != 4 && inArray[i] != 1){
                System.out.println(inArray[i]);
                return false;
            }
        }
        return true;
    }
}
