import java.util.Arrays;

public class Main {
    private static float[] arrayFull;
    private static float[] arrayPart;

    public static void main(String[] args) {
        CreateArrays createArrayFull = new CreateArrays(10000000);
        arrayFull=createArrayFull.create();
        createArrayFull.processingArraysFull(arrayFull);

        CreateArrays createArraysarrayPart = new CreateArrays(10000000);
        arrayPart=createArraysarrayPart.create();

        try {
            createArraysarrayPart.processingArraysPart(arrayPart);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
