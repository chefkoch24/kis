package algorithmus;

import java.util.ArrayList;

public class RoboSim {
	
	private float [] data = new float [3]; 
	private final int DRIVE_FORWARD = 1;
	private final int DRIVE_LEFT = 2;
	private final int DRIVE_RIGHT = 3;
	private final int DRIVE_BACKWARD = 4;
	private final int LOOK_FORWARD = 5;
	private final int LOOK_LEFT = 6;
	private final int LOOK_RIGHT = 7;
	private final int STOP = 0;
	
	private ArrayList<Integer> brain;
	private String[][] map = new String[20][40];
	

}
