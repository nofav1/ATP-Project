package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState{
    public MazeState(Position currentPosition, int cost) {
        this.currentPosition = currentPosition;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return currentPosition.toString();
    }
}
