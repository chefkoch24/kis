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
	 * M�gliche Aktionen STOP = 0, DRIVE_FORWARD = 1, DRIVE_LEFT = 2,
	 * DRIVE_RIGHT = 3, DRIVE_BACKWARD = 4, LOOK_FORWARD = 5, int LOOK_LEFT = 6,
	 * LOOK_RIGHT = 7;
	 */

	// Abh�ngig von Abastand links, rechts, vorne, geschwindigkeit,
	public QLearningAgent(int barrier, float speed) {
		int s;
		q = new double[s][POSSIBLE_ACTIONS];

	}

	/**
	 * Lernt durch die �bergebenen Zust�nde, ob die Aktion erfolgreich war und
	 * speichert die Werte in das q-array.
	 * @param s: Aktueller Zustand
	 * @param s_next: N�chster Zustand
	 * @param a: Aktion
	 * @param r: Belohnung
	 */
	public void learn(int s, int s_next, int a, double r) {
		int aIndex = a + 1;
		this.q[s][aIndex] += this.alpha * (r + this.gamma * (this.q[s_next][actionWithBestRating(s_next)]) - q[s][aIndex]);

	}

	/**
	 * W�hlt die Aktion mit der besten Rate aus
	 * @param s: Zustand s
	 * @return: Gibt die Aktion als int zur�ck.
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
	 * W�hlt eine zuf�llige Aktion anhand des Zustands aus
	 * @param s: Zustand
	 * @return: Gibt die Aktion als int zur�ck.
	 */
	public int chooseAction(int s) {
		int a = 0;
		if (Math.random() < this.epsilon && trained == false) {
			// Bound = 2, because we want 1 to be inclusive
			a = ThreadLocalRandom.current().nextInt(-1, 2);
		} else {
			a = actionWithBestRating(s) - 1;
		}
		return a;
	}

}
