import edu.duke.*;
import java.util.*;

public class WordFrequenciesMap {
	
	public void countWords(String filename){
		FileResource fr = new FileResource(filename);
		ArrayList<String> words = new ArrayList<String>();
		ArrayList<Integer> counters = new ArrayList<Integer>();
		for(String w : fr.words()){
			w = w.toLowerCase();
			int index = words.indexOf(w);
			if (index == -1){
				words.add(w);
				counters.add(1);
			}
			else {
				int value = counters.get(index);
				counters.set(index, value + 1);
			}
		}
		int total = 0;
		for(int k=0; k < words.size(); k++){
			if (counters.get(k) > 500){
				System.out.println(counters.get(k)+"\t"+words.get(k));
			}
			total += counters.get(k);
		}
		System.out.println("total count: "+total+" different = "+words.size());
	}
	
	public void countWordsMap(String filename){
		FileResource fr = new FileResource(filename);
		HashMap<String,Integer> map = new HashMap<String,Integer>();

		for(String w : fr.words()){
			w = w.toLowerCase();
			if (!map.containsKey(w)){
				map.put(w,1);
			}
			else {
				map.put(w,map.get(w)+1);
			}
		}
		int total = 0;
		for(String w : map.keySet()){
			int value = map.get(w);
			if (value > 500){
				System.out.println(value+"\t"+w);
			}
			total += value;
		}
		System.out.println("total count: "+total+" different = "+map.keySet().size());
	}
	
	public void tester(){
		String filename = "kjv10.txt";
		double start1 = System.currentTimeMillis();
		countWords(filename);
		double end1 = System.currentTimeMillis();
		double time1 = (end1-start1)/1000;
		
		double start2 = System.currentTimeMillis();
		countWordsMap(filename);
		double end2 = System.currentTimeMillis();
		double time2 = (end2-start2)/1000;
		
		System.out.println("time = "+time1);
		System.out.println("time = "+time2);
	}
	
}
