/*
 * Fazer, em java, uma aplica��o que liste os processos ativos, permita ao usu�_
 * rio entrar com o nome ou o PID do processo e o mate.
 * */

package view;

import javax.swing.JOptionPane;

import controller.KillController;

public class Main {

	public static void main(String[] args) {
		KillController kill = new KillController();
		
		String param = "";
		int opc=0;
        do{
            opc = Integer.parseInt(JOptionPane.showInputDialog("Qual processo voc�"
            		+ " gostaria de matar?"
                    + "\n------------------------------------------------------"
                    + "\n1 - Listar processos ativos"
                    + "\n2 - Matar processo pelo Pid"
                    + "\n3 - Matar processo pelo Nome"
                    + "\n9 - Finalizar a aplica��o"));
            switch(opc){
                case 1: System.out.print("\n\nListagem de Processos Ativos\n");
                		kill.listaProcessos();
                        break;
                case 2: param = JOptionPane.showInputDialog("Digite o Pid");
                		kill.mataPid(param);
                		System.out.println("Processo finalizado!");
                        break;
                case 3: param = JOptionPane.showInputDialog("Digite o Nome");
		        		kill.mataNome(param);
		        		System.out.println("Processo finalizado!");
		                break;
                case 9: System.out.print("\n\nAplica��o Finalizada!");
                        break;
                default: JOptionPane.showMessageDialog(null, "Op��o inv�lida!");
            }
        }
        while(opc != 9);

	}

}
