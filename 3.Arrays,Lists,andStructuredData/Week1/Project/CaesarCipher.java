
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlph = alphabet.substring(key) + alphabet.substring(0,key);

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
    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder enTwoKeys = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            String temp = "" + input.charAt(i);
            char newChar;
            if (i % 2 == 0)
                newChar = encrypt(temp, key1).charAt(0);
            else
                newChar = encrypt(temp, key2).charAt(0);
            enTwoKeys.setCharAt(i, newChar);
        }
        return enTwoKeys.toString();
    }
    public void testCaesar() {
        FileResource fr = new FileResource("message2.txt");
        int key = 17;
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
}
