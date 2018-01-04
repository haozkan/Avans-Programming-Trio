package view;

import datalayer.MovieDAO;
import model.Movie;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class MoviePanel extends JPanel {
    MoviePanel() {
        List<Movie> movies = MovieDAO.getInstance().getAllMovies();
        String[] columnNames = {"ID", "Titel", "Duratie", "Genre", "Taal", "Leeftijd"};
        DefaultTableModel tm = new DefaultTableModel(columnNames, 0);
        JTable table1 = new JTable(tm);
        JTableHeader header = table1.getTableHeader();

        for (Movie m : movies) {
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
        this.add(header, BorderLayout.NORTH);
        this.add(table1, BorderLayout.CENTER);
    }
}
