public class MyArrayDataException extends NumberFormatException {

    public MyArrayDataException(int line, int column) {
        super(String.format("В ячейке [%s][%s] содержится не число", line+1, column+1));
    }
}

