package view;

import datalayer.MovieDAO;
import model.Movie;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.*;
import java.awt.*;

class MoviePanel extends JPanel {

    private DefaultTableModel tm;
    private JTable tableMovie;
    private JComboBox ageRequirement;

    MoviePanel() {

        // Initialize Components
        String[] columnNames = {"ID", "Titel", "Duratie", "Genre", "Taal", "Leeftijd"};
        tm = new DefaultTableModel(columnNames, 0);
        tableMovie = new JTable(tm);

        String[] ages = {"0-12", "13-16", "17-18","18+"};
        ageRequirement = new JComboBox(ages);
        JLabel requestedAge = new JLabel("Select age requirement");

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(requestedAge);
        topPanel.add(ageRequirement);
        JButton sort = new JButton("Sort");
        topPanel.add(sort);
        sort.addActionListener(e->{
            String ageSelection = ageRequirement.getSelectedItem().toString();

            if(ageSelection.equals("0-12")){
                for(int i=0; i<tm.getRowCount(); i++){
                    if(tm.getValueAt(i,5).equals("3")){
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
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(topPanel, BorderLayout.NORTH);

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
