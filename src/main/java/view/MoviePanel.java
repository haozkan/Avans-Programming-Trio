package view;

import datalayer.EpisodeDAO;
import datalayer.MovieDAO;
import model.Episode;
import model.Movie;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.*;
import java.awt.*;

class MoviePanel extends JPanel {

    private DefaultTableModel tm;
    private JTable tableMovie;
    private JComboBox ageRequirement;
    private JLabel watched;

    MoviePanel() {

        // Initialize Components
        String[] columnNames = {"ID", "Titel", "Duratie", "Genre", "Taal", "Leeftijd"};
        tm = new DefaultTableModel(columnNames, 0);
        tableMovie = new JTable(tm);
        watched = new JLabel();
        String[] ages = {"0-12", "13-16", "17-18", "18+"};
        String[] twelve = {};
        ageRequirement = new JComboBox(ages);
        JLabel requestedAge = new JLabel("Select age requirement");

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(requestedAge);
        topPanel.add(ageRequirement);
        JButton sort = new JButton("Sort");
        topPanel.add(sort);
        sort.addActionListener(e -> {
            String ageSelection = ageRequirement.getSelectedItem().toString();

            if (ageSelection.equals("0-12")) {
                for (int i = 0; i < tm.getRowCount(); i++) {
                    String a = tm.getValueAt(i, 5).toString();
                    int number = Integer.parseInt(a);
                    if (number > 12) {
                        tm.removeRow(i);
                    }
                }
            } else if (ageSelection.equals("13-16")) {
                for (int i = 0; i < tm.getRowCount(); i++) {
                    String a = tm.getValueAt(i, 5).toString();
                    int number = Integer.parseInt(a);
                    if (number <13 || number > 16) {
                        tm.removeRow(i);
                    }
                }
            } else if (ageSelection.equals("17-18")) {
                for (int i = 0; i < tm.getRowCount(); i++) {
                    String a = tm.getValueAt(i, 5).toString();
                    int number = Integer.parseInt(a);
                    if (number <17 || number > 18) {
                        tm.removeRow(i);
                    }
                }
            } else if (ageSelection.equals("18+")) {
                for (int i = 0; i < tm.getRowCount(); i++) {
                    String a = tm.getValueAt(i, 5).toString();
                    int number = Integer.parseInt(a);
                    if (number <18) {
                        tm.removeRow(i);
                    }
                }
            }
        });


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

        //Sorting
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tm);
        tableMovie.setRowSorter(sorter);


        // Hide ID
        tableMovie.getColumnModel().getColumn(0).setMinWidth(0);
        tableMovie.getColumnModel().getColumn(0).setMaxWidth(0);

        this.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(tableMovie);
        tableMovie.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableMovie.setDefaultEditor(Object.class, null);

        JPanel statPanel = new JPanel();
        statPanel.setLayout(new GridLayout());
        statPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        statPanel.add(watched);

        // Add stats on row change
        tableMovie.getSelectionModel().addListSelectionListener(e -> {
            if (!tableMovie.getSelectionModel().isSelectionEmpty()) {
                int m = Integer.parseInt(tm.getValueAt(tableMovie.getSelectedRow(), 0).toString());
                int watchedCount = MovieDAO.getInstance().getCountWatched(MovieDAO.getInstance().getMovieByID(m));
                watched.setText("Bekeken door " + watchedCount + " personen");
            } else {
                watched.setText("");
            }

        });

        this.add(scrollPane, BorderLayout.CENTER);
        this.add(topPanel, BorderLayout.NORTH);
        this.add(statPanel, BorderLayout.SOUTH);

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
