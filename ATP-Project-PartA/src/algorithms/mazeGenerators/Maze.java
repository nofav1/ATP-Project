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

    public void print(){
        int row = maze.length;
        int col = maze[0].length;
        for(int i = 0; i < row; i++){
            //System.out.print("{");
            for(int j = 0; j < col; j++){
                if(startPosition.getColumnIndex() == j && startPosition.getRowIndex() == i){
                    System.out.print("S");
                } else if (goalPosition.getColumnIndex() == j && goalPosition.getRowIndex() == i) {
                    System.out.print("E");
                }
                else{
                    System.out.print(maze[i][j]);
                }
                /*if(j+1 != col) { //not last element
                    System.out.print(",");
                }*/
            }
            System.out.println();
        }
    }
}
