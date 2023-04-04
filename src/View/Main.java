package View;

import java.util.concurrent.Semaphore;

import controller.requisicoes;

public class Main {
public static void main(String[] args) {
	int id, permissoes=1;
	Semaphore sema=new Semaphore(permissoes);
	for(id=1;id<22;id++) {
		requisicoes t= new requisicoes(id,sema);
		t.start();
	}
}
}
