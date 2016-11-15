
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarBreaker {
    /*  public void eyeballDecrypt(String encrypted) {
        CaesarCipher cipher = new CaesarCipher();
        for (int k = 0; k < 26; k++) {
            String s = cipher.encrypt(encrypted, k);
            System.out.println(k+"\t"+s);
        }
    } 
    */
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
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int maxDex = getKey(encrypted);
        int dkey = maxDex - 4; // 4=e
        if (maxDex < 4)
            dkey = 26 - (4 - maxDex);
        String message = cc.encrypt(encrypted, 26-dkey);
        return message;
    }
    public void decryptTwoKeys(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        String mess1 = halfOfString(encrypted, 0);
        String mess2 = halfOfString(encrypted, 1);
        int key1 = getKey(mess1);
        int dkey1 = key1 - 4;
        if (key1 < 4)
            dkey1 = 26 - (4 - key1);
        int key2 = getKey(mess2);
        int dkey2 = key2 - 4;
        if (key2 < 4)
            dkey2 = 26 - (4 - key2);
        String result = cc.encryptTwoKeys(encrypted, 26-dkey1, 26-dkey2);
        System.out.println((dkey1) + "=" + (dkey2));
        System.out.println(result);
    }
    public void testDecrypt(String mess){
        String t = decrypt(mess);
        System.out.println(t);
    }
    public int maxIndex(int[] vals){
        int maxDex = 0;
        for (int k = 0; k < vals.length; k++){
            if (vals[k] > vals[maxDex])
                maxDex = k;
        }
        return maxDex;
    }
    public String halfOfString(String message, int start){
        String halfMessage = "";
        while (start < message.length()){
            halfMessage += message.charAt(start);
            start += 2;
        }
        return halfMessage;
    }
    public int getKey(String s) {
        int[] values = countLetters(s);
        int maxKey = maxIndex(values);
        return maxKey;            
    }
    public void testDecryptTwoKeys() {
        FileResource fr = new FileResource("text2.txt");
        String messs = "";
        for (String word : fr.words()){
            messs += word + " ";
        }
        decryptTwoKeys(messs);        
    }    
}
