
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
}
