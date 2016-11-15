
/**
 * Find out how many times each word occurs, and
 * in particular the most frequently occurring word.
 * 
 * @author Duke Software Team
 * @version 1.0
 */
import edu.duke.*;
import java.util.ArrayList;

public class WordFrequencies
{
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    private int indexMax;

    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource resource = new FileResource();
        
        for(String s : resource.words()){
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1){
                myWords.add(s);
                myFreqs.add(1);
            }
            else {
                int freq = myFreqs.get(index);
                myFreqs.set(index,freq+1);
            }
        }
    }
    public void findCharNames() {
        myWords.clear();
        myFreqs.clear();
        FileResource resource = new FileResource();
        
        for (String s : resource.lines()){
            if (s.contains(".")){
                s = s.substring(0, s.indexOf("."));
                if (isAllUpper(s)) {
                    int index = myWords.indexOf(s);
                    if (index == -1){
                        myWords.add(s);
                        myFreqs.add(1);
                    }
                    else {
                        int freq = myFreqs.get(index);
                        myFreqs.set(index, freq + 1);
                    }
                }
           }
        }
    }
    
    public void testFindCharNames(int refNum1, int refNum2) {
        findCharNames();
        printListCharNames(refNum1, refNum2);
    }
    
    private static boolean isAllUpper(String s) {
        for (char c : s.toCharArray()) {
            if(Character.isLetter(c) && Character.isLowerCase(c)) {
                return false;
            }
        }
        return true;
    }
    public void printListCharNames(int refNum1, int refNum2) {
        for (int k = 0; k < myWords.size(); k++){
            if (myFreqs.get(k) >= refNum1 && myFreqs.get(k) <= refNum2)
                System.out.printf("%3s: %-18s %d\n", k, myWords.get(k), myFreqs.get(k)); 
        }
    }
    
    public void tester(){
        //findUnique
        findUnique();
        System.out.println("# unique words: "+myWords.size());
        
        //findIndexOfMax
        int indexMax = findIndexOfMax();
        System.out.println("Max word is " + myWords.get(indexMax) + " at index " + indexMax + " and it occurs # " + myFreqs.get(indexMax));
        
        //findMax
        int index = findMax();       
        System.out.println("max word/freq: "+myWords.get(index)+" "+myFreqs.get(index));
    }
  
    public int findMax(){
        int max = myFreqs.get(0);
        for(int k=0; k < myFreqs.size(); k++){
            if (myFreqs.get(k) > max){
                max = myFreqs.get(k);
                indexMax = k;
            }
        }
        return max;
    }
    public int findIndexOfMax() {
        findMax();
        return indexMax;
    }
}
