public class Main {
    public static void main(String[] args) {
        MethodsABC methodsABC = new MethodsABC();

        new Thread(() -> {
            /*for (int i = 0; i < 5; i++)*/
            while (methodsABC.getSizeListABC()<15){
                try {
                    methodsABC.getMethodA();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        new Thread(() -> {
            /*for (int i = 0; i < 5; i++)*/
            while (methodsABC.getSizeListABC()<15){
                try {
                    methodsABC.getMethodB();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        new Thread(() -> {
            /*for (int i = 0; i < 5; i++)*/
            while (methodsABC.getSizeListABC()<15){
                try {
                    methodsABC.getMethodC();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

    }
}
