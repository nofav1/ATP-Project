package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy {

    @Override
    public void serverstrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            toClient.flush();

            int[] mazeDimensions = (int[]) fromClient.readObject();
            int rows = mazeDimensions[0];
            int cols = mazeDimensions[1];

            // Get the maze generating algorithm from configurations
            String mazeGeneratingAlgorithm = Configurations.getInstance().getMazeGeneratingAlgorithm();
            IMazeGenerator mazeGenerator = (IMazeGenerator) Class.forName("algorithms.mazeGenerators." + mazeGeneratingAlgorithm).getDeclaredConstructor().newInstance();
            // Generate the maze
            Maze maze = mazeGenerator.generate(rows, cols);

            // Compress and send the maze to the client
            byte[] compressedMaze = compressMaze(maze.toByteArray());
            toClient.writeObject(compressedMaze);
            toClient.flush(); // Ensure the data is sent immediately
            // Send the maze to the client
            toClient.write(maze.toByteArray());

            toClient.flush();

            fromClient.close();
            toClient.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper method to compress the maze data (assuming MyCompressorOutputStream is for compression)
    private byte[] compressMaze(byte[] mazeData) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        MyCompressorOutputStream compressorOutputStream = new MyCompressorOutputStream(byteArrayOutputStream);
        compressorOutputStream.write(mazeData);
        compressorOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
}
