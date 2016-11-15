
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
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> foo) {
         int max = 0;
         for (Integer x : foo.values()) {
             if (x > max)
                max = x;
         }
         
         return max;
     } 
          
     public HashMap<String, Integer> countVisitsPerIP() {
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         
         for (LogEntry le : records) {
             String IP = le.getIpAddress();
             if (!counts.containsKey(IP)) {
                 counts.put(IP, 1);
             }
             else {
                 counts.put(IP, counts.get(IP) + 1);
             }
         }
         return counts;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> foo) {
         int max = 0;
         ArrayList<String> temp = new ArrayList<String>();
         for (String x : foo.keySet()){
             int num = foo.get(x);
             if (num >= max){
                max = num;
            }
         }
         for (String x : foo.keySet()){
             int num = foo.get(x);
             if (num == max)
                temp.add(x);
         }
         return temp;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays() {
         HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
         
         for (LogEntry le : records){
             String day = le.getAccessTime().toString().substring(4,10);
             String ip = le.getIpAddress();
             ArrayList<String> iPs = new ArrayList<String>();
 
             if (!map.containsKey(day)){
                 iPs.add(ip);
                 map.put(day, iPs);
             }
             else {
                 iPs = map.get(day);
                 iPs.add(ip);
                 map.put(day, iPs);
             } 
         }
         return map;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> foo) {
         String mostVis = "";
         int max = 0;
         
         for (String x : foo.keySet()) {
             int sz = foo.get(x).size();
             if (sz > max) {
                 max = sz;
                 mostVis = x;             
             }            
         }
         return mostVis;         
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> foo, String day) {
         ArrayList<String> asd = new ArrayList<String>();                  

         for (String x : foo.keySet()) {
             if (x.equals(day)) {
                 ArrayList<String> temp = new ArrayList<String>();
                 HashMap<String, Integer> tempMap = new HashMap<String, Integer>();
                 temp = foo.get(x);
                 
                 for (String ipuri : temp) {
                     if (!tempMap.containsKey(ipuri)) {
                         tempMap.put(ipuri, 1);
                     }
                     else {
                         tempMap.put(ipuri, tempMap.get(ipuri) + 1);
                     }
                 }
                 asd = iPsMostVisits(tempMap);
             }
         }
         
         return asd;
     }
}
