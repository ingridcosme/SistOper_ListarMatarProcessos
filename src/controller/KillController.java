/*
 * A classe KillController.java deve ter 4 m�todos.
 * 1) O primeiro, chamado os, que identifica e retorna o nome do Sistema Opera_
 * cional (Faz�-lo privado).
 * 2) O segundo, chamado listaProcessos, que verifica o SO e, de acordo com SO, 
 * selecione o comando para listar os processos ativos.
 * O m�todo deve receber todas as linhas de sa�da do processo de listagem e exi_
 * bi-las em console.
 * 3) O terceiro, chamado mataPid, que recebe um PID como par�metro de entrada, 
 * verifica o SO e, de acordo com SO, selecione o comando para matar o processo 
 * e o finalize.
 * 4) O quarto, chamado mataNome, que recebe um nome de processo como par�metro 
 * de entrada, verifica o SO e, de acordo com SO, selecione o comando para matar 
 * o processo e o finalize.
 * */

package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class KillController {

	public KillController() {
		super();
	}

	private String os() {
		//Retorna o Sistema Operacional que est� em execu��o na m�quina
		String os = System.getProperty("os.name");
		return os;
	}
	
	public void listaProcessos() {
		String sistema = os();  //Recebe o sistema em execu��o
		Process p = null;
		
		try {
			if(sistema.contains("Windows")) {
				p = Runtime.getRuntime().exec("TASKLIST /FO TABLE");  //Recebe dados do processo
			} else if(sistema.contains("Linux")) {
				p = Runtime.getRuntime().exec("ps -ef");
			}
			
			InputStream fluxo = p.getInputStream();  //Transformando os dados em um fluxo de bits
			//L� o fluxo e converte para string
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();  //Recebe a primeira linha do buffer
			while(linha != null) {  //Percorre todo o buffer e imprime cada linha
				System.out.println(linha);
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
			
		} catch (IOException e) {
			//Caso ocorra algum erro, vamos mostr�-lo
			e.printStackTrace();
		}
		
	}
	
	public void mataPid(String param) {
		int pid = 0;
		String sistema = os();  //Recebe o sistema em execu��o
		
		try {
			pid = Integer.parseInt(param);
			
			if(sistema.contains("Windows")) {
				Runtime.getRuntime().exec("TASKKILL /PID " + pid);
			} else if(sistema.contains("Linux")) {
				Runtime.getRuntime().exec("kill -9 " + pid);
			}
			
		} catch(Exception e) {
			e.printStackTrace();  //Caso d� erro, mostra qual �
		}
	}
	
	public void mataNome(String nome) {
		String sistema = os();  //Recebe o sistema em execu��o
		
		try {
			if(sistema.contains("Windows")) {
				Runtime.getRuntime().exec("TASKKILL /IM " + nome);
			} else if(sistema.contains("Linux")) {
				Runtime.getRuntime().exec("pkill -f " + nome);
			}
			
		} catch(Exception e) {
			e.printStackTrace();  //Caso d� erro, mostra qual �
		}
	}
	
}
	

