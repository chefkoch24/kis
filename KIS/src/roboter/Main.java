package roboter;

import algorithmus.QLearningAgent;

public class Main {
	
	private static final int BARRIER_MAX = 2;
	private static final int PLACE_OF_BARRIER_MAX = 3;

	public static void main(String[] args) {
		
		
		QLearningAgent agent = new QLearningAgent(BARRIER_MAX, PLACE_OF_BARRIER_MAX);
		Roboter r = new Roboter();
		r.lookLeft();
		r.lookRight();
		r.fetchData();
		int counter = 0;
		int treshold = 10000;
		while(counter < treshold) {
			/*
			 * isBarrier und placeOfBarrier trennen oder durch eine Zahl vereinheitlichen? 0 = keine barriere, 1 = barrier links
			 * was machen wir bei ecken oder sackgassen? -> 2-3 barriers
			 */
			int isBarrier = r.isBarrier(data);
			int placeOfBarrier = ;
			int s = agent.calculateState(isBarrier, BARRIER_MAX, placeOfBarrier);
			
		}
		
//		int [][] a = new int [3][4];
//		int [] b = {0,1,2,4};
//		a[0] = b;
//		System.out.println(a[0].length);
	}

}
