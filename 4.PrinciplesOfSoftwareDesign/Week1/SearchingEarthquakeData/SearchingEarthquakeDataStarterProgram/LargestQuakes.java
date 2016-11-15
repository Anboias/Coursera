
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class LargestQuakes {
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        
        System.out.println("read data for "+list.size()+" quakes");
        int index = indexOfLargest(list);
        System.out.println("Largest data index: "+index);
        list = getLargest(list, 50);
        for (QuakeEntry qe : list) {
            System.out.println(qe.toString());
        }
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        double max = 0;
        int index = 0;
        int indexBiggest = 0;
        
        for (QuakeEntry qe : data) {
            index++;
            double temp = qe.getMagnitude();
            if (temp > max) {
                max = temp;
                indexBiggest = index;
            }
        }
        return indexBiggest-1;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for (int i = 0; i < howMany; i++) {
            if (quakeData == null)
                break;
            int ind = indexOfLargest(quakeData);
            answer.add(quakeData.get(ind));
            quakeData.remove(ind);
        }
        
        return answer;
    }
}
