package view;

import datalayer.SerieDAO;
import model.Episode;
import model.Serie;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SeriePanel extends JPanel{
    SeriePanel() {
        List<Serie> series = SerieDAO.getInstance().getAllSeries();
        String[] columnNamesSerie = {"ID", "Titel", "Duratie", "Seizoen"};
        DefaultTableModel tmSerie = new DefaultTableModel(columnNamesSerie, 0);
        JTable tableSeries = new JTable(tmSerie);
        JTableHeader headerSerie = tableSeries.getTableHeader();
        JComboBox comboBoxSerie = new JComboBox();

        for (Serie s : series) {
            comboBoxSerie.addItem(s);
        }

        comboBoxSerie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                // Clear Table
                tmSerie.setRowCount(0);

                // Get Selected Serie Object from ComboBox
                Serie selectedSerie = (Serie) comboBoxSerie.getSelectedItem();

                // Get parameters and add as row
                for (Episode e : SerieDAO.getInstance().getAllEpisodesBySerie(selectedSerie)) {
                    Object[] o = new Object[4];
                    o[0] = e.getId();
                    o[1] = e.getTitle();
                    o[2] = e.getDuration();
                    o[3] = e.getSeason();
                    tmSerie.addRow(o);
                }
            }
        });

        this.setLayout(new BorderLayout());

        JPanel panelFourCombo = new JPanel();
        panelFourCombo.setLayout(new BorderLayout());
        panelFourCombo.add(comboBoxSerie, BorderLayout.CENTER);

        JPanel panelFourTable = new JPanel();
        panelFourTable.setLayout(new BorderLayout());
        panelFourTable.add(headerSerie, BorderLayout.NORTH);
        panelFourTable.add(tableSeries, BorderLayout.CENTER);

        this.add(panelFourCombo, BorderLayout.NORTH);
        this.add(panelFourTable, BorderLayout.CENTER);
    }

}
