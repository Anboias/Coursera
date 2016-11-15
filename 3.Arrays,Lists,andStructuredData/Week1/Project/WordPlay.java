
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    
    public boolean isVowel(char ch) {
        boolean value = true;
        String vowels = "aeiouAEIOU";
        if (vowels.indexOf(ch) == -1)
            value = false;
        return value;
    }
    public String replaceVowels(String phrase, char ch) {
        String replaced = "";
        for (int i = 0; i < phrase.length(); i++) {
            char c = phrase.charAt(i);
            if (isVowel(c))
                replaced += ch;
            else
                replaced += c;
        }
        return replaced;
    }
    public String emphasize(String phrase, char ch) {
        String replaced = "";
        for (int i = 0; i < phrase.length(); i++) {
            char c = phrase.charAt(i);
            replaced += c == ch ? i % 2 == 0 ? '*' : '+' : c;
        }
        return replaced;
    }
}
