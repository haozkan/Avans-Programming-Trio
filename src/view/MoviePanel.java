package view;

import datalayer.MovieDAO;
import model.Movie;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class MoviePanel extends JPanel {

    private String[] columnNames = {"ID", "Titel", "Duratie", "Genre", "Taal", "Leeftijd"};
    private DefaultTableModel tm = new DefaultTableModel(columnNames, 0);
    private JTable tableMovie = new JTable(tm);

    MoviePanel() {

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
