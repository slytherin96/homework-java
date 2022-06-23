public class SumArrayElements {
    private int sum;

    public void sumElements(String[][] matrix) {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {

             try {
                 sum += strNum(matrix[i][j]);
                } catch (NumberFormatException e) {
                 e.printStackTrace();
                 System.out.println(String.format("В ячейке [%s][%s] содержится не число", i+1, j+1));
             }
            }
        }
            System.out.println(sum);


        }

    public static int strNum(String s)  {
        return Integer.parseInt(s);
    }




}
