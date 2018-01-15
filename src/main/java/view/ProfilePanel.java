package view;

import datalayer.*;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;
import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ProfilePanel extends JPanel {

    private String[] columnNamesMovies = {"Naam", "Percentage"};
    private String[] columnNamesSeries = {"Naam", "Percentage"};
    private static DefaultTableModel tmMovies;
    private static JTable watchedMoviesTable;
    private static DefaultTableModel tmProfile;
    private static JTable tableProfile;
    private static JComboBox<Account> comboBoxAccounts;

    ProfilePanel() {

        // Initialize Components
        String[] columnNamesProfile = {"ID", "AccountID", "Naam", "Geboortedatum"};
        tmProfile = new DefaultTableModel(columnNamesProfile, 0);
        tmMovies = new DefaultTableModel(columnNamesMovies, 0);
        tableProfile = new JTable(tmProfile);
        watchedMoviesTable = new JTable(tmMovies);
        comboBoxAccounts = new JComboBox<>();

        // Get all Accounts and put them in ComboBox
        for (Account a : AccountDAO.getInstance().getAllAccounts()) {
            comboBoxAccounts.addItem(a);
        }

        // Set ComboBox selected item
        comboBoxAccounts.setSelectedIndex(0);

        // Fill table with first value
        fillTable();

        // Refill table on item changed
        comboBoxAccounts.addItemListener(e -> {
            fillTable();
        });

        this.setLayout(new BorderLayout());

        JPanel panelHeader = new JPanel();
        panelHeader.setLayout(new GridLayout(0, 2));

        JPanel panelTable = new JPanel();
        panelTable.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(tableProfile);
        tableProfile.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableProfile.setDefaultEditor(Object.class, null);
        panelTable.add(scrollPane, BorderLayout.CENTER);

        JPanel panelComboBox = new JPanel();
        panelComboBox.setLayout(new BorderLayout());
        panelComboBox.add(comboBoxAccounts, BorderLayout.CENTER);

        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));

        IconFontSwing.register(FontAwesome.getIconFont());

        // Build Icons
        Icon addIcon = IconFontSwing.buildIcon(FontAwesome.PLUS, 15);
        Icon editIcon = IconFontSwing.buildIcon(FontAwesome.PENCIL, 15);
        Icon deleteIcon = IconFontSwing.buildIcon(FontAwesome.TRASH, 15);

        // Create Buttons
        JButton addButton = new JButton();
        JButton editButton = new JButton();
        JButton deleteButton = new JButton();
        JButton editWatched = new JButton();
        JButton addWatched = new JButton();

        // Set Button Icons
        addButton.setIcon(addIcon);
        editButton.setIcon(editIcon);
        deleteButton.setIcon(deleteIcon);
        editWatched.setIcon(editIcon);
        addWatched.setIcon(addIcon);

        // Add Buttons to panel
        panelButtons.add(addButton);
        panelButtons.add(editButton);
        panelButtons.add(deleteButton);

        // Add Panels to Header Panel
        panelHeader.add(panelComboBox);
        panelHeader.add(panelButtons);

        // Edit and Delete buttons are disabled on init
        editButton.setEnabled(false);
        deleteButton.setEnabled(false);
        editWatched.setEnabled(false);

        // Statistics Panel
        JPanel panelStats = new JPanel();
        panelStats.setLayout(new GridLayout(1, 2));

        JPanel watchedMovies = new JPanel();
        JPanel watchedMoviesButtons = new JPanel();
        watchedMovies.setLayout(new BorderLayout());
        watchedMoviesButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));

        watchedMoviesButtons.add(editWatched);
        watchedMoviesButtons.add(addWatched);

        watchedMovies.add(watchedMoviesButtons, BorderLayout.NORTH);
        watchedMovies.add(watchedMoviesTable, BorderLayout.CENTER);

        tableProfile.getSelectionModel().addListSelectionListener(e -> {
            if (!tableProfile.getSelectionModel().isSelectionEmpty()) {
                updateMovieTable();
            } else {
                tmMovies.setRowCount(0);
            }
        });

        panelStats.add(watchedMovies);

        ListSelectionModel listSelectionModel = tableProfile.getSelectionModel();

        // Disable delete button if selection is empty
        listSelectionModel.addListSelectionListener(e -> {
            ListSelectionModel lsm = (ListSelectionModel) e.getSource();
            deleteButton.setEnabled(!lsm.isSelectionEmpty());
        });

        // Disable edit button if selection is empty
        listSelectionModel.addListSelectionListener(e -> {
            ListSelectionModel lsm = (ListSelectionModel) e.getSource();
            editButton.setEnabled(!lsm.isSelectionEmpty());
        });

        // Disable edit watched button if selection is empty
        ListSelectionModel listSelectionModel2 = watchedMoviesTable.getSelectionModel();
        listSelectionModel2.addListSelectionListener(e -> {
            ListSelectionModel lsm = (ListSelectionModel) e.getSource();
            editWatched.setEnabled(!lsm.isSelectionEmpty());
        });

        // Add Panels
        this.add(panelHeader, BorderLayout.NORTH);
        this.add(panelTable, BorderLayout.CENTER);
        this.add(panelStats, BorderLayout.SOUTH);

        deleteButton.addActionListener(e -> {

            // Reset values
            int selectedRow = 0;
            int selectedID = -1;

            // Get selected row and ID
            selectedRow = tableProfile.getSelectedRow();
            selectedID = Integer.parseInt(tableProfile.getValueAt(selectedRow, 0).toString());

            // Show User Dialog
            String[] options = new String[2];
            options[0] = "Verwijderen";
            options[1] = "Annuleren";
            int dialogResult = JOptionPane.showOptionDialog(
                    UserInterface.getFrame(),
                    "Weet u zeker dat u dit profiel wil verwijderen?",
                    "Profiel Verwijderen",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, null);

            // Delete Account if user selected YES
            if (dialogResult == JOptionPane.YES_OPTION && selectedID > -1) {
                ProfileDAO.getInstance().deleteProfile(ProfileDAO.getInstance().getProfileByID(selectedID));
            }

            // Refresh Account and Profile Tables
            updateProfileTable();
        });

        // Add Button Action
        addButton.addActionListener(e -> {
            new ProfilePanelAdd();
        });

        // Edit Button Action
        editButton.addActionListener(e -> {

            // Reset values
            int selectedRow = 0;
            int selectedID = -1;

            // Get selected row and ID
            selectedRow = tableProfile.getSelectedRow();
            selectedID = Integer.parseInt(tableProfile.getValueAt(selectedRow, 0).toString());

            // Open Edit Frame
            ProfilePanelEdit editPanel = new ProfilePanelEdit(selectedID);
        });


        // Disable delete button if selection is empty
        listSelectionModel.addListSelectionListener(e -> {
            ListSelectionModel lsm = (ListSelectionModel) e.getSource();
            deleteButton.setEnabled(!lsm.isSelectionEmpty());
        });

        editWatched.addActionListener(e -> {
            // Reset Values
            int selectedRow = 0;
            int selectedID = -1;
            int selectedProfileRow = 0;
            int selectedProfileID = -1;

            // Get selected row and ID
            selectedRow = watchedMoviesTable.getSelectedRow();
            selectedID = Integer.parseInt(watchedMoviesTable.getValueAt(selectedRow, 0).toString());
            selectedProfileRow = tableProfile.getSelectedRow();
            selectedProfileID = Integer.parseInt(tableProfile.getValueAt(selectedProfileRow, 0).toString());

            new ManageWatchedPanel(selectedID, selectedProfileID);
        });

        addWatched.addActionListener(e -> {
            new AddWatchedPanel();
        });

    }

    public static void updateProfileTable() {

        /// Clear Table
        tmProfile.setRowCount(0);

        // Get Selected Account Object from ComboBox
        Account selectedAccount = (Account) comboBoxAccounts.getSelectedItem();

        // Fill table with Profiles by Account
        for (Profile p : ProfileDAO.getInstance().getProfilesByAccount(selectedAccount)) {
            Object[] o = new Object[4];
            o[0] = p.getProfileID();
            o[1] = p.getAccountID();
            o[2] = p.getProfileName();
            o[3] = p.getDateOfBirth();
            tmProfile.addRow(o);
        }

        // Hide ID's
        tableProfile.getColumnModel().getColumn(0).setMinWidth(0);
        tableProfile.getColumnModel().getColumn(0).setMaxWidth(0);
        tableProfile.getColumnModel().getColumn(1).setMinWidth(0);
        tableProfile.getColumnModel().getColumn(1).setMaxWidth(0);
    }

    public static void updateMovieTable() {
        // Clear table
        tmMovies.setRowCount(0);

        // Get selected row and ID
        int selectedRow = tableProfile.getSelectedRow();
        int selectedID = Integer.parseInt(tableProfile.getValueAt(selectedRow, 0).toString());

        // Refill table from DB
        for (Movie m : MovieDAO.getInstance().getWatchedMoviesByProfile(ProfileDAO.getInstance().getProfileByID(selectedID))) {
            Object[] o = new Object[2];
            o[0] = m.getId();
            o[1] = m.getTitle();
            tmMovies.addRow(o);
        }

        // Refill table from DB
        for (Episode e : EpisodeDAO.getInstance().getWatchedEpisodesByProfile(ProfileDAO.getInstance().getProfileByID(selectedID))) {
            Object[] o = new Object[2];
            o[0] = e.getId();
            o[1] = e.getTitle();
            tmMovies.addRow(o);
        }

        // Hide ID column
        watchedMoviesTable.getColumnModel().getColumn(0).setMinWidth(0);
        watchedMoviesTable.getColumnModel().getColumn(0).setMaxWidth(0);

    }

    public static void updateProfileCombox() {

        // Clear ComboBox
//        comboBoxAccounts.removeAllItems();

        // Refill ComboBox
        for (Account a : AccountDAO.getInstance().getAllAccounts()) {
            comboBoxAccounts.addItem(a);
        }

        // Set Selection
        comboBoxAccounts.setSelectedIndex(0);

    }

    private void fillTable() {

        // Clear Table
        tmProfile.setRowCount(0);

        // Get Selected Account Object from ComboBox
        Account selectedAccount = (Account) comboBoxAccounts.getSelectedItem();

        // Fill table with Profiles by Account
        for (Profile p : ProfileDAO.getInstance().getProfilesByAccount(selectedAccount)) {
            Object[] o = new Object[4];
            o[0] = p.getProfileID();
            o[1] = p.getAccountID();
            o[2] = p.getProfileName();
            o[3] = p.getDateOfBirth();
            tmProfile.addRow(o);
        }

        // Hide ID's
        tableProfile.getColumnModel().getColumn(0).setMinWidth(0);
        tableProfile.getColumnModel().getColumn(0).setMaxWidth(0);
        tableProfile.getColumnModel().getColumn(1).setMinWidth(0);
        tableProfile.getColumnModel().getColumn(1).setMaxWidth(0);
    }


}
