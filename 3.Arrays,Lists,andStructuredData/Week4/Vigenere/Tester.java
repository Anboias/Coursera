
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;
import edu.duke.*;

public class Tester {
    public void testCaesarCipher(int key) {
        FileResource fr = new FileResource();
        
        String mess = fr.asString();
        
        CaesarCipher cc = new CaesarCipher(key);
        mess = cc.encrypt(mess);
        System.out.println("Encrypted: " + mess);  
        
        mess = cc.decrypt(mess);
        System.out.println("Decrypted: " + mess);
        
    }
    
    public void testCaesarCracker() {
        FileResource fr = new FileResource();
        String mess = fr.asString();
        
        CaesarCracker crack = new CaesarCracker();
        mess = crack.decrypt(mess);
        
        System.out.println(mess);                
    }    
    
    public void testCaesarCrackerDiffLanguage(char c) {
        FileResource fr = new FileResource();
        String mess = fr.asString();
        
        CaesarCracker crack = new CaesarCracker(c);
        mess = crack.decrypt(mess);
        
        System.out.println(mess);                
    }
    
    public void testVigenereCipher() {
        int[] rome = {17,14,12,4};
        
        FileResource fr = new FileResource();
        String mess = fr.asString();
        
        VigenereCipher vc = new VigenereCipher(rome);
        
        mess = vc.encrypt(mess);
        System.out.println(mess);
        
        mess = vc.decrypt(mess);
        System.out.println(mess);
    }
    
    public void testVBsliceString(String message, int whichSlice, int totalSlices) {
        VigenereBreaker vb = new VigenereBreaker();
        
        String mess = vb.sliceString(message, whichSlice, totalSlices);
        System.out.println(mess);
    }
    
    public void testVBtryKeyLength(int klength) {
        char mostCommon = 'e';
        
        FileResource fr = new FileResource();
        String mess = fr.asString();
        
        VigenereBreaker vb = new VigenereBreaker();
        int[] asd = vb.tryKeyLength(mess, klength, mostCommon);
        for (Integer x : asd) {
            System.out.println(x);
        }
    }
    
    public void countWords() {
        FileResource fr = new FileResource("messages/secretmessage2.txt");
        String mess = fr.asString();
        int index = 0;
        for (String word : mess.split("\\W+")) {
            index++;
        }
        System.out.println(index);
    }
    
    public void testVBbreakVigenere() {
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();
    }
}
