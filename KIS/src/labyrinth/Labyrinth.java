package labyrinth;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Labyrinth {
	private String mazeFilePath;
	private String maze;
	
	// Arraywerte für Simulation
	private final int arrayVoidValue = 0;
	private final int arrayWallValue = 1;
	private final int arrayRoboBodyValue = 2;
	private final int arrayRoboHeadValue = 3;
	
	private int[][] numericMaze; 

	public Labyrinth(String mazeFile) {
		setMazeFile(mazeFile);
		updateMaze();
		mazeToArray();
		
		
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

	public void mazeToArray(){
		System.out.println(this.mazeFilePath);		
		
		
	}
	
	public void updateMaze() {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(this.mazeFilePath));
			
			StringBuilder sb = new StringBuilder();
			String line = "";
			while((line = br.readLine()) != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
			}
			br.close();
			
			this.maze = sb.toString();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void setMazeFile(String newMazeFile) {
		FileReader fr;
		try {
			fr = new FileReader(newMazeFile);
			this.mazeFilePath = newMazeFile;
			fr.close();
		} catch(FileNotFoundException e){
			System.err.println("Datei: " + newMazeFile + " nicht gefunden!");
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getMaze() {
		return this.maze;
	}

	public static void main(String[] args) {
		Labyrinth l = new Labyrinth("./src/labyrinth/maze1.txt");
		// TODO warum Pfad, nicht Labyrinth selbst?
		l.getMaze();
	}

}
