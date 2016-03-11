package br.com.servidor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainServidor {
	public static void main(String[] args) {
		// executa o servidor
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
		
		ServidorLembretes servidorLembretes = new ServidorLembretes();
		
		singleThreadExecutor.execute(servidorLembretes);
		
		singleThreadExecutor.shutdown();
	}
}
