package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class corredoresThread extends Thread {
	private int dist;
	private Semaphore semaforo;
	private int id;
	private int corredor = 200;
	private int passo;
	private int distRestante = 200;

	public corredoresThread(Semaphore semaforo, int id) {
		this.semaforo = semaforo;
		this.id = id;
	}

	@Override
	public void run() {
		passo();
		if (distRestante == 0) {
			int seg = randomizar(1000, 2000);
			try {
				semaforo.acquire();
				sleep(seg);
				System.out.println("ID#" + id + ": Passou a porta");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo.release();
			}
		}
	}

	private void passo() {
		while (dist < corredor) {
			try {
				if (distRestante >= 6) {
					passo = randomizar(4, 6);
					dist += passo;
					distRestante = corredor - dist;
					sleep(1000);
					System.out.println("ID#" + id + ": Andou " + passo + "m, faltam " + distRestante);
				} else {
					passo = distRestante;
					dist += passo;
					distRestante = corredor - dist;
					sleep(300);
					System.out.println("ID#" + id + ": Andou " + passo + "m, faltam " + distRestante);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public int randomizar(int min, int max) {
		Random r = new Random();
		int x = r.nextInt(max + 1);
		while (x < min) {
			x = r.nextInt(max + 1);
		}
		return x;
	}
}
