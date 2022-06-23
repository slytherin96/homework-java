public class MyArraySizeException extends IllegalArgumentException{
    public MyArraySizeException(int num) {
        super(String.format("Размер массива должен быть 4х4, а был создан массив размером %s x %s", num, num));
    }
}
