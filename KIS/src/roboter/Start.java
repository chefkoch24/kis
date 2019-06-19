package roboter;

import java.util.concurrent.TimeUnit;

import algorithmus.QLearningAgent;
import lejos.hardware.Button;

public class Start {

	/**
	 * 4 - 8 Seiten Wissenschaftliche Ausarbeitung Mit Abstract, Einleitung,
	 * Auswertung der Versuche, Diskussion, Ausblick M�gliche �hnliche
	 * wissenschaftliche Arbeiten, die sich mit diesem Thema besch�ftigt haben.
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
//		LegoRoboter robot = new LegoRoboter();
//		while(Button.readButtons() == 0){
//			System.out.println(robot.isGoal());
//		}
//		double[][] q = new double[][] { 
//			{ 1, 0, 0, 0 }, 
//			{ 0, 0, 0, 1 }, 
//			{ 1, 0, 0.5, 0 }, 
//			{ 1, 0.5, 0, 0 },
//			{ 0, 0, 1, 0 }, 
//			{ 0, 1, 0, 0 }, 
//			{ 1, 0, 0, 0.5 }, 
//			{ 0, 0, 0, 1 }, 
//			{ 0, 0, 0, 1 }, 
//			{ 0, 0, 0, 1 },
//			{ 0, 0, 0, 1 }, 
//			{ 0, 0, 0, 1 }, 
//			{ 0, 0, 0, 1 }, 
//			{ 0, 0, 0, 1 }, 
//			{ 0, 0, 0, 1 } 
//			};
		double[][] q = new double[][] { 
			{ 1, 0, 0, 0 }, 
			{ 0, 0, 0, 1 }, 
			{ 1, 0, 0.5, 0 }, 
			{ 1, 0.5, 0, 0 },
			{ 0, 0, 1, 0 }, 
			{ 0, 1, 0, 0 }, 
			{ 1, 0, 0, 0.5 }, 
			{ 0, 0, 0, 1 }
		};
		QLearningAgent qa1 = new QLearningAgent(q);
		QLearningAgent qa2 = new QLearningAgent();

//		robot.backward();
//		robot.left();
//		robot.forward();
//		robot.right();
//		for(int i = 0; i< 10; i++){
//			System.out.println( Math.random() / 10);
//		}
//		System.out.println("Finished");
		/*
		 * Turn left and turn right missing
		 */
//		boolean notBumped = true;
//		int i = 0;
//		while (notBumped) {
//			robot.forward();
//			robot.look();
//			notBumped = !robot.isBumped();
//			System.out.println("not bumped");
//			i++;
//		}
//		robot.left();
//		robot.forward();
//		robot.right();
//		System.out.println("bumped");
//		Button.waitForAnyPress();
	}

}
