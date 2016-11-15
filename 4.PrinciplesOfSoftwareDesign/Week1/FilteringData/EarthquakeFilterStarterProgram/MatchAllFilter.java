
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MatchAllFilter implements Filter {
    private ArrayList<Filter> filters;
    private HashSet<String> names;
    
    public MatchAllFilter() {
        filters = new ArrayList<Filter>();
        names = new HashSet<String>();
    }
    
    public void addFilter(Filter f) {
        filters.add(f);
    }
    
    public boolean satisfies(QuakeEntry qe) {
        for (Filter f : filters) {
            names.add(f.getName());
            if (!f.satisfies(qe)) {
                return false;
            }
        }
        return true;
    }
    
    public String getName() {
        return names.toString();
    }
}
