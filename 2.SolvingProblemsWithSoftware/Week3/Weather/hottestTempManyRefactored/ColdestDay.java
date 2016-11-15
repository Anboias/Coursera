/**
 * Find the highest (hottest) temperature in any number of files of CSV weather data chosen by the user.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.lang.String;

public class ColdestDay {
    String fileName = "";
    String lowestHumAllFiles = "";
    String hourAndDate = "";
    public CSVRecord coldestHourInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord smallestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar, "TemperatureF");
        }
        //The largestSoFar is the answer
        return smallestSoFar;
    }
    public void testColdestHourInFile(){
        FileResource cc = new FileResource();
        CSVRecord smallest = coldestHourInFile(cc.getCSVParser());
        System.out.println("coldest temperature was " + smallest.get("TemperatureF") +
                   " at " + smallest.get("DateUTC"));
    }
    public void testColdestInDay () {
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + coldest.get("TemperatureF") +
                   " at " + coldest.get("DateUTC"));
    }

    public CSVRecord coldestInManyDays() {

        CSVRecord coldestSoFar = null;        
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());                        
            CSVRecord lowestRow = lowestHumidityInFile(fr.getCSVParser());
            // use method to compare two records
            CSVRecord coldestCopy = coldestSoFar;            
            CSVRecord lowestCopy = lowestSoFar;
            
            coldestSoFar = getSmallestOfTwo(currentRow, coldestSoFar, "TemperatureF");            
            lowestSoFar = getSmallestOfTwo(lowestRow, lowestSoFar, "Humidity");
            
            if (coldestSoFar != coldestCopy){
                fileName = f.getName();
            }
            if (lowestSoFar != lowestCopy){
                lowestHumAllFiles = f.getName();
            }

        }
        //The largestSoFar is the answer
        return coldestSoFar;
    }

    public CSVRecord getSmallestOfTwo (CSVRecord currentRow, CSVRecord smallestSoFar, String valueToCompare) {
        //If largestSoFar is nothing
        if (smallestSoFar == null) {
            smallestSoFar = currentRow;
        }
        //Otherwise
        else {
            if (!currentRow.get(valueToCompare).equals("N/A")){
                double currentTemp = Double.parseDouble(currentRow.get(valueToCompare));
                double smallestTemp = Double.parseDouble(smallestSoFar.get(valueToCompare));
                //Check if currentRow’s temperature > largestSoFar’s
                if (currentTemp < smallestTemp && currentTemp != -9999) {
                    //If so update largestSoFar to currentRow
                    smallestSoFar = currentRow;
                    hourAndDate = currentRow.get("DateUTC");
                }
            }
        }
        return smallestSoFar;
    }

    public void testColdestInManyDays () {
        CSVRecord smallest = coldestInManyDays();
        System.out.println("hottest temperature was " + smallest.get("TemperatureF") +
                   " at " + smallest.get("DateUTC"));
    }
    
    public String fileWithColdestTemperature() {
        coldestInManyDays();
        return fileName;       
    }
    
    public void testLowestHumidityInManyFile() {
        coldestInManyDays();
        String mess = "data/2013/" + lowestHumAllFiles;
        
        FileResource cc = new FileResource(mess);
        CSVRecord smallest = lowestHumidityInFile(cc.getCSVParser());
        System.out.println("Lowest humid in many files is: " + smallest.get("Humidity") + " at  " + smallest.get("DateUTC"));
         
    }
    
    
    public void testFileWithColdestTemperature() {
        String mess = fileWithColdestTemperature();
        mess = "data/2013/" + mess;
        System.out.println("Coldest day was in file " + mess);
        
        FileResource cc = new FileResource(mess);
        CSVRecord smallest = coldestHourInFile(cc.getCSVParser());
        System.out.println("Coldest Temperature on that day was " + smallest.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        CSVParser sls = cc.getCSVParser();
        for (CSVRecord curr : sls) {
            System.out.println(curr.get("DateUTC") + " " + curr.get("TemperatureF"));
        }
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord smallestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar, "Humidity");
        }
        //The largestSoFar is the answer
        return smallestSoFar;
    }
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity")  + " at " + hourAndDate );
    }  
        
    public double averageTemperatureInFile(CSVParser parser) {
        double total = 0;
        double counter = 0;
        for (CSVRecord curr : parser){
            total += Double.parseDouble(curr.get("TemperatureF"));
            counter++;
        }
        return total/counter;
    }
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double temp = averageTemperatureInFile(parser);
        System.out.println("Average Temp when high Humidity is " + temp);
    }    

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double total = 0;
        double counter = 0;
        for (CSVRecord curr : parser){
            if (Double.parseDouble(curr.get("Humidity")) >= (double)value){
                total += Double.parseDouble(curr.get("TemperatureF"));
                counter++;
            }
        }
        return total/counter;
    }
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double temp = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (temp > 0)
            System.out.println("Average Temp when high Humidity is " + temp);
        else
            System.out.println("No temperatures with that humidity");

    }   
        
}
