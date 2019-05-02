package roboter;

import algorithmus.QLearningAgent;

public class Main {

	public static void main(String[] args) {
		
		
		QLearningAgent agent = new QLearningAgent();
		Roboter robot = new Roboter();
		int counter = 0;
		int treshold = 10000;
		while(counter < treshold) {
			int s = robot.findBarrier();
			int a = agent.chooseAction(s);
			robot.doAction(a);
			int sNext = robot.findBarrier();
			int r = 0;
			if(robot.isBumped())
				r = -1;
			if(robot.isGoal())
				r = 1;
			agent.learn(s, sNext, a, r);
			s = sNext;
		}
	}

}
