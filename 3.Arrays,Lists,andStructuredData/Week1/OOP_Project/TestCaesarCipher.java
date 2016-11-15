
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCaesarCipher {
    public void simpleTests() {
        FileResource file = new FileResource("text.txt");
        String mess = "";
        String newMess = "";
        String newnewMess = "";
        for (String word : file.words())
            mess += word + " ";
        int key = breakCaesarCipher(mess);
        CaesarCipher cc = new CaesarCipher(key);
        newMess += cc.encrypt(mess);
        System.out.println("Initial string: " + mess);
        newnewMess += cc.decrypt(newMess);
        System.out.println("Decrypted string: " + newMess);  
        System.out.println("Recrypted string: " + newnewMess);  
    }
    public int breakCaesarCipher(String input) {
        CaesarBreaker getK = new CaesarBreaker();
        int key = getK.getKey(input);
        int dkey = key - 4;
        if (key < 4)
            dkey = 26 - (4-key);
        return (26 - dkey);
    }
}
