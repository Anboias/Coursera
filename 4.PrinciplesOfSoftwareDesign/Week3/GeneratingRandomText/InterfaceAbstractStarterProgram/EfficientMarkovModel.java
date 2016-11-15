
/**
 * Write a description of class EfficientMarkovModel here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.Random;
import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private int n;
    HashMap<String, ArrayList<String>> map;
    
    public EfficientMarkovModel(int n) {
        this.n = n;
        myRandom = new Random();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
        
    public void setTraining(String s){
        myText = s.trim();
        map = new HashMap<String, ArrayList<String>>();
        buildMap();
        printHashMapInfo();
    }
    
    
    public String getRandomText(int numChars){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - n);
        String key = myText.substring(index, index + n);
        sb.append(key);
        
        for (int k = 0; k < numChars-n; k++) {  
            ArrayList<String> follows = getFollows(key);
            String next = "";
              
        }     
        return sb.toString();
    }
    
    protected ArrayList<String> getFollows(String key) {        
        for (String s : map.keySet()) {
            if (s.equals(key)) {
                return map.get(s);
            }
        }
        return null;
    }
    
    public void buildMap() {       
        for (int i = 0; i <= myText.length()-n; i++) {
            if (myText == null)
                break;
            String key = myText.substring(i, i + n);          
            if (!map.containsKey(key)) {
                ArrayList<String> answer = new ArrayList<String>();
                int pos = 1;
                while (true) {
                    int index = myText.indexOf(key, pos);
                    if (index == -1 || index > myText.length() - key.length() - 1)
                        break;
                    String ch = myText.substring(index + key.length(), index + key.length()+1);
                    pos = index + 1;
                    answer.add(ch);
                }
                map.put(key, answer);
            }
        }
    }
    
    public void printHashMapInfo() {
        StringBuilder sb = new StringBuilder();
        int max = 0;
        int i = 0;
        for (String s : map.keySet()) {
            if (map.get(s).size() > max) {
                max = map.get(s).size();
            }
        }
        for (String s : map.keySet()) {
            if (map.get(s).size() == max)
                sb.append(s + " ");
        }        
        System.out.println();
        System.out.println("# of keys: "+map.size());
        System.out.println("Largest value`s size: "+max);
        System.out.println("keys with max value: " + sb.toString());
        System.out.println("myText size = "+myText.length());
    }
}
