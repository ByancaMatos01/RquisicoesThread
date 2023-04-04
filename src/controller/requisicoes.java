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
	private void separa() { // separando se for id com resto 1
		if(id%3==1) {
			for(int i=0; i<2;i++) { // vai fazer duas vezes o calculo e transicões 
				calc(200,800);
				transBD(1000);
				
			}
		}else if(id%3==2) { // vai fazer 3 vezes o calculo e as transições 
			for(int i=0; i<3;i++) 
			{
				calc(500,1000);
				transBD(1500); 
			}
		}else if(id%3==0) { // vai fazer duas vezes o calculo e as transições 
			for(int i=0; i<2;i++) 
			{
				calc(1000,1000);
				transBD(1500); 
			}
		}
		
			
		
		
	}
		
	private void calc(int i, int j) { /// aqui e paramentro do valor de transBD e calc
        int time = (int) (Math.random() * (i - j) + j);  // vai subtrair o valor do transBD - Calc,  depois somar mais o Calc e o valor vai ser em time 
        System.out.println("Thread " + id + " fazendo calculos para " + time + " segundos");
        try {
            Thread.sleep(time * 1); // diminui tempo para rodar mais rapido 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	private void transBD(int i) {
		 int time = (int) (Math.random() * (i - MIN_PRIORITY) + MAX_PRIORITY); //pega o valor da transbd  menos o tempo minimo da thread e depois somar o valor maximo da thread e multiplicar no random 
	        System.out.println("Thread " + id + " fazendo transacao para " + time + " segundos");
	        try {
	            sema.acquire();
	            System.err.println("Thread " + id + " tem semaforo BD");
	            Thread.sleep(time * 1); // tempo 
	            System.err.println("Thread " + id + " lancado semaforo BD");
	            sema.release();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
		
	}
	

