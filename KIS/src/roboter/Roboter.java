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
	private final double  LOOK_ANGLE = 90/500;
	private NXTRegulatedMotor left = Motor.D;
	private NXTRegulatedMotor right = Motor.A;
	private SensorModes distanceSensor = new EV3UltrasonicSensor(SensorPort.S4);
	private SampleProvider us = distanceSensor.getMode("Distance");
	private NXTRegulatedMotor throat = Motor.B;
	// one measurement
	private float[] sample = new float[us.sampleSize()];
	// three measurements, one from front, one from left, one from right
	private float[][] data = new float[3][sample.length];
	
/*
 * 	TODO:
 *  sensor control
 *  simulation-algorithm to learn driving
 *  parser from learning to robot
 */
	
	
	
	// http://www.lejos.org/ev3/docs/lejos/robotics/SampleProvider.html
	public void fetchData() {
		// data from the front		
		us.fetchSample(sample, 0);
		data[0] = sample;
		LCD.drawString("" + sample[0], 1, 1);
		// data from left side
		look(90);
		us.fetchSample(sample, 0);
		data[1] = sample;
		LCD.drawString("" + sample[0], 1, 1);
		// data from right side
		look(180);
		us.fetchSample(sample, 0);
		data[2] = sample;
		LCD.drawString("" + sample[0], 1, 1);
		look(90);
	}

	public void lookLeft() {
		throat.setSpeed(SPEED_LOOK);
		throat.backward();
		Delay.msDelay(500);
		throat.stop();
		// measure distance
		fetchData();
		// back to beginning
		throat.forward();
		Delay.msDelay(500);
	}
	
	/**
	 * head with right turn
	 * @param angle in degree
	 */
	public void look(int angle) {
		throat.setSpeed(SPEED_LOOK);
		throat.forward();
		long delay = (long) LOOK_ANGLE*angle;
		Delay.msDelay(delay);
		// measure distance
		fetchData();
	}

	public void lookRight() {
		throat.setSpeed(SPEED_LOOK);
		throat.forward();
		Delay.msDelay(500);
		throat.stop();
		// measure distance
		fetchData();
		// back to beginning
		throat.backward();
		Delay.msDelay(500);
	}

	public void forward(int speed) {
		left.setSpeed(speed);
		right.setSpeed(speed);
		left.backward();
		right.backward();
	}

	public void backward(int speed) {
		left.setSpeed(speed);
		right.setSpeed(speed);
		left.forward();
		left.forward();
	}

	public void left(int speed, int speedCurve) {
		left.setSpeed(speed);
		right.setSpeed(speedCurve);
		left.backward();
		right.backward();
	}

	public void right(int speed, int speedCurve) {
		left.setSpeed(speedCurve);
		right.setSpeed(speed);
		left.backward();
		right.backward();
	}

	public void stop() {
		left.stop();
		right.stop();
	}

}
