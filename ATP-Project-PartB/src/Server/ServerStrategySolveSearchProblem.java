package Server;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class ServerStrategySolveSearchProblem implements IServerStrategy {
    @Override
    public void serverstrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            toClient.flush();

            Maze maze = (Maze) fromClient.readObject();

            // Generate the unique identifier for the maze
            String tempDirectoryPath = System.getProperty("java.io.tmpdir");
            String mazeIdentifier = getMazeIdentifier(maze);
            String solutionFilePath = tempDirectoryPath + File.separator + mazeIdentifier + ".solution";

            Solution solution;
            File solutionFile = new File(solutionFilePath);

            if (solutionFile.exists()) {
                // If the solution exists, read it from the file
                solution = readSolutionFromFile(solutionFilePath);
                //System.out.println("Returning cached solution.");
            } else {
                // If the solution does not exist, solve the maze and save the solution
                ISearchable searchableMaze = new SearchableMaze(maze);
                String mazeSearchingAlgorithm = Configurations.getInstance().getMazeSearchingAlgorithm();
                ISearchingAlgorithm searcher = (ISearchingAlgorithm) Class.forName("algorithms.search." + mazeSearchingAlgorithm).getDeclaredConstructor().newInstance();
                solution = searcher.solve(searchableMaze);
                saveSolutionToFile(solution, solutionFilePath);
            }

            toClient.writeObject(solution);
            toClient.flush();

            fromClient.close();
            toClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper method to generate a unique identifier for a maze
    private String getMazeIdentifier(Maze maze) throws IOException, NoSuchAlgorithmException {
        byte[] mazeBytes = maze.toByteArray();
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(mazeBytes);
        return HexFormat.of().formatHex(hash);
    }

    // Helper method to save the solution to a file
    private void saveSolutionToFile(Solution solution, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(solution);
        }
    }

    // Helper method to read the solution from a file
    private Solution readSolutionFromFile(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Solution) ois.readObject();

        }
    }
}
