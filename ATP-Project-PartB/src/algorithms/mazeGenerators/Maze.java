package algorithms.mazeGenerators;

import javax.management.InvalidAttributeValueException;
import java.nio.ByteBuffer;

public class Maze {
    private int[][] maze;
    private Position startPosition;
    private Position goalPosition;

    public Maze() {
        maze = new int[][]{};
        startPosition = new Position(-1, -1);
        goalPosition = new Position(-1, -1);
    }

    public Maze(int[][] maze, Position startPosition, Position goalPosition) {
        try {
            this.maze = maze;
            this.startPosition = startPosition;
            this.goalPosition = goalPosition;
            if(maze == null || startPosition.getRowIndex() < 0 || startPosition.getColumnIndex() < 0 || goalPosition.getRowIndex() < 0 || goalPosition.getColumnIndex() < 0 || startPosition.getRowIndex() >= maze.length || startPosition.getColumnIndex() >= maze[0].length || goalPosition.getRowIndex() >= maze.length || goalPosition.getColumnIndex() >= maze[0].length){
                throw new InvalidAttributeValueException();
            }
        }
        catch (InvalidAttributeValueException e) {
            System.out.println("Invalid input");
        }
        catch (Exception e){
            System.out.println("Invalid input");
        }
    }

    public Maze(int[][] maze) {
        this.maze = maze;
    }

    //format: rows, cols, startRow, startCol, goalRow, goalCol, maze
    public Maze(byte[] bytes){
        ByteBuffer buffer = ByteBuffer.wrap(bytes);

        // Get dimensions
        int rows = buffer.getInt();
        int cols = buffer.getInt();

        // Get start and end positions
        startPosition = new Position(buffer.getInt(), buffer.getInt());
        goalPosition = new Position(buffer.getInt(), buffer.getInt());

        // Get maze data
        this.maze = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maze[i][j] = buffer.get();
            }
        }
    }

    //format: rows, cols, startRow, startCol, goalRow, goalCol, maze
    public byte[] toByteArray(){
        int rows = maze.length;
        int cols = maze[0].length;

        // Calculate the total size needed for the byte array
        int size = 2 * Integer.BYTES + 4 * Integer.BYTES + rows * cols;

        ByteBuffer buffer = ByteBuffer.allocate(size);

        // Add dimensions
        buffer.putInt(rows);
        buffer.putInt(cols);

        // Add start and end positions
        buffer.putInt(startPosition.getRowIndex());
        buffer.putInt(startPosition.getColumnIndex());
        buffer.putInt(goalPosition.getRowIndex());
        buffer.putInt(goalPosition.getColumnIndex());

        // Add maze data
        for (int[] row : maze) {
            for (int cell : row) {
                buffer.put((byte) cell);
            }
        }

        return buffer.array();

    }

    public int[][] getMaze() {
        return maze;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public Position getGoalPosition() {
        return goalPosition;
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }

    public void setStartPosition(Position startPosition) {
        this.startPosition = startPosition;
    }

    public void setGoalPosition(Position goalPosition) {
        this.goalPosition = goalPosition;
    }

    public boolean isWall(Position p){
        return maze[p.getRowIndex()][p.getColumnIndex()] == 1;
    }

    public boolean inMaze(Position p){
        return p.getRowIndex() < maze.length && p.getRowIndex() >= 0 && p.getColumnIndex() < maze[0].length && p.getColumnIndex() >= 0;
    }

    public void print(){
        int row = maze.length;
        int col = maze[0].length;
        System.out.println("-".repeat(col+2));
        for(int i = 0; i < row; i++){
            System.out.print("|");
            for(int j = 0; j < col; j++){
                if(startPosition.getColumnIndex() == j && startPosition.getRowIndex() == i){
                    System.out.print("S");
                } else if (goalPosition.getColumnIndex() == j && goalPosition.getRowIndex() == i) {
                    System.out.print("E");
                }
                else{
                    //System.out.print(maze[i][j]);
                    if(maze[i][j] == 0) {
                        System.out.print(0);
                    }
                    else{
                        System.out.print(1);
                    }
                }
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("-".repeat(col+2));
    }
}
