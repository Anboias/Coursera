
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;    
    private String shiftedAlphabet2;
    private int initKey1;
    private int initKey2;
    
    public CaesarCipherTwo(int key1, int key2) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);        
        shiftedAlphabet1 = alphabet.substring(key2) + alphabet.substring(0,key2);
        initKey1 = key1;
        initKey2 = key2;
    }
    public String encrypt(String input) {       
        StringBuilder enTwoKeys = new StringBuilder(input);
        String temp = "";
        String res = "";
        CaesarBreaker cc = new CaesarBreaker();
        String str1 = cc.halfOfString(input, 0);            
        String str2 = cc.halfOfString(input, 1);
        CaesarCipher obj1 = new CaesarCipher(initKey1);        
        CaesarCipher obj2 = new CaesarCipher(initKey2);
        String str1Encr = obj1.encrypt(str1);        
        String str2Encr = obj2.encrypt(str2);
        int j = 0;
        for (int i = 0; i < input.length()/2; i++) {
            enTwoKeys.setCharAt(j, str1Encr.charAt(i));
            j++;
            enTwoKeys.setCharAt(j, str2Encr.charAt(i));
            j++;
        }
        return enTwoKeys.toString();
    }
}
