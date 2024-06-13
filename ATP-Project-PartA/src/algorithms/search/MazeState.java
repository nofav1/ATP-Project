package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState{
    public MazeState(Position currentPosition) {
        this.currentPosition = currentPosition;

        //set neighbors
        int[][] directions = {
                {0, 1},  // right
                {1, 0},  // down
                {0, -1}, // left
                {-1, 0}, // up
                {-1, -1}, // upper-left diagonal
                {-1, 1},  // upper-right diagonal
                {1, -1},  // lower-left diagonal
                {1, 1}    // lower-right diagonal
        };

        for(int[] vector:directions){
            this.neighbors.add(new Position(vector[0] + currentPosition.getRowIndex(),vector[1] + currentPosition.getColumnIndex()));
        }
    }

}
