import java.util.concurrent.*;

public class MainClass {
    public static final int CARS_COUNT = 4;
    static CountDownLatch waitStart = new CountDownLatch(CARS_COUNT);
    static CountDownLatch waitContinionRace = new CountDownLatch(CARS_COUNT);
    static Semaphore waitTunnel = new Semaphore(CARS_COUNT/2);
    static CountDownLatch finishLine = new CountDownLatch(CARS_COUNT);
    static CountDownLatch carWin = new CountDownLatch(2);


    public static void main(String[] args) {

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }

        for (int i = 0; i < cars.length; i++) {

            new Thread(cars[i]).start();
        }
        try {
            MainClass.waitStart.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

        try {
            MainClass.finishLine.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}

