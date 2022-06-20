package interfaces;

public interface Obstacle {
    default boolean obstacle(Participant o) {
        return false;
    }
}
