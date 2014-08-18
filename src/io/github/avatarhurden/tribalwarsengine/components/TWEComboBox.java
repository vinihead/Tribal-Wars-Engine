package io.github.avatarhurden.tribalwarsengine.components;

import database.Cores;
import io.github.avatarhurden.tribalwarsengine.main.WorldManager;
import io.github.avatarhurden.tribalwarsengine.objects.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * @author Wesley Nascimento
 */
public class TWEComboBox extends JComboBox implements ItemListener {

    public TWEComboBox() {
        this.setRenderer(new TWEComboBoxRenderer());
        this.addItemListener(this);
        setBackground(Cores.FUNDO_CLARO);
        setForeground(Color.DARK_GRAY);
    }

    public void itemStateChanged(ItemEvent event) {
        if (event.getStateChange() == ItemEvent.SELECTED && event.getItem() != null) {
            WorldManager worldManager = WorldManager.get();
            worldManager.setSelectedWorld((World) event.getItem());
        }
    }

    //Render desse combo!
    private class TWEComboBoxRenderer extends JLabel implements ListCellRenderer {

        public TWEComboBoxRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

            World world = (World) value;
            setText(world.getName());
            setForeground(Color.DARK_GRAY);
            
            if (index % 2 == 0)
                setBackground(Cores.ALTERNAR_CLARO);
            else
                setBackground(Cores.ALTERNAR_ESCURO);
            
            //Altera a fonte caso esteja selecionado
            if (isSelected) {
                setForeground(Cores.hex2Rgb("#603000"));
                setBackground(Cores.FUNDO_ESCURO);
            }

            return this;
        }
    }
}


