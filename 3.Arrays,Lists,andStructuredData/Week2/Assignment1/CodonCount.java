
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class CodonCount {
    private HashMap<String, Integer> map;
    private int start;
    private int end;
    
    CodonCount(int start, int end) {
        map = new HashMap<String, Integer>();
        this.start = start;
        this.end = end;
    }
    
    public void buildCodonMap(int start, String dna) {
        map = new HashMap<String, Integer>();
        for (int i = start; i < dna.length()-2; i+=3) {
            String w = dna.substring(i, i+3);
            
            if (!map.containsKey(w))
                map.put(w, 1);
            else
                map.put(w, map.get(w) + 1);
        }
    }
    
    public String getMostCommonCodon() {
        int maxValue = 0;
        for (Integer v : map.values()){
            if (v > maxValue)
                maxValue = v;
        }
        for (String s : map.keySet()){
            if (map.get(s) == maxValue){
                return s;
            }
        }
        return "";
    }
    
    public void printCodonCounts() {
        for (String s : map.keySet()) {
            if (map.get(s) >= start && map.get(s) <= end)
                System.out.printf("%-5s %-3d\n", s, map.get(s));
        }
    }
    public void tester() {
        FileResource fr = new FileResource();
        String dna = fr.asString().toUpperCase().trim();
        for (int i = 0; i < 3; i++){
            buildCodonMap(i, dna);
            System.out.println("Reading frame starting with "+i+" results in "+map.size()+" unique condons");
            String mostCommon = getMostCommonCodon();
            System.out.println("and most common codon is "+mostCommon+" with count "+map.get(mostCommon));
            System.out.println("Counts of codons between "+start+" and "+end+" inclusive are:");
            printCodonCounts();
            System.out.printf("\n\n\n");
        }
            
        
    }
}
