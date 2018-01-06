package view;

import datalayer.AccountDAO;
import datalayer.ProfileDAO;
import model.Account;
import model.Profile;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ProfilePanel extends JPanel {

    private String[] columnNamesProfile = {"ID", "AccountID", "Naam", "Geboortedatum"};
    private DefaultTableModel tmProfile = new DefaultTableModel(columnNamesProfile, 0);
    private JTable tableProfile = new JTable(tmProfile);
    private JComboBox<Account> comboBoxAccounts = new JComboBox<>();

    ProfilePanel() {

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

        JPanel panelTable = new JPanel();
        panelTable.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(tableProfile);
        tableProfile.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableProfile.setDefaultEditor(Object.class, null);
        panelTable.add(scrollPane, BorderLayout.CENTER);

        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout());
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        panelButtons.add(comboBoxAccounts);
        panelButtons.add(addButton);
        panelButtons.add(editButton);
        panelButtons.add(deleteButton);

        // Edit and Delete buttons are disabled on init
        editButton.setEnabled(false);
        deleteButton.setEnabled(false);

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

        // Add Panels
        this.add(panelButtons, BorderLayout.NORTH);
        this.add(panelTable, BorderLayout.CENTER);

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
    }

    public void updateProfileTable() {

        // Clear table
        tmProfile.setRowCount(0);

        // Refill table from DB
        for (Profile p : ProfileDAO.getInstance().getAllProfiles()) {
            Object[] o = new Object[4];
            o[0] = p.getProfileID();
            o[1] = p.getAccountID();
            o[2] = p.getProfileName();
            o[3] = p.getDateOfBirth();
            tmProfile.addRow(o);
        }
    }

    public void updateProfileCombox() {

        // Clear ComboBox
        comboBoxAccounts.removeAllItems();

        // Refill ComboBox
        for (Account a : AccountDAO.getInstance().getAllAccounts()) {
            comboBoxAccounts.addItem(a);
        }
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
    }
}
