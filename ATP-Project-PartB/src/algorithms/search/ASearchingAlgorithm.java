package algorithms.search;

import java.util.*;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    protected Set<AState> visited;
    protected int visitedNodes; //pop from openedList
    protected  Map<Integer[],Integer> vectorsCost;
    public ASearchingAlgorithm() {
        visited = new HashSet<>();
        visitedNodes = 0;
    }



    @Override
    public int getNumberOfNodesEvaluated() {
        return visitedNodes;
    }
}
