import interfaces.Participant;

public class Robot implements Participant {
    private String name;
    private int runs;
    private int jumpWall;
    public Robot (String name,int runs,int jumpWall){
        this.name = name;
        this.runs = runs;
        this.jumpWall=jumpWall;
    }

    @Override
    public boolean jump(int height) {
        if (jumpWall>=height) {
            System.out.format("%s успешно пробежал %s %n", name, jumpWall);
            return true;
        }
        else {
            System.out.format("%s не удалось пробежать %s %n", name, jumpWall);
            return false;
        }
    }

    @Override
    public boolean run(int len) {
        if (runs>=len) {
            System.out.format("%s успешно прыгнул %s %n", name, runs);
            return true;
        }
        else {
            System.out.format("%s не удалось прыгнуть %s %n", name, runs);
            return false;
        }
    }

    public String getName() {
        return name;
    }
}

