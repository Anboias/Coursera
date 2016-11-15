import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
import java.io.*;

/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename) throws IOException {
        Reader fr = new FileReader(filename);
        ArrayList<Movie> list = new ArrayList<Movie>();
        
        Iterable<CSVRecord> records = CSVFormat.EXCEL.withDelimiter(',').withHeader(
        "id", "title", "year", "country", "genre", "director", "minutes", "poster").parse(fr);
        
        for (CSVRecord record : records) {
            int min = 0;
            try {
                min = Integer.parseInt(record.get("minutes"));
            }
            catch  (NumberFormatException nfe) {}
            
            Movie temp = new Movie(record.get("id"), record.get("title"), 
                            record.get("year"), record.get("genre"),
                            record.get("director"), record.get("country"),
                            record.get("poster"), min);
            if (temp.getYear() != 0)
                list.add(temp);
        }
        return list;
    }
    
    public ArrayList<Rater> loadRaters(String filename) throws IOException {
        Reader fr = new FileReader(filename);
        ArrayList<Rater> list = new ArrayList<Rater>();
        Iterable<CSVRecord> records = CSVFormat.EXCEL.withDelimiter(',').withHeader(
                                      "rater_id","movie_id","rating","time").parse(fr);
        int z = 0;
        for (CSVRecord record : records) {
            Rater tempRater = new EfficientRater(record.get("rater_id"));
            int sw = 0;
            double d = 0.0;
            try {
                d = Double.parseDouble(record.get("rating"));
            } catch (NumberFormatException nfs) {}    
            if (record.get("time").length() > 4) {
                for (Rater rt : list) {
                    if (rt.getID().equals(record.get("rater_id"))) {
                        rt.addRating(record.get("movie_id"), d);
                        sw = 1;
                        break;
                    }
                }
                if (sw == 0) {
                    tempRater.addRating(record.get("movie_id"), d);
                    list.add(tempRater);
                }
            }
        }
        return list;
    }
    public void testLoadRaters() throws IOException {
        ArrayList<Rater> local = loadRaters("data/ratings.csv");
        String ID = "193";
        String mov = "1798709";
        int max = 0;
        int count = 0;
        int movCount = 0;
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        
        for (Rater rt : local) {
            if (rt.getID().equals(ID)) {
                System.out.println("# of ratings for "+ ID +" is " + rt.numRatings());
            }
            if (rt.numRatings() > max) max = rt.numRatings();
        }
        System.out.println();
        for (Rater rt : local) {
            if (rt.numRatings() == max) {
                System.out.println("ID: "+rt.getID()+ " --- " +"| # of mv: "+ max);  
                count++;
            }
            ArrayList<String> tList = rt.getItemsRated();
            for (String temp : tList) {
                if (temp.equals(mov)) movCount++;
                if (!map.containsKey(temp)) {
                    map.put(temp, 1);
                }
                else {
                    map.put(temp, map.get(temp) + 1);
                }
            }
        }
        
        
        System.out.println("# of raters with max ratings: " + count);
        System.out.println();
        System.out.println("# of raters:    " +local.size());
        System.out.println("# of raters who rated movie "+mov+" is: " +movCount);
        System.out.println("# of unique movies rated: "+map.size());
        
        //for (Rater rt : local) {
        //    System.out.println("ID of rater: " + rt.getID() + " | # of ratings: "+ rt.numRatings());
         //   for (String st : rt.getItemsRated())
            //    System.out.println("    * " +st + " -- " + rt.getRating(st));
        //}
    }
    
    public void testLoadMovies() throws IOException {
        ArrayList<Movie> local = loadMovies("data/ratedmoviesfull.csv");
        HashMap<String, Integer> map = new HashMap<String, Integer>();        
        int comedyCount = 0;
        int greaterThan150 = 0;
        int max = 0;
        
        for (Movie mv : local) {
            if (mv.getGenres().contains("Comedy"))  comedyCount++;
            if (mv.getMinutes() > 150)              greaterThan150++;
            String dir = mv.getDirector();
            map.put(dir,(!map.containsKey(dir)) ? 1 : map.get(dir)+1);
        }
        for (String s : map.keySet()) {
            int temp = map.get(s);
            max = map.get(s) > max ? temp : max;
        }
        System.out.println("# of movies:    "+ local.size());
        System.out.println("# of comedies:  " + comedyCount);
        System.out.println("# of > 150:     " + greaterThan150);
        for (String s : map.keySet()) {
            if (map.get(s) == max)
                System.out.println("# of movies: "+max+" by director " + s);
        }
    }
}
