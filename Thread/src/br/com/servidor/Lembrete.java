package br.com.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Lembrete implements Runnable{

	/**
	 * Utilizado na comunicação com o cliente
	 */
	private Socket socket;

	/**
	 * Nome do lembrete
	 */
	private String nome;

	/**
	 * Intervalo em Segundos
	 */
	private int intervalo;

	/**
	 * Total de vezes que será executado
	 */
	private int total;

	public Lembrete(Socket socket) {
		this.socket = socket;
	}

	/**
	 * Recebe os dados do cliente
	 * 
	 * @throws IOException
	 */
	private void receberDados() throws IOException {
		DataInputStream data = new DataInputStream(this.socket.getInputStream());

		// Lê String no formato Unicode
		this.nome = data.readUTF();

		this.intervalo = data.readInt();
		this.total = data.readInt();
	}

	@Override
	public void run() {
		try {
			receberDados();

			// Envia os lembretes por TCP
			DataOutputStream dataOutputStream = new DataOutputStream(this.socket.getOutputStream());

			for (int i = 0; i < this.total; i++) {
				dataOutputStream.writeUTF(nome + " (" + i + ")");
				Thread.sleep(intervalo * 1000);
			}
			
			socket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
