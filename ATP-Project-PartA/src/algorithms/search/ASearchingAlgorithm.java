package algorithms.search;

import java.util.*;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    protected PriorityQueue<AState> openedList;
    protected Set<AState> visited;
    private int visitedNodes; //pop from openedList

    public ASearchingAlgorithm() {
        openedList = new PriorityQueue<>(Comparator.comparingInt(AState::getCost));
        visited = new HashSet<>();
        visitedNodes = 0;
    }

    public AState popOpenList(){
        visitedNodes++;
        return openedList.poll();
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return visitedNodes;
    }
}
