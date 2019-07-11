package roboter;

import java.nio.ReadOnlyBufferException;

import algorithmus.QLearningAgent;
import lejos.hardware.Button;

public class Main {

	public static void main(String[] args) {
		/*
		 * Idee:
		 * immer links und rechts kein Hinderniss, auch in diese Richtung fahren
		 */
		double[][] q = new double[][] { 
		{ 1, 1, 1, 0 }, 
		{ 0, 1, 1, 0 }, 
		{ 1, 0, 1, 0 }, 
		{ 1, 1, 0, 0 },
		{ 0, 0, 1, 0 }, 
		{ 0, 1, 0, 0 }, 
		{ 1, 0, 0, 0.5 }, 
		{ 0, 0, 0, 1 }, 
		{ 0, 0, 0, 1 }, 
		};
//		// Q- Table einfache Lösung
//		double[][] q = new double[][] { 
//			{1.8925222018615637,-1.6720767275781938,-1.3298117361950599,0.7581207530731985},
//			{-2.2830799720694657,-2.1840289549679994,-1.9823653725869497,-0.8064371074889148},
//			{0.1795680772885344,-1.8342113243155946,-1.296250322250285,-1.3314859484171748},
//			{-0.2507670539496468,-1.5791660949898456,-1.897924454233506,-0.798356408014697},
//			{-1.332461799407045,-2.3616560916671845,1.7067888272512564,-0.8817035848897987},
//			{-1.8424386663459094,-1.9046186461629586,-2.0309464942955566,-0.5597892099581576},
//			{0.09938197935024759,0.0044593753950806555,0.04374147170336519,0.046595055429454954},
//			{0.04317749601970472,0.019943339516849214,0.0736414950657445,0.03269588798431056},
//			{-0.2914694480661803,-2.1833041548758105,-1.598473509842588,0.4921916062968945}
//		};
//		double[][] q = new double[][] { 
//			{-0.6044852465355826,-1.7144963700266431,-1.8052195170869871,-1.0054832086754077},
//			{-2.104618325183619,-1.0036472065483422,-2.0693826505906854,-0.6953031262426599},
//			{-0.5408535697660133,-1.6537010754733923,-1.9375866293244608,-0.5324443999623595},
//			{-1.0754812360860306,-1.7696484527551903,-1.8864261984158737,-0.7298703561973163},
//			{-2.4128918254495098,-2.387989335987669,-2.3712964274200523,-0.5627786685770462},
//			{-2.0442314505168673,-2.071888911814214,-2.1860438864844274,-0.9351859848608172},
//			{0.04014993098111306,0.05270533033440415,0.07809260174375367,0.04217646424664474},
//			{0.039957082060525596,0.05664533729191133,0.05522225352303808,0.03299245123881404},
//			{-1.7349370493168972,-2.0856001159914013,-2.4230088708873776,-0.8953910970267315}
//
//		};
		QLearningAgent agent = new QLearningAgent(q);
		/**
		 * Rï¿½ckwï¿½rtsfahren keine Sensorik hinten
		 * 
		 */
		LegoRoboter robot = new LegoRoboter();
		int counter = 0;
		int treshold = 1000;
		double r = 0;
		// counter < treshold && 
		//Button.readButtons() != 0
		while(Button.readButtons() == 0){//&& r!= 1) {
			counter++;
			robot.look(); // watch and measure data
			int s = robot.findBarrier(); // find the position of the barrier
			int a = agent.chooseAction(s);
			robot.doAction(a);
			int sNext = robot.findBarrier();
			System.out.println(sNext);// find next state
			// is not bumped
//			r = 0.5;
			r = 0;
			if(a == 3)
				r = -0.25;
			if(robot.isGoal())
				r = 1;
			if(robot.isBumped())
				r = -1;
			agent.learn(s, sNext, a, r);
			s = sNext;
			System.out.println("Reward: " + r);
			System.out.println("Durchlauf: " + counter);
//			buttonId = Button.waitForAnyEvent();
		}
	}

}
