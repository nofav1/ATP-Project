package algorithms.search;


import algorithms.mazeGenerators.Position;

import java.util.List;

public abstract class AState {
    protected Position currentPosition;
    protected List<Position> neighbors;

    public void setNeighbors(List<Position> neighbors){
        this.neighbors = neighbors;
    }
    public List<Position> getNeighbors(){
        return neighbors;
    };

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }
}
