package algorithms.search;


import algorithms.mazeGenerators.Position;

import java.util.List;
import java.util.Objects;

public abstract class AState {
    protected Position currentPosition;
    protected int cost;
    protected AState cameFrom;

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public AState getCameFrom() {
        return cameFrom;
    }

    public void setCameFrom(AState cameFrom) {
        this.cameFrom = cameFrom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AState aState = (AState) o;
        return this.currentPosition.equals(aState.currentPosition);
    }
    @Override
    public int hashCode() {
        return Objects.hash(currentPosition);
    }
}
