package br.com.cliente;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainCliente {
	public static void main(String[] args) {
		try {
			
			// intervalo em milisegundos
			Cliente dorflex = new Cliente("Tomar Dorflex", 30, 10);
			Cliente prova = new Cliente("Prova de SO", 1, 12);
			Cliente medico = new Cliente("Médico amanhã",3, 15);
			
			ExecutorService threadPool = Executors.newCachedThreadPool();
			threadPool.execute(dorflex);
			threadPool.execute(prova);
			threadPool.execute(medico);
			
			threadPool.shutdown();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
