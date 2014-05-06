package frames;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.KeyEventDispatcher;
import java.awt.KeyEventPostProcessor;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import selecionar_mundo.GUI;
import config.Lang;
import custom_components.Ferramenta;
import database.Cores;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	JPanel tabs;
	JPanel body;

	public List<Ferramenta> ferramentas = new ArrayList<Ferramenta>();
	public Ferramenta ferramentaSelecionada;
	
	/**
	 * Frame que cont�m todas as ferramentas
	 */
	public MainWindow() {
		
		// Setting the visuals for the frame
		setTitle(Lang.Titulo.toString());
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				GUI.class.getResource("/images/Icon.png")));

		getContentPane().setBackground(Cores.ALTERNAR_ESCURO);
		setBackground(Cores.FUNDO_CLARO);

		setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 0, 5);

		c.gridy = 0;
		c.gridx = 0;
		tabs = new JPanel();
		tabs.setLayout(new GridBagLayout());
		c.anchor = GridBagConstraints.WEST;
		add(tabs, c);

		c.insets = new Insets(0, 5, 5, 5);
		c.gridy++;
		c.fill = GridBagConstraints.HORIZONTAL;
		body = new JPanel();
		body.setLayout(new FlowLayout());
		body.setBackground(Cores.FUNDO_CLARO);
		body.setBorder(new LineBorder(Cores.SEPARAR_ESCURO));
		add(body, c);
		
		// Adds listener for ctrl+tab funcionality
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		
		manager.addKeyEventDispatcher(new KeyEventDispatcher() {
			public boolean dispatchKeyEvent(KeyEvent e) {
				
				// Only do this once per key press
				if (e.getID() == KeyEvent.KEY_PRESSED)
					if (e.getKeyCode() == KeyEvent.VK_TAB && e.isControlDown()) {
						
						// If it is not the last tool, go to the next one. Otherwise, go to first
						if (ferramentas.indexOf(ferramentaSelecionada) < ferramentas.size()-1)
							ferramentas.get(ferramentas.indexOf(ferramentaSelecionada)+1).changeSelection();
						else
							ferramentas.get(0).changeSelection();
		
					}
				
				return true;	
			}
		});
		
	}

	/**
	 * Adiciona uma ferramenta nova ao frame, colocando a "tab" no local
	 * adequado
	 * 
	 * @param tool
	 *            Ferramenta a ser adicionada
	 */
	public void addPanel(Ferramenta tool) {

		tool.setFrame(this);
		
		ferramentas.add(tool);

		tabs.add(tool.getTab());

		body.add(tool);

	}

	/**
	 * Seleciona a primeira ferramenta do frame
	 */
	public void selectFirst() {

		for (Ferramenta i : ferramentas)
			i.setSelected(false);

		ferramentas.get(0).setSelected(true);
		ferramentaSelecionada = ferramentas.get(0);

	}

}