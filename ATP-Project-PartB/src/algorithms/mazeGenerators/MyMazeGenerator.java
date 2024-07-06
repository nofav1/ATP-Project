package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyMazeGenerator extends AMazeGenerator{
    final int WALL = 1;
    final int PASSAGE = 0;
    @Override
    public Maze generate(int rows, int columns) {
        try {
            int[][] maze = new int[rows][columns];
            List<int[]> wallList = new ArrayList<>();
            Random random = new Random();

            // Fill the maze with walls
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    maze[i][j] = WALL;
                }
            }

            // Pick a random starting cell (ensuring it is an odd index)
            int startRow = random.nextInt(rows / 2) * 2 + 1;
            int startCol = random.nextInt(columns / 2) * 2 + 1;
            int rowBetween, colBetween;

            // Mark the starting cell as part of the maze
            maze[startRow][startCol] = PASSAGE;

            // Add the walls of the starting cell to the wall list
            addWalls(startRow, startCol, wallList, rows, columns);


            while (!wallList.isEmpty()) {
                // Pick a random wall from the list
                int[] wall = wallList.remove(random.nextInt(wallList.size()));
                int wallRow = wall[0];
                int wallCol = wall[1];

                // Determine the neighboring cells divided by the wall
                if (maze[wallRow][wallCol] == WALL) {
                    List<int[]> neighbors = getNeighbors(wallRow, wallCol, maze);
                    // If the wall divides a visited and an unvisited cell, neighbors.size() will be 1
                    if (neighbors.size() == 1) {
                        int[] neighbor = neighbors.get(0);
                        startRow = neighbor[0];
                        startCol = neighbor[1];

                        rowBetween = (wallRow + startRow) / 2;
                        colBetween = (wallCol + startCol) / 2;
                        maze[rowBetween][colBetween] = PASSAGE;
                        maze[wallRow][wallCol] = PASSAGE;
                        addWalls(wallRow, wallCol, wallList, rows, columns);

                    }
                }
            }

            //remove frame
            int[][] mazeWithOutFrame = new int[rows][columns];
            for (int i = 0; i < mazeWithOutFrame.length; i++) {
                for (int j = 0; j < mazeWithOutFrame[0].length; j++) {
                    mazeWithOutFrame[i][j] = maze[i][j]; // Copy elements from the original array to the cropped array
                }
            }
            //choose start and end position
            int[] start = getRandomPointOnFrame(rows, columns);
            int[] end = getRandomPointOnFrame(rows, columns);

            while (mazeWithOutFrame[start[0]][start[1]] == WALL && getAdjacentPoint(start[0], start[1], start[2], maze) == WALL) {
                start = getRandomPointOnFrame(rows, columns);
            }

            while ((end[0] == start[0] && end[1] == start[1]) || (mazeWithOutFrame[end[0]][end[1]] == WALL && getAdjacentPoint(end[0], end[1], end[2], maze) == WALL)) {
                end = getRandomPointOnFrame(rows, columns);
            }

            if (mazeWithOutFrame[start[0]][start[1]] == WALL) {
                mazeWithOutFrame[start[0]][start[1]] = PASSAGE;
            }
            if (mazeWithOutFrame[end[0]][end[1]] == WALL) {
                mazeWithOutFrame[end[0]][end[1]] = PASSAGE;
            }

            return new Maze(mazeWithOutFrame, new Position(start[0], start[1]), new Position(end[0], end[1]));
        }
        catch (Exception e){
            System.out.println("Invalid index entered");
            return new Maze();
        }
    }

    public int getAdjacentPoint(int row, int col, int direction, int[][] maze){
        if(direction == 0) //top
        {
            if(maze[row+1][col] == WALL){return WALL;}
            else{return PASSAGE;}
        } else if (direction == 1) { //bottom
            if(maze[row-1][col] == WALL){return WALL;}
            else{return PASSAGE;}
        } else if (direction == 2) { //left
            if(maze[row][col+1] == WALL){return WALL;}
            else{return PASSAGE;}
        }
        else{ //right
            if(maze[row][col-1] == WALL){return WALL;}
            else{return PASSAGE;}
        }
    }
    public int[] getRandomPointOnFrame(int rows, int columns) {
        // Initialize a random number generator
        Random random = new Random();

        // Choose a random side (top, bottom, left, or right)
        int side = random.nextInt(4); // 0: top, 1: bottom, 2: left, 3: right

        int row = 0, col = 0; // Initialize row and column for the random point
        int direction = 0;
        switch (side) {
            case 0: // Top side
                row = 0;
                col = random.nextInt(columns); // Random column on the top row
                direction = 0;
                break;
            case 1: // Bottom side
                row = rows - 1;
                col = random.nextInt(columns); // Random column on the bottom row
                direction = 1;
                break;
            case 2: // Left side
                row = random.nextInt(rows); // Random row on the left column
                col = 0;
                direction = 2;
                break;
            case 3: // Right side
                row = random.nextInt(rows); // Random row on the right column
                col = columns - 1;
                direction = 3;
                break;
        }
        return new int[]{row, col, direction};
    }

    private void addWalls(int row, int col, List<int[]> wallList, int rows, int columns) {
        if (row > 1) wallList.add(new int[]{row - 2, col});
        if (row < rows - 2) wallList.add(new int[]{row + 2, col});
        if (col > 1) wallList.add(new int[]{row, col - 2});
        if (col < columns - 2) wallList.add(new int[]{row, col + 2});
    }

    private List<int[]> getNeighbors(int row, int col, int[][] maze) {
        List<int[]> neighbors = new ArrayList<>();
        // Check all four possible neighboring cells and add if they are passages
        if (row > 1 && maze[row - 2][col] == PASSAGE) neighbors.add(new int[]{row - 2, col});
        if (row < maze.length - 2 && maze[row + 2][col] == PASSAGE) neighbors.add(new int[]{row + 2, col});
        if (col > 1 && maze[row][col - 2] == PASSAGE) neighbors.add(new int[]{row, col - 2});
        if (col < maze[0].length - 2 && maze[row][col + 2] == PASSAGE) neighbors.add(new int[]{row, col + 2});
        return neighbors;
    }

}
