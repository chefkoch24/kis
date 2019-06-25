package roboter;

import java.nio.ReadOnlyBufferException;

import algorithmus.QLearningAgent;
import lejos.hardware.Button;

public class Main {

	public static void main(String[] args) {
		
		
//		QLearningAgent agent = new QLearningAgent();
//		double[][] q = new double[][] { 
//			{ 1, 0, 0, 0 }, 
//			{ 0, 0, 0, 1 }, 
//			{ 1, 0, 0.5, 0 }, 
//			{ 1, 0.5, 0, 0 },
//			{ 0, 0, 1, 0 }, 
//			{ 0, 1, 0, 0 }, 
//			{ 1, 0, 0, 0.5 }, 
//			{ 0, 0, 0, 1 }
//		};
		/*
		 * Idee:
		 * immer links und rechts kein Hinderniss, auch in diese Richtung fahren
		 */
		double[][] q = new double[][] { 
		{ 1, 1, 1, 0 }, 
		{ 0, 1, 1, 0 }, 
		{ 1, 0, 1, 0 }, 
		{ 1, 1, 0, 0 },
		{ 0, 0, 1, 0 }, 
		{ 0, 1, 0, 0 }, 
		{ 1, 0, 0, 1 }, 
		{ 0, 0, 0, 1 }, 
		{ 0, 0, 0, 1 }, 
		};
//		double[][] q = new double[][] { 
//			{-40.23776162656566,-1.7876720491510736,-2.2295177951442504,-15.391437498452099},
//			{-788.8760865645414,-649.0235905621438,-666.368292394488,-635.7650526287117},
//			{-10.583507571810863,-1.069202265710427,-1.1756819456354666,-1.1392396248141006},
//			{0.7848625924928105,0.4198547857985474,0.00628211288631394,0.05080165324198631},
//			{0.0016520698842145687,0.08458580229342696,0.0851753060794094,0.08149172953466172},
//			{0.07809970249925953,0.04372670939227077,0.021855121074417384,0.07892333965497952},
//			{0.006989033130050726,0.00637476959329345,0.03130132014227742,0.013408042010642307},
//			{0.08537332795644556,0.0018365658295513287,0.058385750614613555,0.03854206557078562}
//		};
		QLearningAgent agent = new QLearningAgent(q);
		/**
		 * R�ckw�rtsfahren keine Sensorik hinten
		 * 
		 */
		LegoRoboter robot = new LegoRoboter();
		int counter = 0;
		int treshold = 1000;
		double r = 0;
		// counter < treshold && 
		//Button.readButtons() != 0
		while(Button.readButtons() == 0 ){//&& r!= 1) {
			counter++;
			robot.look(); // watch and measure data
			int s = robot.findBarrier(); // find the position of the barrier
			int a = agent.chooseAction(s);
			robot.doAction(a);
			int sNext = robot.findBarrier();
			System.out.println(sNext);// find next state
			if(robot.isBumped())
				r = -1;
			// is not bumped
//			r = 0.5;
			r = 0;
			if(a == 3)
				r = -0.25;
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
