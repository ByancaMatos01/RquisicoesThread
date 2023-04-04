package View;

import java.util.concurrent.Semaphore;

import controller.requisicoes;

public class Main {
public static void main(String[] args) {
	int id, permissoes=1; // o id e as permissoes=1;
	Semaphore sema=new Semaphore(permissoes);
	for(id=1;id<22;id++) { // for que vai startar as Threads 
		requisicoes t= new requisicoes(id,sema); // passando o id e semaforo
		t.start();
	}
}
}
