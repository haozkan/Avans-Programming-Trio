package view;

import datalayer.SerieDAO;
import model.Episode;
import model.Serie;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SeriePanel extends JPanel {

    private String[] columnNamesSerie = {"ID", "Titel", "Duratie", "Seizoen"};
    private DefaultTableModel tmSerie = new DefaultTableModel(columnNamesSerie, 0);
    private JTable tableSeries = new JTable(tmSerie);
    private JComboBox<Serie> comboBoxSerie = new JComboBox<>();
    private JLabel averageWatchTime = new JLabel();
    private JLabel averageWatchedEpisodeCount = new JLabel();

    SeriePanel() {

        // Fill ComboBox with Series
        for (Serie s : SerieDAO.getInstance().getAllSeries()) {
            comboBoxSerie.addItem(s);
        }

        // Fill Table with Episodes
        fillTable();

        // Refill table on item changed
        comboBoxSerie.addActionListener(event -> {
            fillTable();
            Serie selectedSerie = (Serie) comboBoxSerie.getSelectedItem();
            averageWatchTime.setText("Gemiddelde kijktijd:" + SerieDAO.getInstance().getAverageWatchTime(selectedSerie) + "%");
            averageWatchedEpisodeCount.setText("Gemiddeld aantal bekeken afleveringen: " + 0 + "%");
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

        JPanel statPanel = new JPanel();
        statPanel.setLayout(new GridLayout());
        statPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        statPanel.add(averageWatchTime);
        statPanel.add(averageWatchedEpisodeCount);

        this.add(panelFourCombo, BorderLayout.NORTH);
        this.add(panelFourTable, BorderLayout.CENTER);
        this.add(statPanel, BorderLayout.SOUTH);
    }

    private void fillTable() {
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

        // Set Average Watch time Text Label
        averageWatchTime.setText("Average Watchtime:" + SerieDAO.getInstance().getAverageWatchTime(selectedSerie) + "%");

        // Set Average Watched Epsiode Text Label
        averageWatchedEpisodeCount.setText("Gemiddeld aantal bekeken afleveringen: " + 0 + "%");

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
