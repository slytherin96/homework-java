import interfaces.Obstacle;
import interfaces.Participant;


public class Main {

    public static void main(String[] args) {

        Cat cat = new Cat("Cat", 50, 100);
        Human human = new Human("Human", 180, 200);
        Robot robot = new Robot("robot", 200, 250);
        Wall wall = new Wall(150);
        Road road = new Road(200);
        Wall wallBig = new Wall(200);

        Participant[] participants = {cat, human, robot};
        Obstacle[] obstacles = {wallBig, road, wall};

        for  (Participant participant:participants){
            for (int i=0; i<obstacles.length; i++){
                if (!obstacles[i].obstacle(participant)){
                    break;
                }
            }
        }



    }
}
