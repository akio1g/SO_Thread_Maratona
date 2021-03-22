package view;

import java.util.concurrent.Semaphore;

import controller.corredoresThread;

public class principal {

	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(1);
		for(int id = 1;id<=4;id++) {
			Thread ct = new corredoresThread(semaforo,id);
			ct.start();
		}
	}

}
