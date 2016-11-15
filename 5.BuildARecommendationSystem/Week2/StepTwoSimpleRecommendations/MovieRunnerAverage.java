
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import java.util.*;
import java.lang.*;

public class MovieRunnerAverage {
    private int minimalRaters;
    
    public MovieRunnerAverage(int min) {
        minimalRaters = min;
    }
    
    public void printAverageRatings() throws IOException {
        SecondRatings obj = new SecondRatings();
        int mvSize = obj.getMovieSize();
        int rtSize = obj.getRaterSize();
        //System.out.println("movie size: "+mvSize+" | rater size: "+rtSize);
        ArrayList<Rating> rtm = obj.getAverageRatings(minimalRaters);       
        Collections.sort(rtm);
        
        for (Rating r : rtm) {
            System.out.println(r.getValue() + "    " + obj.getTitle(r.getItem()));
        }
    }
    
    public void getAverageRatingOneMovie() throws IOException {
        SecondRatings obj = new SecondRatings();
        int mvSize = obj.getMovieSize();
        int rtSize = obj.getRaterSize();
        
        ArrayList<Rating> rtm = obj.getAverageRatings(minimalRaters);        
        
        for (Rating r : rtm) {
            if (obj.getTitle(r.getItem()).equals("The Maze Runner"))
                System.out.println(r.getValue() + "    " + obj.getTitle(r.getItem()));
        }        
        
    }
}
