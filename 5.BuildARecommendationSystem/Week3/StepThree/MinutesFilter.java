import java.io.*;

public class MinutesFilter implements Filter{
    private int min;
    private int max;
    
    public MinutesFilter(int min, int max) {
        this.min = min;
        this.max = max;
    }
    
    @Override
    public boolean satisfies(String id) {
        try {
            return (MovieDatabase.getMinutes(id) >= min && MovieDatabase.getMinutes(id) <= max);
        }catch (IOException ie) {return false;}
    }

}
