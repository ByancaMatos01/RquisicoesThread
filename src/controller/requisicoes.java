package controller;

import java.util.concurrent.Semaphore;

public class requisicoes extends Thread {

	private int id;
	private Semaphore sema;
	
	public requisicoes(int id, Semaphore sema) {
		this.id=id;
		this.sema=sema;
		
	}
	@Override
	public void run() {
		separa();
	}
	private void separa() {
		if(id%3==1) {
			for(int i=0; i<2;i++) {
				calc(200,800);
				transBD(1000);
				
			}
		}else if(id%3==2) {
			for(int i=0; i<3;i++) 
			{
				calc(500,1000);
				transBD(1500); 
			}
		}else if(id%3==0) {
			for(int i=0; i<2;i++) 
			{
				calc(1000,1000);
				transBD(1500); 
			}
		}
		
			
		
		
	}
		
	private void calc(int i, int j) {
        int time = (int) (Math.random() * (i - j) + j);
        System.out.println("Thread " + id + " fazendo calculos para " + time + " segundos");
        try {
            Thread.sleep(time * 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	private void transBD(int i) {
		 int time = (int) (Math.random() * (i - MIN_PRIORITY) + MAX_PRIORITY);
	        System.out.println("Thread " + id + " fazendo transacao para " + time + " segundos");
	        try {
	            sema.acquire();
	            System.err.println("Thread " + id + " tem semaforo BD");
	            Thread.sleep(time * 1);
	            System.err.println("Thread " + id + " lancado semaforo BD");
	            sema.release();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
		
	}
	

