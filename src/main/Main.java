package main;

import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.UIManager;

import config.Config_Gerais;
import config.File_Manager;
import frames.MainWindow;
import frames.TrayIconClass;

/**
 * Tribal Wars Engine, uma ferramenta completa para o jogo Tribal Wars
 * 
 * @author Arthur
 * @date 25/07/2013
 *
 * Contribuições de Wesley Nascimento vulgo Sorriso
 */
public class Main {

	private static selecionar_mundo.GUI selecionar;
	private static MainWindow mainFrame;

	public static void main(String[] args) {
		
		Font oldLabelFont = UIManager.getFont("Label.font");
	    UIManager.put("Label.font", oldLabelFont.deriveFont(Font.PLAIN));
		
		Config_Gerais.read();
		
		File_Manager.read();

		File_Manager.defineMundos();
		
		new TrayIconClass();

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
		selecionar.setResizable(false);
		
		selecionar.setLocationRelativeTo(null);

		selecionar.addWindowListener(new WindowListener() {
			
			public void windowOpened(WindowEvent arg0) {}
			public void windowIconified(WindowEvent arg0) {}
			public void windowDeiconified(WindowEvent arg0) {}
			public void windowDeactivated(WindowEvent arg0) {}
			
			public void windowClosing(WindowEvent arg0) {
				File_Manager.save();
				Config_Gerais.save();
			}
			
			public void windowClosed(WindowEvent arg0) {}
			public void windowActivated(WindowEvent arg0) {}
		});
		
	}

	/**
	 * Cria e mostra o frame de ferramentas, fechando o frame de seleção de
	 * mundo e salvando todas as configurações de mundo na pasta de
	 * configurações
	 */
	public static void openMainFrame() {
		
		File_Manager.defineModelos();

		mainFrame = MainWindow.getInstance();
		
		// Adicionando todas as ferramentas criadas
		mainFrame.addPanel(new recrutamento.GUI());
		mainFrame.addPanel(new dados_de_unidade.GUI());
		mainFrame.addPanel(new pontos.GUI());
		mainFrame.addPanel(new distância.GUI());
		mainFrame.addPanel(new oponentes_derrotados.GUI());
		mainFrame.addPanel(new simulador.GUI());
		mainFrame.addPanel(new assistente_saque.GUI());
		mainFrame.addPanel(new alertas.GUI());

		mainFrame.selectFirst();

		//mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		mainFrame.setResizable(false);
		
		// Puts the mainFrame where the world-selection frame was
		mainFrame.setLocationRelativeTo(selecionar);
		selecionar.dispose();
		
		mainFrame.setVisible(true);
		
//		mainFrame.addWindowListener(new WindowListener() {
//			
//			public void windowOpened(WindowEvent arg0) {}
//			public void windowIconified(WindowEvent arg0) {}
//			public void windowDeiconified(WindowEvent arg0) {}
//			public void windowDeactivated(WindowEvent arg0) {}
//			
//			public void windowClosing(WindowEvent arg0) {
//				File_Manager.save();
//				Config_Gerais.save();
//			}
//			
//			public void windowClosed(WindowEvent arg0) {}
//			public void windowActivated(WindowEvent arg0) {}
//		});
		
	}
	
	public static MainWindow getMainWindow() {
		return mainFrame;
	}

}
