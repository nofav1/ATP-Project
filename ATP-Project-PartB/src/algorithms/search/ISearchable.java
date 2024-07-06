package algorithms.search;

import java.util.ArrayList;
import java.util.Map;

public interface ISearchable {
    public AState getStartState();
    public AState getGoalState();
    public ArrayList<AState> getAllSuccessors(AState s, Map<Integer[],Integer> vectorsCost);
}
