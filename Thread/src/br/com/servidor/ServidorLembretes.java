package br.com.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorLembretes implements Runnable {

	private boolean executando;
	
	@Override
	public void run() {
		executando = true;
		
		try {
			ServerSocket server = new ServerSocket(5000);
			System.out.println("Rodando!!");

			ExecutorService threadPool = Executors.newCachedThreadPool();

			// Sem previsão de parada
			while (executando) {
				Socket socket = server.accept();

				Lembrete lembrete = new Lembrete(socket);

				threadPool.execute(lembrete);
			}
			
			server.close();
			threadPool.shutdown();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
