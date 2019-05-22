package roboter;

import java.util.concurrent.TimeUnit;

import lejos.hardware.Button;

public class Start {

	/**
	 * 4 - 8 Seiten Wissenschaftliche Ausarbeitung Mit Abstract, Einleitung,
	 * Auswertung der Versuche, Diskussion, Ausblick Mögliche ähnliche
	 * wissenschaftliche Arbeiten, die sich mit diesem Thema beschäftigt haben.
	 * 
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		LegoRoboter robot = new LegoRoboter();
		robot.backward();
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
