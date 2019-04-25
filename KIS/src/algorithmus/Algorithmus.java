package algorithmus;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Algorithmus {
	
	private static final int TRESHOLD = 500;
	private static final int P = 500; //number of population
	private static final int LENGTH = 20; //length of bitstring, 8 bits for actions, 12 bits for sensors
	private static final double R = 0.05; //crossoverrate
	private static final double M = 0.01; //mutationrate
	private int[][] population = new int[P][LENGTH];
	
	// 00 = stop
	// 01 = rechts
	// 10 = links
	// 11 = geradeaus
	
	public void initPopulation() {
		//TODO: Array of Robots
		int [] individium = new int[LENGTH];
		Random rnd = ThreadLocalRandom.current();
		for (int j = 0; j < population.length; j++){
			for(int i= 0; i<individium.length; i++) {
				individium[i] = rnd.nextInt(2);
			}
			population[j] = individium;
		}
	}
	
	private void calculateFitness() {
		
	}
	
	public void simulate() {
		int training = 0;
		while(training < TRESHOLD) {
			// TODO: calculate Fitness
			
		}
	}
	

}
