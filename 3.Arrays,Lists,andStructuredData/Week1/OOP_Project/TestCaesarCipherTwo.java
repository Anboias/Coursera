
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class TestCaesarCipherTwo {
    public int maxIndex(int[] vals){
        int maxDex = 0;
        for (int k = 0; k < vals.length; k++){
            if (vals[k] > vals[maxDex])
                maxDex = k;
        }
        return maxDex;
    }
    public int[] countLetters(String message){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k = 0; k < message.length(); k++){
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if (dex != -1)
                counts[dex] += 1;
        }
        return counts;
    }
    public String halfOfString(String message, int start){
        String halfMessage = "";
        while (start < message.length()){
            halfMessage += message.charAt(start);
            start += 2;
        }
        return halfMessage;
    }
    public void simpleTests() {
        FileResource file = new FileResource("lotsOfE.txt");
        String mess = "";
        for (String word : file.words())
            mess += word + " ";
        CaesarCipherTwo obj = new CaesarCipherTwo(17, 3);
        String encr = obj.encrypt(mess);
        System.out.println(encr);
        breakCaesarCipher(mess);
    }
    public void breakCaesarCipher(String input) {
        String mess1 = halfOfString(input, 0);
        String mess2 = halfOfString(input, 1);
        int key1 = getKey(mess1);
        int dkey1 = key1 - 4;
        if (key1 < 4)
            dkey1 = 26 - (4 - key1);
        int key2 = getKey(mess2);
        int dkey2 = key2 - 4;
        if (key2 < 4)
            dkey2 = 26 - (4 - key2);
        CaesarCipherTwo cc = new CaesarCipherTwo(26-dkey1, 26-dkey2);
        String result = cc.encrypt(input);
        System.out.println((26-dkey1) + "=" + (26-dkey2));
        System.out.println("RESULT: " + result);
    }    
    public int getKey(String s) {
        int[] values = countLetters(s);
        int maxKey = maxIndex(values);
        return maxKey;            
    }
}
