package view;

import datalayer.MovieDAO;
import model.Movie;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

class MoviePanel extends JPanel {

    private DefaultTableModel tm;
    private JTable tableMovie;

    MoviePanel() {

        // Initialize Components
        String[] columnNames = {"ID", "Titel", "Duratie", "Genre", "Taal", "Leeftijd"};
        tm = new DefaultTableModel(columnNames, 0);
        tableMovie = new JTable(tm);

        for (Movie m : MovieDAO.getInstance().getAllMovies()) {
            Object[] o = new Object[6];
            o[0] = m.getId();
            o[1] = m.getTitle();
            o[2] = m.getDuration();
            o[3] = m.getGenre();
            o[4] = m.getLanguage();
            o[5] = m.getAgeRating();
            tm.addRow(o);
        }

        // Hide ID
        tableMovie.getColumnModel().getColumn(0).setMinWidth(0);
        tableMovie.getColumnModel().getColumn(0).setMaxWidth(0);

        this.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(tableMovie);
        tableMovie.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableMovie.setDefaultEditor(Object.class, null);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public void updateMovieTable() {
        tm.setRowCount(0);

        for (Movie m : MovieDAO.getInstance().getAllMovies()) {
            Object[] o = new Object[6];
            o[0] = m.getId();
            o[1] = m.getTitle();
            o[2] = m.getDuration();
            o[3] = m.getGenre();
            o[4] = m.getLanguage();
            o[5] = m.getAgeRating();
            tm.addRow(o);
        }
    }
}
