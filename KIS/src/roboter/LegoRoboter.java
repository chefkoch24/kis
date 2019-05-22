package roboter;

import java.util.concurrent.TimeUnit;

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

	private final int SPEED = 100;
	private final int SPEED_CURVE = 0;
	private final int SPEED_LOOK = 100;
	private final int LEFT = 0;
	private final int FRONT = 1;
	private final int RIGHT = 2;
	private final double MIN_DISTANCE = 0.1;
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
	private float[] sample = new float[3];
//	private float[] sample = new float[3];
	// three measurements, one from front, one from left, one from right
//	private float[][] data = new float[3][sample.length];
	private float touched[] = new float[2];
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
		}
	}

	private void printData() {
		for (int i = 0; i < sample.length; i++) {
			System.out.print(sample[i] + ", ");
		}
		System.out.println();
	}

	/**
	 * measure one distance an write it in data array
	 * 
	 * @param pos
	 */
	@Override
	public void fetchData(int pos) {
		us.fetchSample(sample, pos);
//		data[pos] = sample;
//		System.out.print(sample[0]);
		touch1.fetchSample(this.touched, 0);
		touch2.fetchSample(this.touched, 1);
	}

	/**
	 * Detect an barrier on with the distance
	 * 
	 * @return 0 to 7, depend on which location it is detected
	 */
	@Override
	public int findBarrier() {
		printData();
		// front = 1
		if (sample[LEFT] > MIN_DISTANCE && sample[FRONT] < MIN_DISTANCE && sample[RIGHT] > MIN_DISTANCE) {
			// front + bumped
			if (isBumped()) {
				return 8;
			}
			return 1;
		}
		// left = 2
		if (sample[LEFT] < MIN_DISTANCE && sample[FRONT] > MIN_DISTANCE && sample[RIGHT] > MIN_DISTANCE) {
			// left + bumped
			if (isBumped()) {
				return 9;
			}
			return 2;
		}
		// right = 3
		if (sample[LEFT] > MIN_DISTANCE && sample[FRONT] > MIN_DISTANCE && sample[RIGHT] < MIN_DISTANCE) {
			// right + bumped
			if (isBumped()) {
				return 10;
			}
			return 3;
		}
		// front + left = 4
		if (sample[LEFT] < MIN_DISTANCE && sample[FRONT] < MIN_DISTANCE && sample[RIGHT] > MIN_DISTANCE) {
			// front + left + bumped
			if (isBumped()) {
				return 11;
			}
			return 4;
		}
		// front + right = 5
		if (sample[LEFT] > MIN_DISTANCE && sample[FRONT] < MIN_DISTANCE && sample[RIGHT] < MIN_DISTANCE) {
			// front + right + bumped
			if (isBumped()) {
				return 12;
			}
			return 5;
		}
		// left + right = 6
		if (sample[LEFT] < MIN_DISTANCE && sample[FRONT] > MIN_DISTANCE && sample[RIGHT] < MIN_DISTANCE) {
			// left + right + bumped
			if (isBumped()) {
				return 13;
			}
			return 6;
		}
		// front + left + right = 7
		if (sample[LEFT] < MIN_DISTANCE && sample[FRONT] < MIN_DISTANCE && sample[RIGHT] < MIN_DISTANCE) {
			// front + left + right + bumped
			if (isBumped()) {
				return 14;
			}
			return 7;
		}
		return 0;
	}

	public void lookRight() {
		throat.setSpeed(SPEED_LOOK);
		throat.rotate(90);
		// measure and prints right distance
		fetchData(2);
		throat.rotate(-90);
	}

	public void lookLeft() {
		throat.setSpeed(SPEED_LOOK);
		throat.rotate(-90);
		// measure and prints left distance
		fetchData(0);
		throat.rotate(90);
	}

	/**
	 * measure data from left, front and right
	 */
	@Override
	public void look() {
		lookLeft();
		// data from front
		fetchData(1);
		lookRight();
	}

	@Override
	public boolean isBumped() {
		touch1.fetchSample(this.touched, 0);
		touch2.fetchSample(this.touched, 1);
		// Abfrage Tastsensor
		if (touched[0] == 0 && touched[1] == 0) {
			return false;
		}
		System.out.println("bumped");
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
		left.forward();
		right.forward();
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Automatisch generierter Erfassungsblock
			e.printStackTrace();
		}
		left.setSpeed(0);
		right.setSpeed(0);
		// stop();
	}

	@Override
	public void backward() {
		left.setSpeed(SPEED);
		right.setSpeed(SPEED);
		left.backward();
		right.backward();
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Automatisch generierter Erfassungsblock
			e.printStackTrace();
		}
		left.setSpeed(0);
		right.setSpeed(0);
	}

	@Override
	public void right() {
		left.setSpeed(SPEED);
		left.rotate(360);
	}

	@Override
	public void left() {
		right.setSpeed(SPEED);
		right.rotate(360);
	}

	@Override
	public void stop() {
		left.stop();
		right.stop();
	}

}
