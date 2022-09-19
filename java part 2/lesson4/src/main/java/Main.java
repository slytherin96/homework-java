import java.util.Arrays;

public class Main {
    private static float[] arrayFull;
    private static float[] arrayPart;

    public static void main(String[] args) {
        ProcessingArrays processingArrayFull = new ProcessingArrays(10000000);
        arrayFull=processingArrayFull.create();
        processingArrayFull.processingArraysFull(arrayFull);

        ProcessingArrays processingArraysarrayPart = new ProcessingArrays(10000000);
        arrayPart=processingArraysarrayPart.create();

        try {
            processingArraysarrayPart.processingArraysPart(arrayPart);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
