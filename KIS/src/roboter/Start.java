package roboter;

public class Start {

	public static void main(String[] args) {
		LegoRoboter robot = new LegoRoboter();
//		TODO: forward = backward
//		left = right
		robot.lookLeft();
		robot.lookRight();
		robot.lookRight();
		robot.lookLeft();
		/*while(!robot.isBumped()){
			robot.forward();
			System.out.println("not bumped");
		}
		System.out.println("bumped");*/
		
	}

}
