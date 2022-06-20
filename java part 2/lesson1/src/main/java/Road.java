import interfaces.Obstacle;
import interfaces.Participant;

public class Road implements Obstacle {

    private int len;

    public Road(int len){
        this.len = len;
    }

    @Override
    public boolean obstacle(Participant o) {
       return o.run(len);
    }
}
