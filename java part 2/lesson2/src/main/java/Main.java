public class Main {
    private static String[][] matrix;
    private static CreateMatrix createMatrix ;

    public static void main(String[] args) {

        try {
            createMatrix = new CreateMatrix(4);
        } catch (MyArraySizeException e){
            e.printStackTrace();
        }

        matrix = createMatrix.generationMatrix();

        SumArrayElements sumArrayElements = new SumArrayElements();
        try {
            sumArrayElements.sumElements(matrix);
        }catch (MyArrayDataException e){
            e.printStackTrace();
        }

}
}
