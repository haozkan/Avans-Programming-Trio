package view;

import datalayer.AccountDAO;
import datalayer.MovieDAO;
import datalayer.ProfileDAO;
import model.Account;
import model.Movie;
import model.Profile;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import static javax.swing.SwingConstants.LEFT;

public class UserInterface implements Runnable {

    private JFrame frame;
    private String series[] = {"test1", "test2", "test3"};

    @Override
    public void run() {
        frame = new JFrame("Netflix statistix");
        frame.setPreferredSize(new Dimension(500, 400));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
        container.setLayout(new BorderLayout());
        container.add(leftButtons(), BorderLayout.CENTER);
        // container.add(toolbar(), BorderLayout.NORTH);
        container.add(bottomBar(), BorderLayout.SOUTH);
        //container.add(new JTextArea(),BorderLayout.CENTER);


    }

    public JPanel leftButtons() {

        // Main Panel
        // -------------------------------------------------- //
        JPanel panel = new JPanel();

        // Panel 1: Account
        // --------------------------------------------------.
        List<Account> accounts = AccountDAO.getInstance().getAllAccounts();
        String[] columnNamesAccount = {"ID", "Naam", "StraatNaam", "Huisnummer", "Postcode", "Woonplaats"};
        DefaultTableModel tmAccount = new DefaultTableModel(columnNamesAccount, 0);
        JTable tableAccount = new JTable(tmAccount);
        JTableHeader headerAccount = tableAccount.getTableHeader();

        for (Account a : accounts) {
            Object[] o = new Object[6];
            o[0] = a.getAccountNumber();
            o[1] = a.getAccountName();
            o[2] = a.getStreetname();
            o[3] = a.getHouseNumber();
            o[4] = a.getZipcode();
            o[5] = a.getResidence();
            tmAccount.addRow(o);
        }

        JPanel panelOne = new JPanel();
        panelOne.setLayout(new BorderLayout());
        panelOne.add(headerAccount, BorderLayout.NORTH);
        panelOne.add(tableAccount, BorderLayout.CENTER);

        // Second Panel: Profiel
        // --------------------------------------------------.
        List<Profile> profiles = ProfileDAO.getInstance().getAllProfiles();
        String[] columnNamesProfile = {"ID", "AccountID", "Naam", "Geboortedatum"};
        DefaultTableModel tmProfile = new DefaultTableModel(columnNamesProfile, 0);
        JTable tableProfile = new JTable(tmProfile);
        JTableHeader headerProfile = tableProfile.getTableHeader();

        for (Profile p : profiles) {
            Object[] o = new Object[4];
            o[0] = p.getProfileID();
            o[1] = p.getAccountID();
            o[2] = p.getProfileName();
            o[3] = p.getDateOfBirth();
            tmProfile.addRow(o);
        }

        JPanel panelTwo = new JPanel();
        panelTwo.setLayout(new BorderLayout());
        panelTwo.add(headerProfile, BorderLayout.NORTH);
        panelTwo.add(tableProfile, BorderLayout.CENTER);

        // Third Panel: Films
        // --------------------------------------------------.
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

        JPanel panelThree = new JPanel();
        panelThree.setLayout(new BorderLayout());
        panelThree.add(header, BorderLayout.NORTH);
        panelThree.add(table1, BorderLayout.CENTER);

        // Fourth Panel: Series
        // --------------------------------------------------.
        JPanel panelFour = new JPanel();
        panelFour.add(new JLabel("test4"));

        // Construct TabMenu
        // --------------------------------------------------.
        JTabbedPane pane = new JTabbedPane();
        pane.setTabPlacement(LEFT);
        pane.addTab("Account", panelOne);
        pane.addTab("Profiel", panelTwo);
        pane.addTab("Movie", panelThree);
        pane.addTab("Serie", panelFour);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(pane);

        return panel;
    }

    public JPanel toolbar() {

        //Original toolbar, discontinued
        JPanel upper = new JPanel();
        upper.setLayout(new FlowLayout());
        upper.add(new JLabel("Select serie"));
        upper.add(new JComboBox(series));

        JPanel lower = new JPanel();
        lower.add(new JLabel("Results: "));

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(upper, BorderLayout.NORTH);
        panel.add(lower, BorderLayout.SOUTH);

        return panel;

    }

    public JPanel bottomBar() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Netflix Statistix"));
        panel.add(new JLabel("Informatica klas1E - Djim Oomes, Marco van Poortvliet"));
        return panel;
    }

    public JFrame getFrame() {
        return frame;
    }
}