
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.lang.*;

public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts){
        for (String word : resource.words()){
            int wordLength = 0;
            for (int k = 0; k < word.length(); k++){
                char c = word.charAt(k);
                if (k == 0 || k == word.length()-1){
                    if (Character.isLetter(c))
                        wordLength++;
                }
                else
                    wordLength++;
            }
            counts[wordLength]++;
        }
        System.out.println("Most commmon word size is: " + indexOfMax(counts));
        System.out.println();
    }
    public void testCountWordLengths(){
        FileResource file = new FileResource("errors.txt");
        int[] counts = new int[31];
        countWordLengths(file, counts);
        for (int i = 0; i < counts.length; i++){
            if (counts[i] != 0)
                System.out.println(counts[i] + " words of length " + i + ".");
        }
        
    }
    public int indexOfMax(int[] values){
        int max = 0;
        for (int k = 0; k < values.length; k++){
            if (values[k] > max) 
                max = values[k];
        }
        return max;        
    }
}
