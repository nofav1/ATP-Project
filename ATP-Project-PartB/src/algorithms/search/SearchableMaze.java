package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.Map;

public class SearchableMaze implements ISearchable{
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

        int counter=0, cost;
        for(Integer[] vector: vectorsCost.keySet()){
            Position p = new Position(s.currentPosition.getRowIndex() + vector[0],s.currentPosition.getColumnIndex() + vector[1]);
            if(maze.inMaze(p) && !maze.isWall(p)){
                successors.add(new MazeState(p, vectorsCost.get(vector)));
            }
        }

        return successors;
    }
}
