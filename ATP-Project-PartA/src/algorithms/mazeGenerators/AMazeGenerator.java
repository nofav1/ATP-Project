package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator{

    //public abstract Maze generate(int columns, int rows);
    public long measureAlgorithmTimeMillis(int rows, int columns){
        long startTime = System.currentTimeMillis();
        generate(rows, columns);
        return System.currentTimeMillis() - startTime;
    }
}
