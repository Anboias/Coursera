
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() throws IOException {
        // default constructor
        this("data/ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) throws IOException {
        FirstRatings obj = new FirstRatings();
        myRaters = obj.loadRaters(ratingsfile);        
    }

    public int getRaterSize() {
        return myRaters.size();
    }
    
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
               
        for (Rater rt : myRaters) {
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
}