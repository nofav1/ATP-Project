package algorithms.search;

import java.util.List;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    protected List<AState> closedList;
    protected List<AState> openedList;

    public String getNumberOfNodesEvaluated() {
        return String.valueOf(closedList.size());
    }
}
