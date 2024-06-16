package algorithms.mazeGenerators;

import javax.management.InvalidAttributeValueException;

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
