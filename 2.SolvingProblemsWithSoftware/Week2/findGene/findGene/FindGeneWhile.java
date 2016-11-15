package findGene;

//video Coding While Loops in lesson Finding All Genes in DNA

/**
 * Write a description of FindGeneWhile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FindGeneWhile {
    public int count = 0;
    public String findGene(String dna) {
        //Find first occurrence of “ATG” call its index "startIndex"
        int startIndex = dna.indexOf("ATG");
        //Find the "TAA" starting from (startIndex+3), call this result currIndex
        String[] typ = {"TAA", "TGA", "TAG"};
        int k = 0;
        int min = Integer.MAX_VALUE;
        int currIndex = 0;

        while (k < 3){
            currIndex = dna.indexOf(typ[k], startIndex + 3);
            if ((currIndex - startIndex) % 3 == 0 && startIndex != -1){
                if (currIndex < min){
                    System.out.println("Inside " + dna.substring(startIndex, currIndex + 3));
                    count++;
                    startIndex = dna.indexOf("ATG", currIndex + 3);
                    k = 0;
                    min = Integer.MAX_VALUE;
                }
            }
            k++;
        }
        if(min != -1 && min != Integer.MAX_VALUE) {
            return dna.substring(startIndex, min +3);
        }
        return "";
    }
    public void testFindGeneSimple() {
        //             v  v  v  v  v
        String dna = "AATGCTAACTAGCTGACTAAT";
        System.out.println("DNA strand is " + dna);
        String gene = findGene(dna);
        System.out.println("Gene count is " + count);

    }
}
