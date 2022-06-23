import java.util.Random;

public class CreateMatrix {

    private int size;
    private String[][] matrix;

    public CreateMatrix(int size){
        this.size = size;


     }
    public String[][] generationMatrix()  throws MyArraySizeException{
        Random random = new Random();
        matrix = new String[size][size];

            if (size !=4){
                throw new MyArraySizeException(size) ;
            }
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[i].length; j++){
                int num = random.nextInt(65,90);
                matrix[i][j] = String.valueOf(num);
            }
        }
        //matrix[0][1]="test"; //для проверки ошибки на число
        return matrix;
    }

}
