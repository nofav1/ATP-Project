package algorithms.search;

import java.util.*;

public class BestFirstSearch extends BreadthFirstSearch{
    public BestFirstSearch() {
        vectorsCost = new LinkedHashMap<>() {{
            put(new Integer[]{-1, 0}, 10);// up
            put(new Integer[]{-1, 1}, 15);// upper-right diagonal
            put(new Integer[]{0, 1}, 10);// right
            put(new Integer[]{1, 1}, 15);// lower-right diagonal
            put(new Integer[]{1, 0}, 10); // down
            put(new Integer[]{1, -1}, 15); // lower-left diagonal
            put(new Integer[]{0, -1}, 10); // left
            put(new Integer[]{-1, -1}, 15); // upper-left diagonal
        }};
    }
    @Override
    public String getName() {
        return "BestFirstSearch";
    }

}
