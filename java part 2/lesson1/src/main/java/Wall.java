import interfaces.Obstacle;
import interfaces.Participant;

public class Wall implements Obstacle {

    private int height;

    public Wall(int height){
        this.height = height;
    }
    @Override
    public boolean obstacle(Participant o) {

        return o.jump(height);
    }



}
