
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, int startCodon, int stopCodon) {
        String result = "";
        
        if (startCodon == -1 || stopCodon == 2)
            return "";
        result = dna.substring(startCodon, stopCodon);
        if (dna.length() % 3 != 0)
            return "";
        return result;
    }
    
    public void testSimpleGene() {
        //no ATG
        String dna1 = "ASCXASCACSACASTAAASAC";
        System.out.println("DNA: " + dna1);
        System.out.println("GENE: " + findSimpleGene(dna1, dna1.indexOf("ATG"), dna1.indexOf("TAA")+3));
        //no TAA
        String dna2 = "FDCATGASDASSCAADWEEWQW";
        System.out.println("DNA: " + dna2);
        System.out.println("GENE: " + findSimpleGene(dna2, dna2.indexOf("ATG"), dna2.indexOf("TAA")+3));
        //no ATG or TAA
        String dna3 = "SADOGSIKSDGISDDVVEOLSVKES";
        System.out.println("DNA: " + dna3);
        System.out.println("GENE: " + findSimpleGene(dna3, dna3.indexOf("ATG"), dna3.indexOf("TAA")+3));
        //a gene
        String dna4 = "ASDVDSDATGASDZCXZXCZXCTAAASDAS";
        System.out.println("DNA: " + dna4);
        System.out.println("GENE: " + findSimpleGene(dna4, dna4.indexOf("ATG"), dna4.indexOf("TAA")+3));
        //with ATG and TAA; %3 != 0
        String dna5 = "ASDVDSDATGASDZCXZXCDZXCTAAASDASD";
        System.out.println("DNA: " + dna5);
        System.out.println("GENE: " + findSimpleGene(dna5, dna5.indexOf("ATG"), dna5.indexOf("TAA")+3));
    }
}
