package roboter;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.*;
import lejos.utility.Delay;

public class LegoRoboter implements Roboter {

	private final int STOP = 0;
	private final int DRIVE_FORWARD = 1;
	private final int DRIVE_LEFT = 2;
	private final int DRIVE_RIGHT = 3;
	private final int DRIVE_BACKWARD = 4;
	private final int LOOK = 5;

	private final int SPEED = 400;
	private final int SPEED_CURVE = 100;
	private final int SPEED_LOOK = 100;

	private final double MIN_DISTANCE = 0.05;
	private NXTRegulatedMotor left = Motor.D;
	private NXTRegulatedMotor right = Motor.A;
	private SensorModes distanceSensor = new EV3UltrasonicSensor(SensorPort.S3);
	private SensorModes touchSensor1 = new EV3TouchSensor(SensorPort.S1);
	private SampleProvider touch1 = touchSensor1.getMode("Touch");
	private SensorModes touchSensor2 = new EV3TouchSensor(SensorPort.S4);
	private SampleProvider touch2 = touchSensor2.getMode("Touch");
	private SampleProvider us = distanceSensor.getMode("Distance");
	private NXTRegulatedMotor throat = Motor.B;
	// one measurement
	private float[] sample = new float[us.sampleSize()];
//	private float[] sample = new float[3];
	// three measurements, one from front, one from left, one from right
	private float[][] data = new float[3][sample.length];
	/*
	 * TODO: sensor control simulation-algorithm to learn driving parser from
	 * learning to robot
	 */

	// http://www.lejos.org/ev3/docs/lejos/robotics/SampleProvider.html
	@Override
	public void doAction(int action) {
		switch (action) {
		case STOP:
			this.stop();
			break;
		case DRIVE_FORWARD:
			this.forward();
			break;
		case DRIVE_LEFT:
			this.left();
			break;
		case DRIVE_RIGHT:
			this.right();
			break;
		case DRIVE_BACKWARD:
			this.backward();
			break;
		case LOOK:
			this.look();
			break;
		}
	}

	/**
	 * measure one distance an write it in data array
	 * 
	 * @param pos
	 */
	@Override
	public void fetchData(int pos) {
		us.fetchSample(sample, 0);
		data[pos] = sample;
		System.out.print(sample[0]);
	}

	/**
	 * Detect an barrier on with the distance
	 * 
	 * @return 0 to 7, depend on which location it is detected
	 */
	@Override
	public int findBarrier() {
		// front = 1
		if (sample[0] > MIN_DISTANCE && sample[1] < MIN_DISTANCE && sample[2] > MIN_DISTANCE)
			return 1;
		// left = 2
		if (sample[0] < MIN_DISTANCE && sample[1] > MIN_DISTANCE && sample[2] > MIN_DISTANCE)
			return 2;
		// right = 3
		if (sample[0] > MIN_DISTANCE && sample[1] > MIN_DISTANCE && sample[2] < MIN_DISTANCE)
			return 3;
		// front + left = 4
		if (sample[0] < MIN_DISTANCE && sample[1] < MIN_DISTANCE && sample[2] > MIN_DISTANCE)
			return 4;
		// front + right = 5
		if (sample[0] > MIN_DISTANCE && sample[1] < MIN_DISTANCE && sample[2] < MIN_DISTANCE)
			return 5;
		// left + right = 6
		if (sample[0] < MIN_DISTANCE && sample[1] > MIN_DISTANCE && sample[2] < MIN_DISTANCE)
			return 6;
		// front + left + right = 7
		if (sample[0] < MIN_DISTANCE && sample[1] < MIN_DISTANCE && sample[2] < MIN_DISTANCE)
			return 7;
		return 0;
	}
	
	public void lookRight(){
		throat.setSpeed(SPEED_LOOK);
		throat.rotate(90);
		
		//measure and prints right distance
		fetchData(2);
	}
	
	public void lookLeft(){
		throat.setSpeed(SPEED_LOOK);
		throat.rotate(-90);
		
		//measure and prints left distance
		fetchData(0);
	}

	/**
	 * measure data from left, front and right
	 */
	@Override
	public void look() {
		lookLeft();
		throat.rotate(-90);
		fetchData(1);
		lookRight();
		throat.rotate(90);
	}

	@Override
	public boolean isBumped() {
		// Abfrage Tastsensor
		float touched[] = new float[2];
		touch1.fetchSample(touched, 0);
		touch2.fetchSample(touched, 1);
		if(touched[0] == 0 && touched[1] == 0) {
			return false;
		}
		LCD.drawString("bumped: " + touched[0] + "," + touched[1], 0, 1);
		return true;
	}

	@Override
	public boolean isGoal() {
		// TODO: implement color sensors
		return true;
	}

	@Override
	public void forward() {
		left.setSpeed(SPEED);
		right.setSpeed(SPEED);
		left.backward();
		right.backward();
	}

	@Override
	public void backward() {
		left.setSpeed(SPEED);
		right.setSpeed(SPEED);
		left.forward();
		left.forward();
	}

	@Override
	public void left() {
		left.setSpeed(SPEED);
		right.setSpeed(SPEED_CURVE);
		left.backward();
		right.backward();
	}

	@Override
	public void right() {
		left.setSpeed(SPEED_CURVE);
		right.setSpeed(SPEED);
		left.backward();
		right.backward();
	}

	@Override
	public void stop() {
		left.stop();
		right.stop();
	}

}
