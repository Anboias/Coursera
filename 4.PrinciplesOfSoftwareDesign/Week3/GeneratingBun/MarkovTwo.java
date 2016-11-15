
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.Random;
import java.util.*;

public class MarkovTwo {
    private String myText;
    private Random myRandom;
    
    public MarkovTwo() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - 2);
        String key = myText.substring(index, index + 2);
        sb.append(key);
        
        for (int k = 0; k < numChars-2; k++) {
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }     
        return sb.toString();
    }
    
    public ArrayList<String> getFollows(String key) {
        ArrayList<String> answer = new ArrayList<String>();
        int pos = 0;
        
        while (true) {
            int index = myText.indexOf(key, pos);
            if (index == -1 || index >= myText.length() - key.length() - 1)
                break;
            String ch = myText.substring(index + key.length(), index + key.length()+1);
            pos = index + key.length();
            answer.add(ch);
        }
        return answer;
    }
}
