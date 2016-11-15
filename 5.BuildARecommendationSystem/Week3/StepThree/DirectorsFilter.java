
/**
 * Write a description of GenreFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import java.util.*;

public class DirectorsFilter implements Filter {
    private String[] directorsList;
    
    public DirectorsFilter(String dir) {
        directorsList = dir.split(",");
    }
    
    @Override
    public boolean satisfies(String id) {
        boolean answer = false;
        try {
            for (int i = 0; i < directorsList.length; i++) {
                if (MovieDatabase.getDirector(id).contains(directorsList[i])) {
                    answer = true;
                    break;
                }
            }
        } catch (IOException ie) {}
        return answer;
    }
}
