
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarCipher {
    private String alphabet;
    private String shiftedAlph;
    private int mainKey;
    public CaesarCipher() {
    }
    public CaesarCipher(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlph = alphabet.substring(key) + alphabet.substring(0,key);
        mainKey = key;
    }
    public String encrypt(String input) {       
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++){
            char currChar = encrypted.charAt(i);
            int index;
            if (Character.isLowerCase(currChar)){
               char copy = currChar;
               index = alphabet.indexOf(Character.toUpperCase(copy));
               char newChar = Character.toLowerCase(shiftedAlph.charAt(index));
               encrypted.setCharAt(i, newChar);
            }   
            else{
               index = alphabet.indexOf(currChar);
               if (index != -1){
                    char newChar = shiftedAlph.charAt(index);
                    encrypted.setCharAt(i, newChar);
               }
            }
        }
        return encrypted.toString();
    }
    public String decrypt(String input){
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input);
    }
    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder enTwoKeys = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            String temp = "" + input.charAt(i);
            char newChar;
            if (i % 2 == 0)
                newChar = encrypt(temp).charAt(0);
            else
                newChar = encrypt(temp).charAt(0);
            enTwoKeys.setCharAt(i, newChar);
        }
        return enTwoKeys.toString();
    }
    public void testCaesar() {
        FileResource fr = new FileResource("message2.txt");
        int key = 17;
        String message = fr.asString();
        String encrypted = encrypt(message);
        System.out.println("key is " + key + "\n" + encrypted);
    }
}
