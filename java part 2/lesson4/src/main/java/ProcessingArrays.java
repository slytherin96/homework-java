import java.util.Arrays;

public class ProcessingArrays {
    private final int SIZE;
    private final int H;
    private float[] arr;
    private long time;
    private float[] arrayPart1;
    private float[] arrayPart2;

    ProcessingArrays(int size){
        this.SIZE = size;
        this.arr = new float[SIZE];
        this.H = SIZE/2;
        this.arrayPart1 = new float[H];
        this.arrayPart2 = new float[H];
    }

    public float[] create(){
        for (int i=0; i<arr.length;i++){
            arr[i]=1;
        }
        return arr;
    }

    private static float[] calculation(float[] array, int h) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (float) (array[i] * Math.sin(0.2f + h / 5) * Math.cos(0.2f + h / 5) * Math.cos(0.4f + h / 2));
            h++;
        }
        return array;
    }
    public void processingArraysFull(float[] array){
        time = System.currentTimeMillis();
        array=calculation(array,0);
        System.out.println("Full "+(System.currentTimeMillis() - time));

    }

   public void processingArraysPart(float[] array) throws InterruptedException {
        time = System.currentTimeMillis();

        System.arraycopy(array, 0, arrayPart1, 0, H);
        System.arraycopy(array, H, arrayPart2, 0, H);

        Thread t1 = new Thread(() -> arrayPart1 = calculation(arrayPart1,0));
        Thread t2 = new Thread(() -> arrayPart2 = calculation(arrayPart2,H));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.arraycopy(arrayPart1, 0, array, 0, H);
        System.arraycopy(arrayPart2, 0, array, H, H);
        System.out.println("Part "+(System.currentTimeMillis() - time));
    }
}
