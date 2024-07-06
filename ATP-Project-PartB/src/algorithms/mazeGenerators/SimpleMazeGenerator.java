package algorithms.mazeGenerators;

import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator{
    @Override
    public Maze generate(int rows, int columns) {
        try {
            Position[] points = generateRandomFramePoints(rows, columns);
            Random rand = new Random();
            int[][] maze_arr = new int[rows][columns];

            //initialize the maze with zeroes
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    maze_arr[i][j] = 1;
                }
            }

            // Create a primary path from start to end
            for (int i = 0; i < rows; i++) {
                maze_arr[i][points[0].getColumnIndex()] = 0;
            }
            for (int j = 0; j < columns; j++) {
                maze_arr[points[1].getRowIndex()][j] = 0;
            }

            // Create additional paths to ensure multiple solutions
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (rand.nextInt(4) != 0 && maze_arr[i][j] == 1) {
                        maze_arr[i][j] = 0;
                    }
                }
            }
            return new Maze(maze_arr, points[0], points[1]);
        }
        catch (Exception e){
            System.out.println("Invalid index entered");
            return new Maze();
        }
    }
}
