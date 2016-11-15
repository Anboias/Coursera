import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
   private int gKey[];
   private int gValidWords;
   //OK
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for (char c : message.toCharArray()) {
            if (i == whichSlice){
                answer.append(c);
                whichSlice+=totalSlices;
            }
            i++;
        }
        return answer.toString();
    }
    
    //OK
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        
        for (int i = 0; i < klength; i++) {
            String mess = sliceString(encrypted, i, klength);
            key[i] = cc.getKey(mess);            
        }
        return key;
    }
    
    //OK
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> hash = new HashSet<String>();
        for (String line : fr.lines()) {
            hash.add(line.toLowerCase());
        }
        return hash;
    }
    
    //OK
    public char mostCommonCharIn(HashSet<String> dict) {
        HashMap<Character, Integer> count = new HashMap<Character, Integer>();
        char mostUsed = 'i'; 
        int max = 0;
        String wholeString = dict.toString();
        for (int k = 0; k < wholeString.length(); k++) {
            char ch = wholeString.charAt(k);
            if (ch != ' ' && ch != '.' && ch != ',') {
                if (!count.containsKey(ch)){
                    count.put(ch, 1);
                }
                else {
                    count.put(ch, count.get(ch) + 1);
                }
            }
        }
        for (Character c : count.keySet()) {
            if (count.get(c) > max)
                max = count.get(c);
        }
        for (Character c : count.keySet()) {
            if (count.get(c) == max)
                mostUsed = c;
        }
        return mostUsed;
    }
       
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        HashSet<String> temp;

        String languageOfTheMessage = "";
        
        
        char mostCommon;
        String mess = "";

        for (String l : languages.keySet()) {
            languageOfTheMessage = l;
            
            temp = languages.get(l);
            mostCommon = mostCommonCharIn(temp); 
           // System.out.println("Most common char in " + l + " is " + mostCommon);
        
            String per = breakForLanguage(encrypted, temp, mostCommon);
            if (per.length() > 1){
                mess = per;
                languageOfTheMessage = l;
                break;
            }
        }
              
               
        System.out.println(mess.substring(0, 100));   
        System.out.printf("\n\n");
        System.out.println("The key: " + Arrays.toString(gKey));
        System.out.println("Key length = " + gKey.length);
        System.out.println("# of valid words: " + gValidWords);
        System.out.println("Language of the message: " + languageOfTheMessage);
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary, char mostCommon) {
        String mess = "";
        int[] bestSoFar = {0};
        int max = 0;
        
        for (int i = 1; i <= 100; i++) {
            int[] potentialKey = tryKeyLength(encrypted, i, mostCommon);
            VigenereCipher  vc = new VigenereCipher(potentialKey);
            mess = vc.decrypt(encrypted);
            int temp = countWords(mess, dictionary);
            if (temp == 1) {
                gKey = potentialKey;
                return mess;
            }
        }
        
        return "";
    }
    
    public int countWords(String message, HashSet<String> dict) {
        int count = 0;
        int len = 0;
        for (String word : message.split("\\W+")) {
            if (dict.contains(word.toLowerCase())){
                count++;
            }
            len++;
        }
        if (count > len/2) {
            gValidWords = count;
            return 1;
        }
        return 0;        
    }
     
    public void breakVigenere () {
       FileResource fr = new FileResource();
       String mess = fr.asString();
       
       String[] languages = {"Danish","Dutch","English","French","German","Italian","Portuguese","Spanish"};
       HashSet<String> theirDict;
       HashMap<String, HashSet<String>> multipleLang = new HashMap<String, HashSet<String>>();
       
       for (String l : languages) {
           String address = "dictionaries/" + l;
           FileResource tempfr = new FileResource(address);
           theirDict = readDictionary(tempfr);           
           multipleLang.put(l, theirDict);
       }
       System.out.println();
       breakForAllLangs(mess, multipleLang);
       
        
       /* char mostCommon = mC;
        FileResource fr = new FileResource("messages/secretmessage2.txt");
        String mess = fr.asString();
        
        FileResource dct = new FileResource("dictionaries/English");
        dictionary = readDictionary(dct);
        
        mess = breakForLanguage(mess, dictionary);
        
        System.out.println(mess.substring(0, 100));
        
        System.out.println(Arrays.toString(theBestSoFar));
        System.out.println("Key length = " + theBestSoFar.length);
        System.out.println("# of valid words: " + validWords);
        */
        /*
        *OLD with klength provided
        
        int[] theKey = tryKeyLength(mess, klength, 'e');
        
        VigenereCipher vc = new VigenereCipher(theKey);
        mess = vc.decrypt(mess);
        
        System.out.println(mess);
        */
    }
    
}
