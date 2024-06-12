package algorithms.mazeGenerators;

import java.util.Random;

public abstract class AMazeGenerator implements IMazeGenerator{

    //public abstract Maze generate(int columns, int rows);
    public long measureAlgorithmTimeMillis(int rows, int columns){
        long startTime = System.currentTimeMillis();
        generate(rows, columns);
        return System.currentTimeMillis() - startTime;
    }

    public Position[] generateRandomFramePoints(int rows, int cols) {
        Random rand = new Random();
        int[][] points = new int[2][2];

        for (int i = 0; i < 2; i++) {
            int edge = rand.nextInt(4);
            switch (edge) {
                case 0: // Top row
                    points[i][0] = 0;
                    points[i][1] = rand.nextInt(cols);
                    break;
                case 1: // Bottom row
                    points[i][0] = rows - 1;
                    points[i][1] = rand.nextInt(cols);
                    break;
                case 2: // Left column
                    points[i][0] = rand.nextInt(rows);
                    points[i][1] = 0;
                    break;
                case 3: // Right column
                    points[i][0] = rand.nextInt(rows);
                    points[i][1] = cols - 1;
                    break;
            }
        }

        // Ensure the two points are not the same
        while (points[0][0] == points[1][0] && points[0][1] == points[1][1]) {
            int edge = rand.nextInt(4);
            switch (edge) {
                case 0: // Top row
                    points[1][0] = 0;
                    points[1][1] = rand.nextInt(cols);
                    break;
                case 1: // Bottom row
                    points[1][0] = rows - 1;
                    points[1][1] = rand.nextInt(cols);
                    break;
                case 2: // Left column
                    points[1][0] = rand.nextInt(rows);
                    points[1][1] = 0;
                    break;
                case 3: // Right column
                    points[1][0] = rand.nextInt(rows);
                    points[1][1] = cols - 1;
                    break;
            }
        }
        Position[] positions = new Position[]{new Position(points[0][0],points[0][1]),new Position(points[1][0],points[1][1])};
        return positions;
    }
}
