
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;
import java.io.*;

public class Tester
{
    HashMap<String, Integer> map;
    HashMap<String, ArrayList<String>> mapz;
    
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        
        la.readFile("short-test_log");
        la.printAll();
    }
    
    public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            String name = f.getName();
            la.readFile(name);
            System.out.println("#ofUniqueIPs in "+ name +" is: " + la.countUniqueIPs());
        }
    }
    
    public void testAllHigherThanNum(int num) {
        LogAnalyzer la = new LogAnalyzer();
        
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            String name = f.getName();
            la.readFile(name);
            System.out.println("Those with higher # than "+ num +" in file "+name+ "are: ");
            la.printAllHigherThanNum(num);
        }
    }
    
    public void testIPsInRange(int low, int high) {
        LogAnalyzer la = new LogAnalyzer();
        
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            String name = f.getName();
            la.readFile(name);
            System.out.println("uniqueIPsInRange in file "+name+ "are: "+la.countUniqueIPsInRange(low, high));
            
        }
        
    }
       
    public void testUniqueIPVisitsOnDay(String someday) {
        LogAnalyzer la = new LogAnalyzer();
        
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            String name = f.getName();
            la.readFile(name);
            System.out.println("Unique visits on "+ someday +" in file "+name+ "are: ");
            ArrayList<LogEntry> logE = la.uniqueIPVisitsOnDay(someday);
            for (LogEntry e : logE)
                System.out.println("\t-"+e.toString());
            System.out.println("Size of arrayList returned: "+logE.size());
        }
    }
    
    public void testCountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        int size = 0;
        int max = 0;
        String temp = "";
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            String name = f.getName();
            la.readFile(name);
            map = la.countVisitsPerIP();  
            System.out.printf("IP               Visits\n");
            for (String e : map.keySet()){
                System.out.printf("%-20s %-4d\n",e, map.get(e));
                if (map.get(e) > max)
                    max = map.get(e);
            }
            size = map.size();
        }
       // return size;
    }
    
    public void testMostNumberVisitsByIp() {
        LogAnalyzer obj = new LogAnalyzer();
        testCountVisitsPerIP();
        
        int mostN = obj.mostNumberVisitsByIP(map);
        System.out.println("Most # per IP are: " + mostN);
    }
    
    public void testIpsMostVisits() {
        LogAnalyzer obj = new LogAnalyzer();
        testCountVisitsPerIP();
        
        ArrayList<String> newList = obj.iPsMostVisits(map);
        System.out.println("iPsMostVisits: " + newList);
    }
    
    public void countUniqueIPs() {
        testCountVisitsPerIP();
        
        System.out.println("# of unique IPs is : " + map.size());
    }
    
    public void testIPsforDays() {
        LogAnalyzer la = new LogAnalyzer();
        
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            String name = f.getName();
            la.readFile(name);
            
            mapz = la.iPsForDays();
            
            for (String s : mapz.keySet()){
                System.out.println("iPsForDays: " + s + " -- " + mapz.get(s));
            }
            System.out.printf("\n-----------------------------------------------------------\n");
            
        }
        
    }
    public void testDayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer();
        testIPsforDays();
        
        System.out.println("dayWithMostIPVisits: "+ la.dayWithMostIPVisits(mapz));
    
    }    
    
    public void testIPsWithMostVisitsOnDay(String day) {
        LogAnalyzer la = new LogAnalyzer();
        testIPsforDays();
        
        System.out.println("iPsWithMostVisitsOnDay: "+ la.iPsWithMostVisitsOnDay(mapz, day));
    
    }
}
