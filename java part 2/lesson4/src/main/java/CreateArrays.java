public class CreateArrays {
    private final int SIZE;
    private final int H;
    private float[] arr;
    private long time;
    private float[] arrayPart1;
    private float[] arrayPart2;

    CreateArrays(int size){
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

    public void processingArraysFull(float[] array){
        time = System.currentTimeMillis();
        СalculationFormula calculationFormulaFull= new СalculationFormula();
        calculationFormulaFull.calculation(array);
        System.out.println("Full "+(System.currentTimeMillis() - time));

    }

    public void processingArraysPart(float[] array) throws InterruptedException {
        time = System.currentTimeMillis();

        System.arraycopy(array, 0, arrayPart1, 0, H);
        System.arraycopy(array, H, arrayPart2, 0, H);

        СalculationFormula calculationFormulaPart1= new СalculationFormula();
        СalculationFormula calculationFormulaPart2= new СalculationFormula();

        Thread t1 = new Thread(() -> arrayPart1 = calculationFormulaPart1.calculation(arrayPart1));
        Thread t2 = new Thread(() -> arrayPart2 = calculationFormulaPart2.calculation(arrayPart2));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.arraycopy(arrayPart1, 0, array, 0, H);
        System.arraycopy(arrayPart2, 0, array, H, H);
        System.out.println("Part "+(System.currentTimeMillis() - time));
    }
}
