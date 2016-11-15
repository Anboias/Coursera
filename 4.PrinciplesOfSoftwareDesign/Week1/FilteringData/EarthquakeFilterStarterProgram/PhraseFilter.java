
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter {
    private String where;
    private String what;
      
    public PhraseFilter (String where, String what) {
        this.where = where;
        this.what = what;
    }
    
    public boolean satisfies (QuakeEntry qe) {
        return (where.equals("start") && qe.getInfo().startsWith(what) ||
            where.equals("end") && qe.getInfo().endsWith(what) ||
            where.equals("any") && qe.getInfo().contains(what));            
    }
    
    public String getName() {
        return "Phrase";
    }    
}
