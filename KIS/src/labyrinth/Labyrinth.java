package labyrinth;

import java.io.BufferedReader;
import java.io.FileReader;

public class Labyrinth {
	
	public Labyrinth(String filename) {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filename));
			StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    br.close();
		    String everything = sb.toString();
		    System.out.print(everything);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String [] args) {
		Labyrinth l = new Labyrinth("./src/labyrinth/maze1.txt");
	}

}
