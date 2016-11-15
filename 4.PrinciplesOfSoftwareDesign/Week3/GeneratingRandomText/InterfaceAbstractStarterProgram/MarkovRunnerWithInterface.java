
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("Running: "+markov);
        for(int k=0; k < 3; k++){
           String st= markov.getRandomText(size);
           printOut(st);
        }
    }
    
    public void testHashMap() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        st = "this is a test yes this is really a test";
        int size = 50;
        int seed = 42;
        
        EfficientMarkovModel mEff = new EfficientMarkovModel(2);
        runModel(mEff, st, size, seed);
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 100;
        int seed = 365;
        
        MarkovZero mz = new MarkovZero();
        //runModel(mz, st, size, seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        
        MarkovModel mThree = new MarkovModel(3);
        //runModel(mThree, st, size, seed);
        
        MarkovFour mFour = new MarkovFour();
        //runModel(mFour, st, size, seed);
        
        MarkovModel mModel = new MarkovModel(7);
        // runModel(mModel, st, size, seed);

    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
    
}
