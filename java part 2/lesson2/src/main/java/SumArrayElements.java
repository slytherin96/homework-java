public class SumArrayElements {
    private int sum;

    public void sumElements(String[][] matrix) throws MyArrayDataException{

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {

             try {
                 sum += Integer.parseInt(matrix[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i,j);
                }
            }
        }
        System.out.println(sum);
    }
}
