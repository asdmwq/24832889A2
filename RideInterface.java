
public interface RideInterface {
    // Part 3: Queue-related methods
    void addVisitorToQueue(Visitor visitor);
    void removeVisitorFromQueue();
    void printQueue();

    // Part 4: Ride history-related methods
    void addVisitorToHistory(Visitor visitor);
    boolean checkVisitorFromHistory(Visitor visitor);
    int numberOfVisitors();
    void printRideHistory();

    // Part 5: Ride cycle operation method
    void runOneCycle();
}
