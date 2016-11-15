
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class Part1 {
    public int countCTG(String dna){
        int counter = 0;
        int start = 0;
        while (true){
            int ind = dna.indexOf("CTG", start);
            if (ind == -1)
                break;
            counter++;
            start = ind + 3;
        }
        return counter;
    }
    public double cgRatio(String dna){
        int numitor = dna.length();
        int numarator = 0;
        for (int i = 0; i < numitor; i++){
            if (dna.charAt(i) == 'C' || dna.charAt(i) == 'G')
                numarator++;
        }
        return ((double)numarator)/numitor;        
    }
    public void processGenes(StorageResource sr){
        int counter = 0;
        int index = 0;
        int index2 = 0;        
        int index3 = 0;
        int max = 0;
        for (String s : sr.data()){
            if (s.length() > 9){
                System.out.println("processGenes: length >9: " + s);
                index++;
            }
            if (s.length() > 60){
                System.out.println("processGenes: length >60: " + s);
                index3++;
            }
            if (cgRatio(s) > 0.35){
                System.out.println("processGenes: cgRatio > 0.35: " + s);
                index2++;
            }
            if (s.length() > max){
                max = s.length();
            }
            counter++;
        }        
        System.out.println("processGenes: Total#OfGenes : " + counter);                
        System.out.println("processGenes: #length >9: " + index);                
        System.out.println("processGenes: #length >60: " + index3);        
        System.out.println("processGenes: #cgRatio >0.35: " + index2);        
        System.out.println("processGenes: max length: " + max);        
    }
}//video Coding StorageResource class in lesson Using the StorageResource Class
