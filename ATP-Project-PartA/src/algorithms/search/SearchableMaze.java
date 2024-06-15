package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.Map;

public class SearchableMaze implements ISearchable{

    //private ISearchingAlgorithm mySearcher;
    private Maze maze;

    public SearchableMaze(Maze maze) {
        this.maze = maze;
    }

    @Override
    public AState getStartState() {
        return new MazeState(maze.getStartPosition(), 0);
    }

    @Override
    public AState getGoalState() {
        return new MazeState(maze.getGoalPosition(),0);
    }

    @Override
    public ArrayList<AState> getAllSuccessors(AState s, Map<Integer[],Integer> vectorsCost) {
        ArrayList<AState> successors = new ArrayList<>();

        //set neighbors
//        int[][] directions = {
//                {-1, 0}, // up
//                {-1, 1}, // upper-right diagonal
//                {0, 1},  // right
//                {1, 1},  // lower-right diagonal
//                {1, 0},  // down
//                {1, -1},  // lower-left diagonal
//                {0, -1}, // left
//                {-1, -1}, // upper-left diagonal
//        };

        int counter=0, cost;
        for(Integer[] vector: vectorsCost.keySet()){
//            if(counter % 2 == 0){cost=10;}
//            else{cost = 15;}
//            counter++;

            Position p = new Position(s.currentPosition.getRowIndex() + vector[0],s.currentPosition.getColumnIndex() + vector[1]);
            if(maze.inMaze(p) && !maze.isWall(p)){
                successors.add(new MazeState(p, vectorsCost.get(vector)));
            }
        }

        return successors;
    }
}
