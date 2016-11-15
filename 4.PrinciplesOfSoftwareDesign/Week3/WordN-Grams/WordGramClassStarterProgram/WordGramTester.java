import java.util.*;

public class WordGramTester {
    public void testWordGram(){
        String source = "this is a test sadas";
        String[] words = source.split("\\s+");
        String[] w2 = new String[words.length+1];
        w2[words.length] = "asd";
        int size = words.length;
        for(int index = 0; index <= words.length - size; index += 1) {
            WordGram wg = new WordGram(words,index,size);            
            WordGram wg1 = new WordGram(w2,index,size+1);
            //System.out.println(index+"\t"+wg.length()+"\t"+wg);
            System.out.println("length= "+wg.length());         
            System.out.println("toString= "+wg.toString());
            System.out.println("equals= "+wg.equals(wg1));         
            System.out.println("shiftAdd= "+wg.shiftAdd("mama").toString());     
        }
    }
    
    public void testWordGramEquals(){
        String source = "this is a test this is a test this is a test of words";
        String[] words = source.split("\\s+");
        ArrayList<WordGram> list = new ArrayList<WordGram>();
        int size = 4;
        for(int index = 0; index <= words.length - size; index += 1) {
            WordGram wg = new WordGram(words,index,size);
            list.add(wg);
        }
        WordGram first = list.get(0);
        System.out.println("checking "+first);
        for(int k=0; k < list.size(); k++){
            //if (first == list.get(k)) {
              if (first.equals(list.get(k))) {
                System.out.println("matched at "+k+" "+list.get(k));
            }
        }
    }
    
}
