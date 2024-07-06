package algorithms.search;

import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    private ArrayList<AState> solutionPath;

    public Solution() {
        solutionPath = new ArrayList<>();
    }

    // Method to reverse the solution path
    public void reversePath() {
        Collections.reverse(solutionPath);
    }

    public Solution(ArrayList<AState> solutionPath) {
        this.solutionPath = solutionPath;
    }

    public ArrayList<AState> getSolutionPath(){
        return solutionPath;
    }
}
