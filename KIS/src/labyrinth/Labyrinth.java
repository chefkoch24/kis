package labyrinth;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Labyrinth {
	private String mazeFile;

	public Labyrinth(String mazeFile) {
		setMazeFile(mazeFile); //sets private variable: mazeFile
		
//		 BufferedReader br;
//		try {
//			br = new BufferedReader(new FileReader(mazeFile));
//			StringBuilder sb = new StringBuilder();
//		    String line = br.readLine();
//		    while (line != null) {
//		        sb.append(line);
//		        sb.append(System.lineSeparator());
//		        line = br.readLine();
//		    }
//		    br.close();
//		    String everything = sb.toString();
//		    System.out.print(everything);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	public void printMazeCommandLine() {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(this.mazeFile));
			
			StringBuilder sb = new StringBuilder();
			String line = "";
			while((line = br.readLine()) != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
			}
			br.close();
			System.out.println(sb.toString());
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void setMazeFile(String newMazeFile) {
		FileReader fr;
		try {
			fr = new FileReader(newMazeFile);
			this.mazeFile = newMazeFile;
			fr.close();
		} catch(FileNotFoundException e){
			System.err.println("Datei: " + newMazeFile + " nicht gefunden!");
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Labyrinth l = new Labyrinth("./src/labyrinth/maze2.txt");
		l.printMazeCommandLine();
	}

}
