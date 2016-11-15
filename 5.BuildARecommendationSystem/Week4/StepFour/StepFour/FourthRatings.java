
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.*;

public class FourthRatings { 
    public ArrayList<Rating> getAverageRatings(int minimalRaters) throws IOException  {
        ArrayList<Rating> list = new ArrayList<Rating>();
        ArrayList<String> movieIds = MovieDatabase.filterBy(new TrueFilter());
        
        for (String s : movieIds) {
            double tempD = getAverageByID(s, minimalRaters);
            if (tempD != 0.0) {
                Rating tempR = new Rating(s, tempD);
                list.add(tempR);
            }
        }
        return list;
    }
    
    private double getAverageByID(String id, int minimalRateres) {
        double total = 0.0;
        int counter = 0;
               
        for (Rater rt : RaterDatabase.getRaters()) {
            if (rt.hasRating(id)) {
                total += rt.getRating(id);
                counter++;
            }
        }
        if (counter < minimalRateres)
            return 0.0;
        return total/(double)counter;
    }   
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) throws IOException {
        ArrayList<Rating> list = new ArrayList<Rating>();
        ArrayList<String> movieIds = MovieDatabase.filterBy(filterCriteria);
        
        for (String s : movieIds) {
            double tempD = getAverageByID(s, minimalRaters);
            if (tempD != 0.0) {
                Rating tempR = new Rating(s, tempD);
                list.add(tempR);
            }
        }        
        return list;
    }
    
    private double dotProduct(Rater me, Rater r) {      
        ArrayList<String> meRated = me.getItemsRated();        
        double prod = 0.0;
        
        for (String str : meRated) {
            if (r.hasRating(str)) {
                prod += (me.getRating(str) - 5) * (r.getRating(str) - 5);
            }
        }       
        return prod;
    }
    
    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> list = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        for (Rater r : RaterDatabase.getRaters()) {
            if (!r.equals(me)) {
                double temp = dotProduct(me, r);
                if (temp > 0.0) {
                    list.add(new Rating(r.getID(), temp));
                }
            }
       }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }
    
    public ArrayList<Rating> getSimilarRatings (
                String id, int numSimilarRaters, int minimalRaters) throws IOException {
       return getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, new TrueFilter());
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter (
                String id, int numSimilarRaters, int minimalRaters, 
                Filter filterCriteria) throws IOException {
        ArrayList<Rating> list = new ArrayList<Rating>();
        ArrayList<Rating> sim = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
               
        for (String movieId : movies) {
            double avg = 0.0;
            double sum = 0.0;
            int counter = 0;
            for (int k = 0; k < numSimilarRaters; k++) {
                Rating r = sim.get(k);
                double temp = r.getValue();
                String rId = r.getItem();
                Rater tRater = RaterDatabase.getRater(rId);
                if (tRater.hasRating(movieId)) {
                    counter++;
                    sum += temp * tRater.getRating(movieId);
                }
            }
            if (counter >= minimalRaters) {
                avg = sum/counter;
                list.add(new Rating(movieId, avg));
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;        
    }
}
