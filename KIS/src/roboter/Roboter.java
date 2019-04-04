package roboter;

import lejos.hardware.motor.Motor;

public class Roboter {
	
	private final int SPEED = 400;
	private final int SPEED_CURVE = 100;
	
	//TODO: sensors
	
	public void forward(int speed){
		Motor.A.setSpeed(speed);
		Motor.B.setSpeed(speed);
		Motor.A.backward();
		Motor.B.backward();
	}
	public void backward(int speed){
		Motor.A.setSpeed(speed);
		Motor.B.setSpeed(speed);
		Motor.A.forward();
		Motor.B.forward();
	}
	public void left(int speed, int speedCurve){
		Motor.A.setSpeed(speed);
		Motor.B.setSpeed(speedCurve);
		Motor.A.backward();
		Motor.B.backward();
	}
	public void right(int speed, int speedCurve){
		Motor.A.setSpeed(speedCurve);
		Motor.B.setSpeed(speed);
		Motor.A.backward();
		Motor.B.backward();
	}
	
	public void stop(){
		Motor.A.stop();
		Motor.B.stop();
	}

}
