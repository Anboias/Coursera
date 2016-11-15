import edu.duke.*;

public class Part4 {
    public void funct() {
        URLResource url = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String word : url.words()){
            String word2 = word.toLowerCase();
            int i = word2.indexOf("youtube.com");
            if (i != -1) {
                System.out.println(word.substring(i-11, word.indexOf("\"", i-11)));
            }
        }
    }
}
