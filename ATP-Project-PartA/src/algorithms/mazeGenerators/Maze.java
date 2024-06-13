package algorithms.mazeGenerators;

public class Maze {
    private int[][] maze;
    private Position startPosition;
    private Position goalPosition;

    public Maze(int[][] maze, Position startPosition, Position goalPosition) {
        this.maze = maze;
        this.startPosition = startPosition;
        this.goalPosition = goalPosition;
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
