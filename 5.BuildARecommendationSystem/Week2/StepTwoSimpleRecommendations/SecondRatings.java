
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() throws IOException {
        // default constructor
        this("data/ratedmoviesfull.csv", "data/ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) throws IOException {
        FirstRatings obj = new FirstRatings();
        myMovies = obj.loadMovies(moviefile);
        myRaters = obj.loadRaters(ratingsfile);        
    }
    
    public int getMovieSize() {
        return myMovies.size();
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> list = new ArrayList<Rating>();
        
        for (Movie mvTemp : myMovies) {
            double tempD = getAverageByID(mvTemp.getID(), minimalRaters);
            if (tempD != 0.0) {
                Rating tempR = new Rating(mvTemp.getID(), tempD);
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
        if (counter <= minimalRateres)
            return 0.0;
        return total/(double)counter;
    }   
    
    public String getTitle(String id) {
        for (Movie mv : myMovies) {
            if (mv.getID().equals(id)) {
                return mv.getTitle();
            }
        }
        return "the Id was not found";
    }
    
    public String getID(String title) {
        for (Movie mv : myMovies) {
            if (mv.getTitle().equals(title)){
                System.out.println(mv.getID());
                return mv.getID();
            }
        }
        return "NO SUCH TITLE";
    }
}