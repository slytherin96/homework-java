import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[][] matrix;

        CreateMatrix createMatrix = new CreateMatrix(4);
        matrix = createMatrix.generationMatrix();

        SumArrayElements sumArrayElements = new SumArrayElements();
       sumArrayElements.sumElements(matrix);
}
}
