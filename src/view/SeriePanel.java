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

public class SeriePanel extends JPanel {

    private String[] columnNamesSerie = {"ID", "Titel", "Duratie", "Seizoen"};
    private DefaultTableModel tmSerie = new DefaultTableModel(columnNamesSerie, 0);
    private JTable tableSeries = new JTable(tmSerie);
    private JComboBox<Serie> comboBoxSerie = new JComboBox<>();

    SeriePanel() {

        for (Serie s : SerieDAO.getInstance().getAllSeries()) {
            comboBoxSerie.addItem(s);
        }

        comboBoxSerie.addActionListener(event -> {

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
        });

        this.setLayout(new BorderLayout());

        JPanel panelFourCombo = new JPanel();
        panelFourCombo.setLayout(new BorderLayout());
        panelFourCombo.add(comboBoxSerie, BorderLayout.CENTER);

        JPanel panelFourTable = new JPanel();
        panelFourTable.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(tableSeries);
        tableSeries.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableSeries.setDefaultEditor(Object.class, null);
        panelFourTable.add(scrollPane, BorderLayout.CENTER);

        this.add(panelFourCombo, BorderLayout.NORTH);
        this.add(panelFourTable, BorderLayout.CENTER);
    }

    public void updateSerieTable() {
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

}
