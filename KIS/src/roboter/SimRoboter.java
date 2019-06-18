package roboter;

import java.util.ArrayList;
import java.util.List;

import labyrinth.Labyrinth;

public class SimRoboter implements Roboter {
	private static int numberOfRobots = 0;

	private final String roboName;

	private int[] position;
	private int headPosition;
	// private float[] data = new float[3];
	// private final int STOP = 0;
	// private final int DRIVE_FORWARD = 1;
	// private final int DRIVE_LEFT = 2;
	// private final int DRIVE_RIGHT = 3;
	// private final int DRIVE_BACKWARD = 4;
	// private final int LOOK = 5;
	// private final int WALL = 1;
	// int posx = 0;
	// int posy = 0;
	// private int[][] maze;

	// Simulationsmaße
	// Originalmaße
	// private final int simRobotYSize = 20;
	// private final int simRobotXSize = 15;
	// private final int simRobotHeadUltrasonicWidth = 5;

	public SimRoboter(int headPosition, int[] position) {
		this.roboName = "Robo_" + (++numberOfRobots);
		
		this.position = position;
		this.headPosition = headPosition;
	}
	
	public int getHeadPosition() {
		return this.headPosition;
	}
	
	public int[] getPosition() {
		return this.position;
	}

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

	// @Override
	// public void doAction(int action) {
	// switch (action) {
	// case STOP:
	// break;
	// case DRIVE_FORWARD:
	// // posx += 1;
	// break;
	// case DRIVE_LEFT:
	// posy -= 1;
	// break;
	// case DRIVE_RIGHT:
	// posy += 1;
	// break;
	// case DRIVE_BACKWARD:
	// posx -= 1;
	// break;
	// case LOOK:
	// this.look();
	// break;
	// }
	// }
	//
	// @Override
	// public void fetchData(int pos) {
	// // front
	// int front = 0;
	// for (int i = posx; i < maze.length; i++) {
	// if (maze[i][posy] == WALL) {
	// front++;
	// }
	// }
	// // left
	// int left = 0;
	// for (int i = posy; i >= maze.length; i--) {
	// if (maze[posx][i] == WALL) {
	// left++;
	// }
	// }
	// // right
	// int right = 0;
	// for (int i = posy; i < maze.length; i++) {
	// if (maze[posx][i] == WALL) {
	// right++;
	// }
	// }
	// }


}
