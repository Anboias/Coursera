
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Random;
import java.util.*;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> map;
    
    public EfficientMarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();
        map = new HashMap<WordGram, ArrayList<String>>();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
        buildMap();
        printHashMapInfo();
        
    }
    //aproape ok
    public void printHashMapInfo () {
        int maxSetSize = -1;
        
        for (WordGram wg : map.keySet()) {
            maxSetSize = Math.max(maxSetSize, map.get(wg).size());
        }
        int i = 0;
        System.out.println();
        System.out.println("# of keys: "+map.keySet().size());
        System.out.println("Largest value`s size: "+maxSetSize);
        for (WordGram wg : map.keySet()) {
         //   System.out.println(i+": "+wg.toString()+" ----- "+ map.get(wg).toString());
            i++;
        }
        System.out.println("keys with max value: " +i);        
        //System.out.println("myText size = "+myText.length());
    }
    
    public void buildMap() {
        for (int i = 0; i <= myText.length-myOrder; i++) {
            WordGram wg = new WordGram(myText, i, myOrder);
            int num = wg.hashCode();
            int onOff = 0;
            
            for (WordGram x : map.keySet()) {
                if (x.hashCode() == num) {
                    onOff = 1;
                }
            }        
            if (onOff == 0){
                ArrayList<String> answer = new ArrayList<String>();
                
                int index = indexOf(myText, wg, 0);
                for (int k = 0; k < myText.length-myOrder; k++) {
                    if (k == index) {
                        answer.add(myText[index+myOrder]);
                        index = indexOf(myText, wg, index+1);
                    }
                }
                map.put(wg, answer);
            }
        }
    }    
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> answer = new ArrayList<String>();

        int index = indexOf(myText, kGram, 0);
        for (int i = 0; i < myText.length-myOrder; i++) {
            if (i == index) {
                answer.add(myText[index+myOrder]);
                index = indexOf(myText, kGram, index+1);
            }
        }
        return answer;
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with   
        WordGram key = new WordGram(myText, index, myOrder);
        
        sb.append(key.toString());
        sb.append(" ");

        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0 || follows == null) break;
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    //ok
    private int indexOf(String[] words, WordGram target, int start) {
        for (int i = start; i < words.length-myOrder; i++) {
            WordGram wg = new WordGram(words, i, myOrder);
            if (wg.equals(target)) 
                return i;
        }
        return -1;
    }
    
}
