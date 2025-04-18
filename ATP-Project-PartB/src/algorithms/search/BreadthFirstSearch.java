package algorithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm{
    protected PriorityQueue<AState> openedList;

    public BreadthFirstSearch() {
        openedList = new PriorityQueue<>(Comparator.comparingInt(AState::getCost));
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

    public AState popOpenList(){
        visitedNodes++;
        return openedList.poll();
    }

    @Override
    public Solution solve(ISearchable domain) {
        try {
            AState startState = domain.getStartState();
            AState goalState = domain.getGoalState();

            Map<AState, Integer> costMap = new HashMap<>();

            startState.setCost(0);
            openedList.add(startState);
            costMap.put(startState, 0);

            while (!openedList.isEmpty()) {
                AState currentState = popOpenList(); //evaluate++
                visited.add(currentState);

                if (currentState.equals(goalState)) {
                    return reconstructSolution(currentState);
                }

                ArrayList<AState> successors = domain.getAllSuccessors(currentState, vectorsCost);

                for (AState successor : successors) {
                    if (!visited.contains(successor)) {
                        int newCost = currentState.getCost() + successor.getCost();
                        if (!costMap.containsKey(successor) || newCost < currentState.getCost()) {
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
        catch (Exception e){
            if(e instanceof NullPointerException){
                System.out.println("Invalid domain entered");
            }
            return new Solution(); // No path
        }
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
        return "BreadthFirstSearch";
    }

}
