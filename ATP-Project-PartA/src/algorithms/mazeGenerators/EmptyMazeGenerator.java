package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator{
    @Override
    public Maze generate(int rows, int columns) {
        int[][] maze = new int[rows][columns];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                maze[i][j] = 0;
            }
        }
        Position s = new Position(0, 0);
        Position e = new Position(rows - 1, columns - 1);
        return new Maze(maze, s, e);
    }
}
