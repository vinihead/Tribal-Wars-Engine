package selecionar_mundo;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import config.World_Reader;
import database.Cores;

@SuppressWarnings("serial")
public class GUI extends JFrame{

		List<Properties> propertyList = new ArrayList<Properties>();
	
		Informa��es_de_mundo informationTable;
		Escolha_de_mundo selectionPanel;
		
		JPanel panelMundo;
	
	/**
	 * Frame inicial, no qual ocorre a escolha do mundo
	 * Ele possui:
	 * - Logo do programa
	 * - Tabela com as informa��es do mundo selecionado
	 * - Lista dos mundos dispon�veis
	 * - Bot�o para abrir o "MainWindow"
	 */
	public GUI() {
		
		propertyList = World_Reader.getPropertiesList();
		
		getContentPane().setBackground(Cores.ALTERNAR_ESCURO);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{546, 1, 350};
		gridBagLayout.rowHeights = new int[]{92};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.gridx = 0;
		constraints.gridy = 0;
		
		constraints.gridwidth = 3;
		addImage(constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(30, 5, 30, 5);
		addWorldPanel(constraints);
		
		changeInformationPanel();
		
	}
	
	/**
	 * Cria um JLabel com a imagem do logo, e adiciona no frame
	 * 
	 * @param c GridBagConstraints para adicionar
	 */
	private void addImage(GridBagConstraints c) {
		
		JLabel lblT�tulo = new JLabel("");
		
		// No ideia how or why this works, but do not remove "resources" folder from src
		lblT�tulo.setIcon(new ImageIcon
				(Toolkit.getDefaultToolkit().getImage(
						GUI.class.getResource("/images/T�tulo.png"))));
		
		add(lblT�tulo, c);
		
	}
	
	/**
	 * Cria um JPanel com a tabela de informa��es do mundo, lista de mundos e bot�o para iniciar
	 * e o adiciona no frame
	 * 
	 * @param c GridBagConstraints para adicionar
	 */
	private void addWorldPanel(GridBagConstraints c) {
		
		panelMundo = new JPanel();
		
		panelMundo.setBackground(Cores.FUNDO_CLARO);
		
		panelMundo.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{506, 1, 310};
		gridBagLayout.rowHeights = new int[]{};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelMundo.setLayout(gridBagLayout);
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(25, 5, 25, 5);

		// Tabela de informa��es
		informationTable = new Informa��es_de_mundo();
		
		panelMundo.add(informationTable, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.insets = new Insets(25, 0, 25, 0);

		JSeparator test = new JSeparator(SwingConstants.VERTICAL);
		test.setForeground(Cores.SEPARAR_ESCURO);
		constraints.fill = GridBagConstraints.VERTICAL;
		panelMundo.add(test, constraints);
		
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.insets = new Insets(25, 5, 25, 5);
		
		// Lista dos mundos com o bot�o para inciar
		selectionPanel = new Escolha_de_mundo(this);
		
		panelMundo.add(selectionPanel, constraints);
		
		add(panelMundo, c);
		
	}
	
	/**
	 * Muda as informa��es da tabela, chamado toda vez que o mundo selecionado � alterado
	 */
	public void changeInformationPanel() {
		
		informationTable.changeProperties(propertyList.get(selectionPanel.getSelectedIndex()));
		informationTable.revalidate();
		
	}
	
}
