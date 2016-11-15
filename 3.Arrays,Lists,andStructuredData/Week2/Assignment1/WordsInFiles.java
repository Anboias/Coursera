
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.*;


public class WordsInFiles {
    private HashMap<String, ArrayList<String>> map;
    private int indexGlobal = 0;
    
    WordsInFiles () {
        map = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile (File f) {
        String fileName = f.getName();
        FileResource fr = new FileResource(f);
        
        for (String w : fr.words()){
            ArrayList<String> list = new ArrayList<String>();
            if (!map.containsKey(w)) {
                list.add(fileName);
                map.put(w, list);
            }
            else {
                list = map.get(w);
                if (!list.contains(fileName)) {
                    list.add(fileName);
                    map.put(w, list);
                }
            }
        }
    }
    
    private void buildWordFileMap() {
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    
    private int maxNumber() {
        int maxNum = 0;
        
        for (String s : map.keySet()) {
            int num = map.get(s).size();
            if (num > maxNum)
                maxNum = num;
        }
        return maxNum;
    }
    
    private ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> wordNames = new ArrayList<String>();
        for (String k : map.keySet()) {
            if (map.get(k).size() == number) {
                wordNames.add(k);
                indexGlobal++;
            }
        }
        return wordNames;
    }
    
    private void printFilesIn (String word) {
        if (map.containsKey(word)){
            System.out.println(map.get(word));
        }
    }
    
    public void tester() {
        buildWordFileMap();
        int maxN = maxNumber();
        ArrayList<String> wordNames = new ArrayList<String>();
        
        wordNames = wordsInNumFiles(maxN);
        
        System.out.println(map.get("tree"));
        
        for (String s : wordNames){
          //  System.out.print(s + " appears in the files: ");
          //  printFilesIn(s);
        }
        //System.out.println(map.get("red"));
        //for (String x : map.keySet())
        //    System.out.println(x + " " + map.get(x) + " " + map.get(x).size());
    }
}
