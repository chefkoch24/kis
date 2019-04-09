package roboter;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.*;
import lejos.utility.Delay;

public class Roboter {
	
	private final int SPEED = 400;
	private final int SPEED_CURVE = 100;
	private final int SPEED_LOOK = 100;
	private NXTRegulatedMotor left = Motor.D;
	private NXTRegulatedMotor right = Motor.A;
	private SensorModes distanceSensor = new EV3UltrasonicSensor(SensorPort.S4);
	private SampleProvider us = distanceSensor.getMode("Distance");
	private NXTRegulatedMotor throat = Motor.B;
	private float [] data = new float[us.sampleSize()];
	
	//TODO: sensors
	public float getDistance(){
		us.fetchSample(data, 0);
		return data[data.length-1];
	}
	public void dataOnDisplay(){
//		float distanz = 10;
		for (int i=0; i<data.length;i++){
			// http://www.lejos.org/ev3/docs/lejos/robotics/SampleProvider.html
			us.fetchSample(data, 0);
			LCD.drawString("" + data[i], i, 1);
		}
		
//		LCD.clearDisplay();
		
	}
	
	public void lookLeft(){
		throat.setSpeed(SPEED_LOOK);
		throat.backward();
		Delay.msDelay(500);
		throat.stop();
	}
	public void lookRight(){
		throat.setSpeed(SPEED_LOOK);
		throat.forward();
		Delay.msDelay(500);
		throat.stop();
	}
	
	public void forward(int speed){
		left.setSpeed(speed);
		right.setSpeed(speed);
		left.backward();
		right.backward();
	}
	public void backward(int speed){
		left.setSpeed(speed);
		right.setSpeed(speed);
		left.forward();
		left.forward();
	}
	public void left(int speed, int speedCurve){
		left.setSpeed(speed);
		right.setSpeed(speedCurve);
		left.backward();
		right.backward();
	}
	public void right(int speed, int speedCurve){
		left.setSpeed(speedCurve);
		right.setSpeed(speed);
		left.backward();
		right.backward();
	}
	
	public void stop(){
		left.stop();
		right.stop();
	}

}
