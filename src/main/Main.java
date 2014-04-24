package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import config.File_Manager;
import frames.MainWindow;

/**
 * Calculadora Tribal Wars, uma ferramenta completa para o jogo Tribal Wars
 * 
 * @author Arthur
 * @date 25/07/2013
 */
public class Main {

	static selecionar_mundo.GUI selecionar;
	static MainWindow mainFrame;

	public static void main(String[] args) {

		File_Manager.read();

		File_Manager.defineMundos();

		openSelection();

	}

	/**
	 * Cria e mostra o frame de seleção de mundo
	 */
	public static void openSelection() {

		selecionar = new selecionar_mundo.GUI();

		selecionar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		selecionar.pack();
		selecionar.setVisible(true);

	}

	/**
	 * Cria e mostra o frame de ferramentas, fechando o frame de seleção de
	 * mundo e salvando todas as configurações de mundo na pasta de
	 * configurações
	 */
	public static void openMainFrame() {

		selecionar.dispose();

		File_Manager.defineModelos();

//		File_Manager.save();

		mainFrame = new MainWindow();
		// Adicionando todas as ferramentas criadas
		mainFrame.addPanel(new recrutamento.GUI());
		mainFrame.addPanel(new dados_de_unidade.GUI());
		mainFrame.addPanel(new pontos.GUI());
		mainFrame.addPanel(new distância.GUI());
		mainFrame.addPanel(new oponentes_derrotados.GUI());
		mainFrame.addPanel(new simulador.GUI());

		mainFrame.selectFirst();

		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
		mainFrame.pack();
		mainFrame.setResizable(false);

		mainFrame.addWindowListener(new WindowListener() {
			
			public void windowOpened(WindowEvent arg0) {}
			public void windowIconified(WindowEvent arg0) {}
			public void windowDeiconified(WindowEvent arg0) {}
			public void windowDeactivated(WindowEvent arg0) {}
			
			public void windowClosing(WindowEvent arg0) {
//				File_Manager.save();
			}
			
			public void windowClosed(WindowEvent arg0) {}
			public void windowActivated(WindowEvent arg0) {}
		});
		
	}

}
