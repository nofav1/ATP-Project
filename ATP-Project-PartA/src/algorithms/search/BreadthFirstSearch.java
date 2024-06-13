package algorithms.search;

public class BreadthFirstSearch extends ASearchingAlgorithm{
    @Override
    public Solution solve(ISearchable domain) {
        domain.search();
        return null;
    }

    @Override
    public String getName() {
        return "BreadthFirstSearch";
    }

}
