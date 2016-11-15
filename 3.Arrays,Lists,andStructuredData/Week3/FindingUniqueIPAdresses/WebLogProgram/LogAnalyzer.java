
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     private ArrayList<LogEntry> uniqueVisits;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
         uniqueVisits = new ArrayList<LogEntry>();
         // complete constructor
     }
        
     public void readFile(String filename) {
         FileResource fn = new FileResource(filename);
         
         for (String line : fn.lines()) {
             records.add(WebLogParser.parseEntry(line));
         }
     }
     
     public void printAllHigherThanNum(int num) {
         int index = 0;
         for (LogEntry le : records) {
             int temp = le.getStatusCode();
             if (temp > num){
                System.out.println("\t-"+le);
                index++;
            }
         }
         System.out.println("There are " + index + " with greater status code than "+num);
         System.out.println();
     }
     
     public int countUniqueIPsInRange(int low, int high) {
         int index = 0;
         ArrayList<String> ipList = new ArrayList<String>();
         
         for (LogEntry le : records) {
             int temp = le.getStatusCode();
             String IP = le.getIpAddress();
             if (temp >= low && temp <= high && !ipList.contains(IP)) {
                 index++;
                 ipList.add(IP);
             }
         }
         return index;
     }
     
     public int countUniqueIPs() {
         ArrayList<String> copy = new ArrayList<String>();
         
         for (LogEntry s : records) {
             String temp = s.getIpAddress();
             if (!copy.contains(temp)) {
                 copy.add(temp);
                 uniqueVisits.add(s);
             }
         }
         return copy.size();
     }
          
     public ArrayList<LogEntry> uniqueIPVisitsOnDay(String someday) {
         ArrayList<LogEntry> uniqIpDay = new ArrayList<LogEntry>();
         ArrayList<String> ipList = new ArrayList<String>();
         
         for (LogEntry le : records) {
             String date = le.getAccessTime().toString();
             String IP = le.getIpAddress();
             if (date.indexOf(someday) != -1 && !ipList.contains(IP)) {
                 uniqIpDay.add(le);
                 ipList.add(IP);
             }
         }
   
         return uniqIpDay;
     }
     
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}
