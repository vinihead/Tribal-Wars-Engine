package io.github.avatarhurden.tribalwarsengine.panels;

import io.github.avatarhurden.tribalwarsengine.enums.Cores;
import io.github.avatarhurden.tribalwarsengine.managers.WorldManager;
import io.github.avatarhurden.tribalwarsengine.objects.World;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.json.JSONObject;

public class WorldInfoPanel extends JPanel {
    private GridBagConstraints gbc;

    /**
     * Muda as informa��es da tabela de informa��es
     * As propriedades mostradas s�o escolinhas dinamicamente de acordo com o mundo!
     */
    public void changeProperties() {
    	World world = WorldManager.getSelectedWorld();
        JSONObject json = world.getBasicConfig();

        removeAll();
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{290};
        gridBagLayout.rowHeights = new int[]{};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0,
                Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);
        setOpaque(false);

        setBorder(new LineBorder(Cores.SEPARAR_ESCURO, 1, false));

        gbc = new GridBagConstraints();
        gbc.gridy = 0;

        addProp("Nome", world.getPrettyName());
        addProp("Velocidade", json.get("speed"));
        addProp("Velocidade das unidades", json.get("unit_speed"));
        addProp("Moral", json.get("moral"));
        addProp("Sistema de pesquisa", json.get("tech"));
        addProp("Igreja", json.get("church"));
        addProp("Bonus Noturno", json.get("night"));
        addProp("Archeiros", json.get("archer"));
        addProp("Paladino", json.get("knight"));
        addProp("Itens Aprimorados", json.get("better_items"));
        addProp("Mil�cia", json.get("militia"));
        addProp("Cunhagem de moedas", json.get("coins"));

        this.revalidate();
    }

    /**
     * Converte os valores para Strings que possam ser entendidas mais facilmente,
     * cria o subpanel e adiciona ao superPanel
     */
    public void addProp(String propName, Object value) {
        String propValue;

        //Se � um boleano
        if (value instanceof Boolean) {

            if ((Boolean) value) {
                propValue = "Ativado";
            } else {
                propValue = "Desativado";
            }
        }
        //Se � qualquer outra coisa!
        else {
            propValue = String.valueOf(value);
        }

        //Adiciona uma lina ao grid
        gbc.gridy++;

        add(createPropPanel(propName, propValue), gbc);
    }

    public JPanel createPropPanel(String propName, String propValue) {
        JPanel panel = new JPanel();
        
        panel.setBackground(Cores.getAlternar(gbc.gridy));

        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{150, 20, 140};
        gbl_panel.rowHeights = new int[]{20};
        gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
        panel.setLayout(gbl_panel);

        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(5, 5, 5, 5);

        JLabel lblName = new JLabel(propName);

        gbc_panel.gridx = 0;
        gbc_panel.gridy = 0;
        panel.add(lblName, gbc_panel);

        JLabel lblValue = new JLabel(propValue);

        gbc_panel.gridx = 2;
        gbc_panel.gridy = 0;
        panel.add(lblValue, gbc_panel);

        return panel;
    }
}