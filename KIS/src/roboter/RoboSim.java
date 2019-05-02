package roboter;

import java.util.ArrayList;

public class RoboSim implements Roboter{
	
	private float [] data = new float [3]; 
	private final int STOP = 0;
	private final int DRIVE_FORWARD = 1;
	private final int DRIVE_LEFT = 2;
	private final int DRIVE_RIGHT = 3;
	private final int DRIVE_BACKWARD = 4;
	private final int LOOK_FORWARD = 5;
	private final int LOOK_LEFT = 6;
	private final int LOOK_RIGHT = 7;
	
	
	private ArrayList<Integer> brain;
	private String[][] map = new String[20][40];
	@Override
	public void doAction(int action) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void fetchData(int pos) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int findBarrier() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void look() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean isBumped() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isGoal() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void forward() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void backward() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void left() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void right() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
	
 
}
