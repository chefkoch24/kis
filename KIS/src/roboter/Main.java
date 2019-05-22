package roboter;

import algorithmus.QLearningAgent;

public class Main {

	public static void main(String[] args) {
		
		
		QLearningAgent agent = new QLearningAgent();
		/**
		 * Rückwärtsfahren keine Sensorik hinten
		 * links/ rechts hinten rausdrehen?
		 * status abhängig von bumped?
		 * 
		 */
		LegoRoboter robot = new LegoRoboter();
		int counter = 0;
		int treshold = 100;
		while(counter < treshold) {
			counter++;
			robot.look(); // watch and measure data
			int s = robot.findBarrier(); // find the position of the barrier
			int a = agent.chooseAction(s);
			robot.doAction(a);
			int sNext = robot.findBarrier();
			System.out.println(sNext);// find next state
			int r = 0;
			if(robot.isBumped())
				r = -10;
			else{
				r = 1;
			}
			// if robot are not moving
			if(a == 0)
				r = -1;
//			if(robot.isGoal())
//				r = 1;
			agent.learn(s, sNext, a, r);
			s = sNext;
			System.out.println("Reward: " + r);
			System.out.println("Durchlauf: " + counter);
		}
	}

}
