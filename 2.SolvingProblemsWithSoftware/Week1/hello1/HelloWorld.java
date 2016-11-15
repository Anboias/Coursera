import edu.duke.*;

public class HelloWorld {
	public void runHello () {
		URLResource res = new URLResource("http://nytimes.com");
		for (String line : res.words()) {
			System.out.println(line);
		}
	}
}
