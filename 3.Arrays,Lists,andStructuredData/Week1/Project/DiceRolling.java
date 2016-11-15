
/**
 * Write a description of DiceRolling here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;

public class DiceRolling {
    public void simpleSimulate(int rolls) {
        Random rand = new Random();
        int[] dice = new int[6];
        
        for (int k = 0; k < rolls; k++) {
            int d = rand.nextInt(6) + 1;
            dice[d-1]++;
        }
        for (int j = 0; j < 6; j++){
            System.out.println(j+1 + "`s - " + dice[j]);
        }
    }
}
