import java.util.Arrays;

public class Task2 {
    private int index;
    public int[] newArray(int[] inArray){
        index=-1;
        for (int i=0; i < inArray.length; i++){
            if (inArray[i]==4){
                index=i;
            }
        }
        if (index==-1){
            throw new RuntimeException();
        }
        return Arrays.copyOfRange(inArray,index+1,inArray.length);
    }
}
