package algorithms.search;

import java.util.*;

public class DepthFirstSearch extends ASearchingAlgorithm{
    private Stack<AState> stack;
    private Set<AState> inProgress;

    public DepthFirstSearch() {
        stack = new Stack<>();
        inProgress = new HashSet<>();
        vectorsCost = new LinkedHashMap<>() {{
            put(new Integer[]{-1, 0}, 1);// up
            put(new Integer[]{-1, 1}, 1);// upper-right diagonal
            put(new Integer[]{0, 1}, 1);// right
            put(new Integer[]{1, 1}, 1);// lower-right diagonal
            put(new Integer[]{1, 0}, 1); // down
            put(new Integer[]{1, -1}, 1); // lower-left diagonal
            put(new Integer[]{0, -1}, 1); // left
            put(new Integer[]{-1, -1}, 1); // upper-left diagonal

        }};
    }

    @Override
    public Solution solve(ISearchable domain) {
        try {


            // Initialize stack and visited set
            AState startState = domain.getStartState();
            AState goalState = domain.getGoalState();
            stack.push(startState);
            visited.add(startState);
            inProgress.add(startState);
            AState currentState = startState;

            while (!stack.isEmpty()) {
                AState formerState = currentState;
                currentState = stack.pop();

                // Check if goal state is reached
                if (currentState.equals(goalState)) {
                    return constructSolution(currentState);
                }

                // Get all successors
                ArrayList<AState> successors = domain.getAllSuccessors(currentState, vectorsCost);
                Collections.reverse(successors);
                for (AState successor : successors) {
                    if (!visited.contains(successor)) {
                        successor.setCameFrom(currentState);
                        stack.push(successor);
                        inProgress.add(successor);
                    }
                }
                visited.add(currentState);
                visitedNodes++;
            }
            // Return null if no solution is found
            return null;
        }
        catch (Exception e){
            if(e instanceof NullPointerException){
                System.out.println("Invalid domain entered");
            }
            return null;
        }

    }

    // Helper method to construct the solution path
    private Solution constructSolution(AState goalState) {
        Solution solution = new Solution();
        AState currentState = goalState;

        // Backtrack from the goal state to the start state
        while (currentState != null) {
            solution.getSolutionPath().add(currentState);
            currentState = currentState.getCameFrom();
        }

        // Reverse the path to start from the initial state
        solution.reversePath();
        return solution;
    }


    @Override
    public String getName() {
        return "DepthFirstSearch";
    }

}
