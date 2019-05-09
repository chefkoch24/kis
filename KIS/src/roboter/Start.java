package roboter;

import lejos.hardware.Button;

public class Start {

	/**
	 * 4 - 8 Seiten Wissenschaftliche Ausarbeitung
	 * Mit Abstract, Einleitung, Auswertung der Versuche, Diskussion, Ausblick
	 * Mögliche ähnliche wissenschaftliche Arbeiten, die sich mit diesem Thema 
	 * beschäftigt haben.
	 * @param args
	 */
	public static void main(String[] args) {
		LegoRoboter robot = new LegoRoboter();
//		TODO: forward = backward
//		left = right
		robot.lookLeft();
		robot.lookRight();
		robot.lookRight();
		robot.lookLeft();
		robot.lookLeft();
		robot.lookRight();
		robot.lookRight();
		robot.lookLeft();
		System.out.println("Finished");
		Button.waitForAnyPress();
		/*while(!robot.isBumped()){
			robot.forward();
			System.out.println("not bumped");
		}
		System.out.println("bumped");*/
		
	}

}
