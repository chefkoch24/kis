package roboter;

import algorithmus.QLearningAgent;

public class Main {

	public static void main(String[] args) {
		
		
		QLearningAgent agent = new QLearningAgent();
		LegoRoboter robot = new LegoRoboter();
		int counter = 0;
		int treshold = 10000;
		int aBefore = 0;
		while(counter < treshold) {
			robot.look(); // watch and measure data
			int s = robot.findBarrier(); // find the position of the barrier
			int a = agent.chooseAction(s); 
			// do action 1 second
			robot.doAction(a);
			int sNext = robot.findBarrier();
			System.out.println(sNext);// find next state
			int r = 0;
			if(robot.isBumped())
				r = -10;
			// if robot are not moving
			if(a == 0)
				r = -1;
			if(a == aBefore)
				r = -1;
//			if(robot.isGoal())
//				r = 1;
			agent.learn(s, sNext, a, r);
			s = sNext;
			a = aBefore;
		}
	}

}
