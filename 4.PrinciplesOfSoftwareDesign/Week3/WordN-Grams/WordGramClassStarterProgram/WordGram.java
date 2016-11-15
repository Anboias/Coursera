
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
        StringBuilder s = new StringBuilder();
        for (String str : myWords)
            s.append(str);
        String res = s.toString();
        myHash = res.hashCode();
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public int hashCode() {
        return myHash;
    }
    
    public String toString(){
        String ret = "";
        for (String s : myWords)
            ret = ret + s + " ";
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if (myWords.length != this.length())
            return false;
        for (int i = 0; i < myWords.length; i++) {
            if (this.wordAt(i) != other.wordAt(i))
                return false;
        }
        return true;
    }

    public WordGram shiftAdd(String word) { 
        WordGram out = new WordGram(myWords, 0, myWords.length);
        for (int i = 1; i < myWords.length; i++) {
            out.myWords[i-1] = out.myWords[i];
        }
        out.myWords[myWords.length-1] = word;        
        return out;
    }

}