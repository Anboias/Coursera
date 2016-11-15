
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter {
    private Location where;
    private double max;
    
    public DistanceFilter (Location where, double max) {
        this.where = where;
        this.max = max;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        return (qe.getLocation().distanceTo(where) <= max);
    }
    
    public String getName() {
        return "Distance";
    }    
}
