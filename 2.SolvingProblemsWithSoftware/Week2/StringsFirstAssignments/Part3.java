
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurences(String stringa, String stringb) {
        if(stringb.indexOf(stringa, stringb.indexOf(stringa) + stringa.length()) != -1)
            return true;
        return false;
    }
    public void testing() {
        String t = "a";
        String s1 = "cxvcxsadcxxcvsd";        
        String s2 = "asfcxasd";        
        String s3 = "asdcasdxasd";        
        String s4 = "asd";
        System.out.println(t + " " + s1 + " - " + twoOccurences(t, s1));        
        System.out.println(t + " " + s2 + " - " + twoOccurences(t, s2));
        System.out.println(t + " " + s3 + " - " + twoOccurences(t, s3));        
        System.out.println(t + " " + s4 + " - " + twoOccurences(t, s4));
    }
    public String lastPart(String stringa, String stringb) {
        if (stringb.indexOf(stringa) == -1)
            return stringb;
        return stringb.substring(stringb.indexOf(stringa) + stringa.length(), stringb.length());
    }
    public void testLastPart() {
        String p1 = "an";
        String str1 = "banana"; 
        String p2 = "an";
        String str2 = "bcxvxcnna"; 
        String p3 = "an";
        String str3 = "Moncxandsdsa"; 
        System.out.println("The part of the string after " + p1 + " in " + str1 + " is " + lastPart(p1, str1));        
        System.out.println("The part of the string after " + p2 + " in " + str2 + " is " + lastPart(p2, str2));        
        System.out.println("The part of the string after " + p3 + " in " + str3 + " is " + lastPart(p3, str3));        
    }
}
