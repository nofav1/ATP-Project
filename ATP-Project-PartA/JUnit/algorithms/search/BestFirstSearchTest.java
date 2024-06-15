package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {

    @Test
    void getName() {
        BestFirstSearch bfs = new BestFirstSearch();
        assertEquals("BestFirstSearch", bfs.getName());
    }

    @Test
    void solve() {
        int[][] m ={ //start- {0, 8}, end - {4, 0}
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };

        Maze maze = new Maze(m,new Position(0, 8), new Position(4, 0));
        ISearchingAlgorithm searcher = new BestFirstSearch();

        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(new SearchableMaze(maze));
        //assertEquals(100, searcher.getNumberOfNodesEvaluated());

        //Printing Solution Path
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        ArrayList<AState> solutionPathExpected = new ArrayList<>(Arrays.asList(
                new MazeState(new Position(0, 8), 0),
                new MazeState(new Position(0, 7), 0),
                new MazeState(new Position(0, 6), 0),
                new MazeState(new Position(0, 5), 0),
                new MazeState(new Position(1, 4), 0),
                new MazeState(new Position(2, 3), 0),
                new MazeState(new Position(3, 2), 0),
                new MazeState(new Position(4, 1), 0),
                new MazeState(new Position(4, 0), 0))

        );
        assertEquals(solutionPathExpected, solutionPath);

        /*//breadth
        ISearchingAlgorithm searcher1 = new BreadthFirstSearch();
        Solution solution1 = searcher1.solve(new SearchableMaze(maze));

        ArrayList<AState> solutionPath1 = solution1.getSolutionPath();
        ArrayList<AState> solutionPathExpected1 = new ArrayList<>(Arrays.asList(
                new MazeState(new Position(0, 8), 0),
                new MazeState(new Position(0, 7), 0),
                new MazeState(new Position(1, 6), 0),
                new MazeState(new Position(2, 5), 0),
                new MazeState(new Position(3, 4), 0),
                new MazeState(new Position(4, 3), 0),
                new MazeState(new Position(5, 2), 0),
                new MazeState(new Position(4, 1), 0),
                new MazeState(new Position(4, 0), 0))

        );
        assertEquals(solutionPathExpected1, solutionPath1);*/
    }

}