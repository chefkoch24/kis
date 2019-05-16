package roboter;

import java.util.ArrayList;

import labyrinth.Labyrinth;

public class RoboSim implements Roboter {
	private static int numberOfRobots = 0;
	
	private final String roboName;

	private float[] data = new float[3];
	private final int STOP = 0;
	private final int DRIVE_FORWARD = 1;
	private final int DRIVE_LEFT = 2;
	private final int DRIVE_RIGHT = 3;
	private final int DRIVE_BACKWARD = 4;
	private final int LOOK = 5;
	private final int WALL = 1;
	int posx = 0;
	int posy = 0;
	private int[][] maze;
	
	// Simulationsmaße
	private final int simRobotYSize = 20;
	private final int simRobotXSize = 15;
	private final int simRobotHeadUltrasonicWidth = 5;
	
	// Darstellung in Simulation (für Javakonsole)
	private final char simRobotBodyImaging = 'O';
	private final char simRobotHeadImaging = 'H';

	RoboSim(){
		this.roboName = "Robo" + (++numberOfRobots);
	}
	
	@Override
	public void doAction(int action) {
		switch (action) {
		case STOP:
			break;
		case DRIVE_FORWARD:
			posx += 1;
			break;
		case DRIVE_LEFT:
			posy -= 1;
			break;
		case DRIVE_RIGHT:
			posy += 1;
			break;
		case DRIVE_BACKWARD:
			posx -= 1;
			break;
		case LOOK:
			this.look();
			break;
		}
	}

	@Override
	public void fetchData(int pos) {
		// front
		int front = 0;
		for (int i = posx; i < maze.length; i++) {
			if (maze[i][posy] == WALL) {
				front++;
			}
		}
		// left
		int left = 0;
		for (int i = posy; i >= maze.length; i--) {
			if (maze[posx][i] == WALL) {
				left++;
			}
		}
		// right
		int right = 0;
		for (int i = posy; i < maze.length; i++) {
			if (maze[posx][i] == WALL) {
				right++;
			}
		}
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
