package algorithmus;

import java.util.concurrent.ThreadLocalRandom;

public class QLearningAgent {

	private double epsilon = 0.1;
	private double alpha = 0.01; // Lernrate (0..1)
	private double gamma = 0.9; // Bewertungsfaktor (0..1)
	private double q[][]; // Q-Learning-Array
	private static final int POSSIBLE_ACTIONS = 8;
	boolean trained = false; 
	/*
	 * possible actions: STOP = 0, DRIVE_FORWARD = 1, DRIVE_LEFT = 2,
	 * DRIVE_RIGHT = 3, DRIVE_BACKWARD = 4, LOOK_FORWARD = 5, int LOOK_LEFT = 6,
	 * LOOK_RIGHT = 7;
	 */

	// Abhängig von Abstand barriere, ort der barriere, geschwindigkeit,
	// 3 mögliche Orte der Barriere -> vorne = 0, links = 1, rechts = 2
	public QLearningAgent(int barrierMax, int placeOfBarrier) {
		int s = barrierMax + (placeOfBarrier * barrierMax);
		this.q = new double[s][POSSIBLE_ACTIONS];

	}

	/**
	 * Lernt durch die übergebenen Zustände, ob die Aktion erfolgreich war und
	 * speichert die Werte in das q-array.
	 * @param s: Aktueller Zustand
	 * @param s_next: Nächster Zustand
	 * @param a: Aktion
	 * @param r: Belohnung
	 */
	public void learn(int s, int s_next, int a, double r) {
		int aIndex = a + 1;
		this.q[s][aIndex] += this.alpha * (r + this.gamma * (this.q[s_next][actionWithBestRating(s_next)]) - q[s][aIndex]);

	}

	/**
	 * Wählt die Aktion mit der besten Rate aus
	 * @param s: Zustand s
	 * @return: Gibt die Aktion als int zurück.
	 */
	public int actionWithBestRating(int s) {
		double max = 0;
		int index = 0;
		for (int i = 0; i < POSSIBLE_ACTIONS; i++) {
			if (this.q[s][i] > max) {
				max = this.q[s][i];
				index = i;
			}
		}
		return index;
	}	

	/**
	 * Wählt eine zufällige Aktion anhand des Zustands aus
	 * @param s: Zustand
	 * @return: Gibt die Aktion als int zurück.
	 */
	public int chooseAction(int s) {
		int a = 0;
		if (Math.random() < this.epsilon && this.trained == false) {
			// Bound = 8, because we want 7 to be inclusive
			a = ThreadLocalRandom.current().nextInt(0, 8);
		} else {
			a = actionWithBestRating(s);
		}
		return a;
	}
	
	public int calculateState(int barrier, int barrierMax, int placeOfBarrier) {
		return barrier + (placeOfBarrier * barrierMax);
}

}