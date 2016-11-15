

import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = filterByMagnitude(parser.read(source), 5.0);
        System.out.println("read data for "+list.size()+" quakes");
        for (QuakeEntry qe : list) {
            System.out.println(qe.toString());
        }

    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();

        for (QuakeEntry qe : quakeData) {
            double dist = from.distanceTo(qe.getLocation());
            if (dist < distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
    double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry qe : quakeData) {
            if (qe.getDepth() > minDepth && qe.getDepth() < maxDepth) {
                answer.add(qe);
            }
        }      
        return answer;
    }
    
    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        
        System.out.println("read data for "+list.size()+" quakes");
        
        list = filterByDepth(list, -4000.0, -2000.0);
        for (QuakeEntry qe : list) {
           // System.out.println(qe.toString());
        }
        System.out.println(list.size());
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,
    String where, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry qe : quakeData) {
            if (where.equals("start") && qe.getInfo().startsWith(phrase) ||
                where.equals("end") && qe.getInfo().endsWith(phrase) ||
                where.equals("any") && qe.getInfo().contains(phrase)) {
                answer.add(qe);
            }
        }      
        return answer;
    }    
    
    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        
        System.out.println("read data for "+list.size()+" quakes");
        
        String phrase = "Can";
        String where = "any";
        list = filterByPhrase(list, where, phrase);
        for (QuakeEntry qe : list) {
            System.out.println(qe.toString());
        }
        System.out.println("Found "+list.size()+" that match "+phrase+" at "+where);
    }
    
    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
                
        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);
        
        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);
        

        list = filterByDistanceFrom(list, 1000000.00, city);       
        for (QuakeEntry qe : list) {
            double dist = city.distanceTo(qe.getLocation());
            System.out.println(dist/1000+" "+qe.getInfo());
        }
        
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        //  dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
