
/**
 * Write a description of WhichCountriesExport here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
public class Assignement 
{
    //2
    public void countryInfo(CSVParser parser, String co){
        for (CSVRecord record : parser) {
            String country = record.get("Country");
            if (country.equals(co)){
                String export = record.get("Exports");
                String value = record.get("Value (dollars)");
                System.out.println("countryInfo - " + country + ": " + export + ": " + value);
            }
        }
    }
    //3
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        int i = 0;
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem1) && export.contains(exportItem2)){
                String country = record.get("Country");
                System.out.println("listExportersTwoProducts - " + country);
                i++;
            }
            if (i == 5)
                break;
        }
    }
    //4
    public int numberOfExporters(CSVParser parser, String exportItem){
        int counter = 0;
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem)){
                counter++;
            }
        }
        return counter;
    }
    //5
    public void bigExporters(CSVParser parser, String value){
        for (CSVRecord record : parser) {
            String val = record.get("Value (dollars)");
            if (val.length() > value.length()){
                String country = record.get("Country");
                System.out.println("Big exporters are - " + country + " " + val);
            }  
        }
    }
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        //2.countryInfo
        //countryInfo(parser, "Nauru");
        System.out.println();
        
        //3.listExportersTwoProducts
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "cotton", "flowers");
        System.out.println();
        
        //4.numberOfExporters
        parser = fr.getCSVParser();
        String elem = "cocoa";
        int noOfExporters = numberOfExporters(parser, elem);
        System.out.println("No of exporters of " + elem + " is - " + noOfExporters);
        System.out.println();
        
        //5.bigExporters
        parser = fr.getCSVParser();
        String val = "$999,999,999,999";
        bigExporters(parser, val);
        
    }
}
