package roboter;

public class Start {

	public static void main(String[] args) {
		LegoRoboter robot = new LegoRoboter();
		while(!robot.isBumped()){
			System.out.println("not bumped");
		}
		System.out.println("bumped");
		
	}

}
