import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

/**
 * Write a description of BabyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class BabyBirths {
    double noOfFiles = -1;
    double totalRank = -1;
    int totalOfBirths = 0;
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.printf("%-10s %-5s %-5s\n", rec.get(0), rec.get(1), rec.get(2));
            }
        }
    }
    
    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int noOfBoys = 0;
        int noOfGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                noOfBoys++;
                totalBoys += numBorn;
            }
            else {
                noOfGirls++;
                totalGirls += numBorn;
            }
        }
        System.out.println("Total births = " + totalBirths);        
        System.out.println("Total boys = " + totalBoys);        
        System.out.println("Total girls = " + totalGirls);        
        System.out.println("#OfBoyz girls = " + noOfBoys);        
        System.out.println("#OfGirlz girls = " + noOfGirls);
    }
    
    public void testTotalBirths() {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public int getRank(int year, String name, String gender) {
        String fileName = "data/yob" + year + ".csv";
        FileResource fr = new FileResource(fileName);
        int rank = 1;
        
        for(CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)){
                if (rec.get(0).equals(name))
                    return rank;
                totalOfBirths += Integer.parseInt(rec.get(2));
                rank++;
            }
        }
        return -1;
    }
    public String getName(int year, int rank, String gender) {
        String fileName = "data/yob" + year + ".csv";
        FileResource fr = new FileResource(fileName);
        int pos = 1;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)){
                if (pos == rank)
                    return rec.get(0);
                pos++;
            }
        }
        return "NO NAME";
    }
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int pos = getRank(year, name, gender);
        String whatName = getName(newYear, pos, gender);
        
        System.out.println(name + " born in " + year + " would be " + whatName + " if she/he was born in " + newYear);
    }
    
    public int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int rankMax = 999999;
        int yearMax = -1;
        noOfFiles = 0;
        totalRank = 0;
        for (File f : dr.selectedFiles()){
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.substring(3,7));
            int thisYearRank = getRank(year, name, gender);
            totalRank += (double)thisYearRank;
            noOfFiles++;
            System.out.println(year + " " + thisYearRank + " " + fileName);
            if (thisYearRank < rankMax && thisYearRank != -1){
                rankMax = thisYearRank;
                yearMax = year;
            }
        }
        return yearMax;
    }
    
    public double getAverageRank(String name, String gender) {
        yearOfHighestRank(name, gender);
        
        return totalRank/noOfFiles;
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        getRank(year, name, gender);
        
        return totalOfBirths;
    }
}
