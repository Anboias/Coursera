
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class Tester {
    public void testGetFollows(String t) {
        String st = "this is a test yes this is a test.";
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        ArrayList<String> asd = markov.getFollows(t);
        for(String s : asd){
            System.out.print("\""+s+"\" ");
        }        
        System.out.println();
        System.out.println("Size = " + asd.size());
    }
    
    public void testGetFollowsWithFile(String t) {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);

        ArrayList<String> asd = markov.getFollows(t);
        for(String s : asd){
            System.out.print("\""+s+"\" ");
        }        
        System.out.println();
        System.out.println("Size = " + asd.size());
    }
}
