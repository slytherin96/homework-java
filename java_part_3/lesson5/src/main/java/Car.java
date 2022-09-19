
public class Car implements Runnable {
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            MainClass.waitStart.countDown();

            System.out.println(this.name + " готов");
            MainClass.waitContinionRace.countDown();
            MainClass.waitContinionRace.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

        MainClass.carWin.countDown();
        if (MainClass.carWin.getCount() == 1) {
            System.out.println(name + " - WIN");
        }

        MainClass.finishLine.countDown();
    }
}
