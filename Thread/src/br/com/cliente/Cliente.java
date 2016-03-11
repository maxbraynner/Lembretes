package br.com.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente implements Runnable {

	private DataInputStream input;
	private DataOutputStream output;
	private Socket socket;
	
	/**
	 * dados para conexão
	 */
	private final String endereco = "localhost";
	private final int porta = 5000;

	private String nomeLembrete;

	/**
	 * intervalo em segundos
	 */
	private int intervalo;

	/**
	 * total de lembretes
	 */
	private int total;

	public Cliente(String nomeLembrete, int intevalo, int total) throws UnknownHostException, IOException {
		this.nomeLembrete = nomeLembrete;
		this.intervalo = intevalo;
		this.total = total;
	}
	
	@Override
	public void run() {
		try {
			// Efetua conexão TCP
			socket = new Socket(endereco, porta);
			
			input = new DataInputStream(socket.getInputStream());
			output = new DataOutputStream(socket.getOutputStream());

			// Envia os dados ao servidor
			output.writeUTF(nomeLembrete);
			output.writeInt(intervalo);
			output.writeInt(total);

			// Aguarda os lembretes
			for (int i = 0; i < total; i++) {
				String lembrete = input.readUTF();
				System.out.println(lembrete);
			}

			socket.close();
			System.out.println(nomeLembrete + " Terminou!!");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro na thread:" + nomeLembrete);
		}

	}

	public void close() throws IOException {
		socket.close();
	}

	public String getNomeLembrete() {
		return nomeLembrete;
	}

	public void setNomeLembrete(String nomeLembrete) {
		this.nomeLembrete = nomeLembrete;
	}

	public int getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(int intervalo) {
		this.intervalo = intervalo;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
}
