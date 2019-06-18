package roboter;

import java.nio.ReadOnlyBufferException;

import algorithmus.QLearningAgent;
import lejos.hardware.Button;

public class Main {

	public static void main(String[] args) {
		
		
		QLearningAgent agent = new QLearningAgent();
		/**
		 * R�ckw�rtsfahren keine Sensorik hinten
		 * 
		 */
		LegoRoboter robot = new LegoRoboter();
		int counter = 0;
		int treshold = 1000;
		int r = 0;
		// counter < treshold && 
		//Button.readButtons() != 0
		while(Button.readButtons() == 0 && r!= 1) {
			counter++;
			robot.look(); // watch and measure data
			int s = robot.findBarrier(); // find the position of the barrier
			int a = agent.chooseAction(s);
			robot.doAction(a);
			int sNext = robot.findBarrier();
			System.out.println(sNext);// find next state
			if(robot.isBumped())
				r = -1;
			if(a == 3)
				r = -1;
			if(robot.isGoal())
				r = 1;
			agent.learn(s, sNext, a, r);
			s = sNext;
			System.out.println("Reward: " + r);
			System.out.println("Durchlauf: " + counter);
//			buttonId = Button.waitForAnyEvent();
		}
	}

}
