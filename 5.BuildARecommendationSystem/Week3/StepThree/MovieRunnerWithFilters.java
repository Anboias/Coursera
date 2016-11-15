
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import java.util.*;

public class MovieRunnerWithFilters {
    private int minimalRaters;
    
    public MovieRunnerWithFilters(int min) {
        minimalRaters = min;
    }
    
    public void printAverageRatings() throws IOException {
        ThirdRatings obj = new ThirdRatings();
        int rtSize = obj.getRaterSize();
        System.out.println("rater size: "+rtSize);
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("# of movies in dB = "+ (MovieDatabase.size()-1));

        ArrayList<Rating> rtm = obj.getAverageRatings(minimalRaters);       
        Collections.sort(rtm);
        System.out.println("# of movies printed: " + rtm.size());
        for (Rating r : rtm) {
            System.out.println(r.getValue() + "    " + MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printAverageRatingsByYear() throws IOException {
        ThirdRatings obj = new ThirdRatings();
        int rtSize = obj.getRaterSize();
        int year = 2000;
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        Filter yaf = new YearAfterFilter(year);

        ArrayList<Rating> rtm = obj.getAverageRatingsByFilter(minimalRaters, yaf);       
        Collections.sort(rtm);
        
        System.out.println("read data for "+rtSize+ " raters");        
        System.out.println("read data for "+ (MovieDatabase.size())+" movies");   
        
        System.out.println("found "+ rtm.size() +" movies");
        for (Rating r : rtm) {
            System.out.println(r.getValue() + "  " + MovieDatabase.getYear(r.getItem()) + "  " + MovieDatabase.getTitle(r.getItem()));
        }    
        
    }
    
    public void printAverageRatingsByGenre() throws IOException {
        ThirdRatings obj = new ThirdRatings();
        int rtSize = obj.getRaterSize();
        String genre = "Comedy";
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        Filter yaf = new GenreFilter(genre);

        ArrayList<Rating> rtm = obj.getAverageRatingsByFilter(minimalRaters, yaf);       
        Collections.sort(rtm);
        
        System.out.println("read data for "+rtSize+ " raters");        
        System.out.println("read data for "+ (MovieDatabase.size())+" movies");   
        
        System.out.println("found "+ rtm.size() +" movies");
        for (Rating r : rtm) {
            System.out.println(r.getValue() + "  " + MovieDatabase.getTitle(r.getItem())
                                    + " [ " +  MovieDatabase.getGenres(r.getItem()) + " ]");
        }    
        
    }  
    
    public void printAverageRatingsByMinutes() throws IOException {
        ThirdRatings obj = new ThirdRatings();
        int rtSize = obj.getRaterSize();
        int min = 105;
        int max = 135;
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        Filter yaf = new MinutesFilter(min, max);

        ArrayList<Rating> rtm = obj.getAverageRatingsByFilter(minimalRaters, yaf);       
        Collections.sort(rtm);
        
        System.out.println("read data for "+rtSize+ " raters");        
        System.out.println("read data for "+ (MovieDatabase.size())+" movies");   
        
        System.out.println("found "+ rtm.size() +" movies");
        for (Rating r : rtm) {
            System.out.println(r.getValue() + " "+ " Time: "+ MovieDatabase.getMinutes(r.getItem())  + " " + MovieDatabase.getTitle(r.getItem()));
        }    
        
    }    
    
    public void printAverageRatingsByDirectors() throws IOException {
        ThirdRatings obj = new ThirdRatings();
        int rtSize = obj.getRaterSize();
        String directors = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        Filter yaf = new DirectorsFilter(directors);

        ArrayList<Rating> rtm = obj.getAverageRatingsByFilter(minimalRaters, yaf);       
        Collections.sort(rtm);
        
        System.out.println("read data for "+rtSize+ " raters");        
        System.out.println("read data for "+ (MovieDatabase.size())+" movies");   
        
        System.out.println("found "+ rtm.size() +" movies");
        for (Rating r : rtm) {
            System.out.println(r.getValue() + "  "+ MovieDatabase.getTitle(r.getItem()) +
                                " " + MovieDatabase.getDirector(r.getItem()));
        }    
        
    }    
    
    public void printAverageRatingsByYearAfterAndGenre() throws IOException {
        ThirdRatings obj = new ThirdRatings();
        int rtSize = obj.getRaterSize();
        
        int year = 1990;
        String genre = "Drama";
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        AllFilters yaf = new AllFilters();
        Filter fy = new YearAfterFilter(year);
        Filter fg = new GenreFilter(genre);      
        yaf.addFilter(fy);
        yaf.addFilter(fg);
        
        ArrayList<Rating> rtm = obj.getAverageRatingsByFilter(minimalRaters, yaf);    
        Collections.sort(rtm);
        
        System.out.println("read data for "+rtSize+ " raters");        
        System.out.println("read data for "+ (MovieDatabase.size())+" movies");   
        
        System.out.println("found "+ rtm.size() +" movies");
        for (Rating r : rtm) {
            System.out.println(r.getValue() + "  "+ MovieDatabase.getTitle(r.getItem()) +
                              " [ " +  MovieDatabase.getGenres(r.getItem()) + " ]");
        }    
    }
    
    public void printAverageRatingsByDirectorsAndMinutes() throws IOException {
        ThirdRatings obj = new ThirdRatings();
        int rtSize = obj.getRaterSize();
        
        String directors = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
        int min = 90;
        int max = 180;
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        AllFilters yaf = new AllFilters();
        Filter fy = new DirectorsFilter(directors);
        Filter fg = new MinutesFilter(min, max);      
        yaf.addFilter(fy);
        yaf.addFilter(fg);
        
        ArrayList<Rating> rtm = obj.getAverageRatingsByFilter(minimalRaters, yaf);    
        Collections.sort(rtm);
        
        System.out.println("read data for "+rtSize+ " raters");        
        System.out.println("read data for "+ (MovieDatabase.size())+" movies");   
        
        System.out.println("found "+ rtm.size() +" movies");
        for (Rating r : rtm) {
            System.out.println(r.getValue() + "  "+ " Time: "+ MovieDatabase.getMinutes(r.getItem())  +
                              " [ " + MovieDatabase.getDirector(r.getItem()));
        }    
    }
}
