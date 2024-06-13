package algorithms.search;

public interface ISearchingAlgorithm {
    public Solution solve(ISearchable domain);
    public String getName();
    public String getNumberOfNodesEvaluated();
}
