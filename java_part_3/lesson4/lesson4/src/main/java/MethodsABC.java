import java.util.ArrayList;

public class MethodsABC {
    private ArrayList<String> listABC;
    private int number;
    private int sizeList;
    public MethodsABC() {
        listABC = new ArrayList<>();
        number = 0;
        sizeList = 0;
    }

    public synchronized void getMethodA() throws InterruptedException {
        sizeList = listABC.size();
        if (sizeList!=15 && ((sizeList!=0 && listABC.get(sizeList - 1)=="C")||sizeList==0)) {
            listABC.add("A");
            System.out.println(++number+" "+listABC.get(++sizeList - 1));
        }
        notify();
        wait();
    }

    public synchronized void getMethodB() throws InterruptedException {
        sizeList = listABC.size();
        if (sizeList!=15 && sizeList!=0 && listABC.get(sizeList - 1)=="A") {
            listABC.add("B");
            System.out.println(++number+" "+listABC.get(++sizeList - 1));
        }
        notify();
        wait();
    }

    public synchronized void getMethodC() throws InterruptedException {
        sizeList = listABC.size();
        if (sizeList!=15 && sizeList!=0 && listABC.get(sizeList - 1)=="B") {
            listABC.add("C");
            System.out.println(++number+" "+listABC.get(++sizeList - 1));
            notify();
            if (sizeList!=15) {
                wait();
            }
            else {
                notifyAll();
            }
        }
    }

    public int getSizeListABC() {
        return listABC.size();
    }
}
