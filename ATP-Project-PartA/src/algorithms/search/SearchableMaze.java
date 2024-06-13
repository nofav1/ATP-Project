package algorithms.search;

import algorithms.mazeGenerators.Maze;

public class SearchableMaze implements ISearchable{

    //private ISearchingAlgorithm mySearcher;
    private Maze maze;

    public SearchableMaze(Maze maze) {
        this.maze = maze;
    }

    @Override
    public void search() {

    }
}
