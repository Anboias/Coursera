
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna) {
        String result = "";
        
        int start = dna.indexOf("ATG");
        int end = dna.indexOf("TAA", start + 3);
        if (start == -1 || end == -1)
            return "";
        result = dna.substring(start, end + 3);
        if (dna.length() % 3 != 0)
            return "";
        return result;
    }
    
    public void testSimpleGene() {
        //no ATG
        String dna1 = "ASCXASCACSACASTAAASAC";
        System.out.println("DNA: " + dna1);
        System.out.println("GENE: " + findSimpleGene(dna1));
        //no TAA
        String dna2 = "FDCATGASDASSCAADWEEWQW";
        System.out.println("DNA: " + dna2);
        System.out.println("GENE: " + findSimpleGene(dna2));
        //no ATG or TAA
        String dna3 = "SADOGSIKSDGISDDVVEOLSVKES";
        System.out.println("DNA: " + dna3);
        System.out.println("GENE: " + findSimpleGene(dna3));
        //a gene
        String dna4 = "ASDVDSDATGASDZCXZXCZXCTAAASDAS";
        System.out.println("DNA: " + dna4);
        System.out.println("GENE: " + findSimpleGene(dna4));
        //with ATG and TAA; %3 != 0
        String dna5 = "ASDVDSDATGASDZCXZXCDZXCTAAASDASD";
        System.out.println("DNA: " + dna5);
        System.out.println("GENE: " + findSimpleGene(dna5));
    }
}
