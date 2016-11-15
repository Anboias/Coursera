
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import java.util.*;

public class MovieRunnerSimilarRatings {
    private int minimalRaters;
    
    public MovieRunnerSimilarRatings(int min) {
        minimalRaters = min;
    }    
    
    public void printAverageRatings() throws IOException {
        FourthRatings obj = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<Rating> rtm = obj.getAverageRatings(minimalRaters);       
        Collections.sort(rtm);
        int rtSize = RaterDatabase.size();
        
        System.out.println("rater size: "+rtSize);
        System.out.println("# of movies in dB = "+ (MovieDatabase.size()-1));
        System.out.println("# of movies printed: " + rtm.size());
        for (Rating r : rtm) {
            System.out.println(r.getValue() + "    " + MovieDatabase.getTitle(r.getItem()));
        }
    }

    public void printSimilarRatings() throws IOException {
        FourthRatings obj = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv"); 
        
        String id = "71";
        int topSimilarRaters = 20;
        
        ArrayList<Rating> rtm = obj.getSimilarRatings(id, topSimilarRaters, minimalRaters);
        
        System.out.println("read data for "+ RaterDatabase.size() + " raters");        
        System.out.println("read data for "+ MovieDatabase.size() + " movies");           
        //System.out.println("found "+ rtm.size() +" movies");
        for (Rating r : rtm) {
            System.out.println(r.getValue() + "  " + MovieDatabase.getYear(r.getItem()) + "  " + MovieDatabase.getTitle(r.getItem()));
        }          
    }
    
    public void printAverageRatingsByYear() throws IOException {
        FourthRatings obj = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        int year = 2000;        
        Filter yaf = new YearAfterFilter(year);       
        ArrayList<Rating> rtm = obj.getAverageRatingsByFilter(minimalRaters, yaf);       
        Collections.sort(rtm); 
        
        int rtSize = RaterDatabase.size();
        
        System.out.println("read data for "+rtSize+ " raters");        
        System.out.println("read data for "+ (MovieDatabase.size())+" movies");          
        System.out.println("found "+ rtm.size() +" movies");
        for (Rating r : rtm) {
            System.out.println(r.getValue() + "  " + MovieDatabase.getYear(r.getItem()) + "  " + MovieDatabase.getTitle(r.getItem()));
        }           
    }
        
    public void printSimilarRatingsByGenre() throws IOException {
        FourthRatings obj = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");   
        
        String id = "964";
        int topSimilarRaters = 20;
        
        AllFilters all = new AllFilters();
        String genre = "Mystery";       
        GenreFilter yaf = new GenreFilter(genre);  
        all.addFilter(yaf);
        
        ArrayList<Rating> rtm = obj.getSimilarRatingsByFilter(id, topSimilarRaters, minimalRaters, yaf);
        
        System.out.println("read data for "+ RaterDatabase.size() + " raters");        
        System.out.println("read data for "+ MovieDatabase.size() + " movies");          
        System.out.println("found "+ rtm.size() +" movies");
        
        for (Rating r : rtm) {
            System.out.println(MovieDatabase.getTitle(r.getItem()));
        }          
    }
    
    public void printSimilarRatingsByDirector() throws IOException {
        FourthRatings obj = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");   
        
        String id = "120";
        int topSimilarRaters = 10;
        
        AllFilters all = new AllFilters();
        String dir = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";       
        DirectorsFilter yaf = new DirectorsFilter(dir);  
        all.addFilter(yaf);
        
        ArrayList<Rating> rtm = obj.getSimilarRatingsByFilter(id, topSimilarRaters, minimalRaters, yaf);
        
        System.out.println("read data for "+ RaterDatabase.size() + " raters");        
        System.out.println("read data for "+ MovieDatabase.size() + " movies");          
        System.out.println("found "+ rtm.size() +" movies");
        
        for (Rating r : rtm) {
            System.out.println(MovieDatabase.getTitle(r.getItem()));
        }          
    }
    
    public void printSimilarRatingsByGenreAndMinutes() throws IOException {
        FourthRatings obj = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");   
        
        String id = "168";
        int topSimilarRaters = 10;
              
        AllFilters all = new AllFilters();
        String genre = "Drama";   
        int min = 80;
        int max = 160;
        GenreFilter yaf = new GenreFilter(genre);
        MinutesFilter maf = new MinutesFilter(min, max);
        all.addFilter(yaf);
        all.addFilter(maf);
        
        ArrayList<Rating> rtm = obj.getSimilarRatingsByFilter(id, topSimilarRaters, minimalRaters, all);
        
        
        
        System.out.println("read data for "+ RaterDatabase.size() + " raters");        
        System.out.println("read data for "+ MovieDatabase.size() + " movies");          
        System.out.println("found "+ rtm.size() +" movies");
        
        for (Rating r : rtm) {
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " " + MovieDatabase.getMinutes(r.getItem())
                            + " " + r.getValue() + " " +MovieDatabase.getGenres(r.getItem()));
        }          
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes() throws IOException {
        FourthRatings obj = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");   
        
        String id = "314";
        int topSimilarRaters = 10;
              
        AllFilters all = new AllFilters();
        int year = 1975;   
        int min = 70;
        int max = 200;
        YearAfterFilter yaf = new YearAfterFilter(year);
        MinutesFilter maf = new MinutesFilter(min, max);
        all.addFilter(yaf);
        all.addFilter(maf);
        
        ArrayList<Rating> rtm = obj.getSimilarRatingsByFilter(id, topSimilarRaters, minimalRaters, all);
        
        
        
        System.out.println("read data for "+ RaterDatabase.size() + " raters");        
        System.out.println("read data for "+ MovieDatabase.size() + " movies");          
        System.out.println("found "+ rtm.size() +" movies");
        
        for (Rating r : rtm) {
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " " + MovieDatabase.getMinutes(r.getItem())
                            + " " + r.getValue() + " " +MovieDatabase.getGenres(r.getItem()));
        }          
    }
        
}
