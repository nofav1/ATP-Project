package algorithms.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BestFirstSearch extends ASearchingAlgorithm{
    @Override
    public Solution solve(ISearchable domain) {
        AState startState = domain.getStartState();
        AState goalState = domain.getGoalState();

        Map<AState, Integer> costMap = new HashMap<>();
        //Set<AState> visited = new HashSet<>();

        startState.setCost(0);
        openedList.add(startState);
        costMap.put(startState, 0);

        while (!openedList.isEmpty()) {
            AState currentState = popOpenList();
            visited.add(currentState);

            if (currentState.equals(goalState)) {
                return reconstructSolution(currentState);
            }

            ArrayList<AState> successors = domain.getAllSuccessors(currentState);

            for (AState successor : successors) {
                if (!visited.contains(successor)) {
                    int newCost = currentState.getCost() + successor.getCost();
                    if (!costMap.containsKey(successor) || newCost < currentState.getCost()) {
                    //if (newCost < currentState.getCost()) {
                        successor.setCost(newCost);
                        successor.setCameFrom(currentState);
                        costMap.put(successor, newCost);
                        openedList.add(successor);
                    }
                }
            }
        }

        return new Solution(); // No path found
    }

    private Solution reconstructSolution(AState goalState) {
        Solution solution = new Solution();
        AState current = goalState;

        while (current != null) {
            solution.getSolutionPath().add(current);
            current = current.getCameFrom();
        }

        Collections.reverse(solution.getSolutionPath());
        return solution;
    }

    @Override
    public String getName() {
        return "BestFirstSearch";
    }

}
