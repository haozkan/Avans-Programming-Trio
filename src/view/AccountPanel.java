package view;

import datalayer.AccountDAO;
import model.Account;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AccountPanel extends JPanel {

    private String[] columnNamesAccount = {"ID", "Naam", "StraatNaam", "Huisnummer", "Postcode", "Woonplaats"};
    private DefaultTableModel tmAccount = new DefaultTableModel(columnNamesAccount, 0);
    private JTable tableAccount = new JTable(tmAccount);

    AccountPanel() {

        // Get rows from database
        for (Account a : AccountDAO.getInstance().getAllAccounts()) {
            Object[] o = new Object[6];
            o[0] = a.getAccountNumber();
            o[1] = a.getAccountName();
            o[2] = a.getStreetname();
            o[3] = a.getHouseNumber();
            o[4] = a.getZipcode();
            o[5] = a.getResidence();
            tmAccount.addRow(o);
        }

        // Table Panel
        JPanel panelTable = new JPanel();
        panelTable.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(tableAccount);
        tableAccount.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableAccount.setDefaultEditor(Object.class, null);
        panelTable.add(scrollPane, BorderLayout.CENTER);

        // Button Panel
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        panelButtons.add(addButton);
        panelButtons.add(editButton);
        panelButtons.add(deleteButton);

        // Edit and Delete buttons are disabled on init
        editButton.setEnabled(false);
        deleteButton.setEnabled(false);

        // Add Button Action
        addButton.addActionListener(e -> {
            new AccountPanelAdd();
        });

        // Delete Button Action
        deleteButton.addActionListener(e -> {

            // Reset values
            int selectedRow = 0;
            int selectedID = -1;

            // Get selected row and ID
            selectedRow = tableAccount.getSelectedRow();
            selectedID = Integer.parseInt(tableAccount.getValueAt(selectedRow, 0).toString());

            // Show User Dialog
            String[] options = new String[2];
            options[0] = "Verwijderen";
            options[1] = "Annuleren";
            int dialogResult = JOptionPane.showOptionDialog(
                    UserInterface.getFrame(),
                    "Bij het verwijderen van een account worden alle onderliggende profielen verwijderd, weet u zeker dat u wilt doorgaan?",
                    "Account Verwijderen",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, null);

            // Delete Account if user selected YES
            if (dialogResult == JOptionPane.YES_OPTION && selectedID > -1) {

                // Delete Account
                AccountDAO.getInstance().deleteAccountByID(selectedID);

                // Refresh Account and Profile tables and ComboBox
                updateAccountTable();
                UserInterface.getProfilePanel().updateProfileTable();
                UserInterface.getProfilePanel().updateProfileCombox();
            }

        });

        // Edit Button Action
        editButton.addActionListener(e -> {

            // Reset values
            int selectedRow = 0;
            int selectedID = -1;

            // Get selected row and ID
            selectedRow = tableAccount.getSelectedRow();
            selectedID = Integer.parseInt(tableAccount.getValueAt(selectedRow, 0).toString());

            // Open Edit Frame
            AccountPanelEdit editPanel = new AccountPanelEdit(selectedID);
        });

        ListSelectionModel listSelectionModel = tableAccount.getSelectionModel();

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

        // Add Panels to AccountPanel
        this.setLayout(new BorderLayout());
        this.add(panelButtons, BorderLayout.NORTH);
        this.add(panelTable, BorderLayout.CENTER);
    }

    public void updateAccountTable() {
        // Clear Table
        tmAccount.setRowCount(0);

        // Get rows from database
        for (Account a : AccountDAO.getInstance().getAllAccounts()) {
            Object[] o = new Object[6];
            o[0] = a.getAccountNumber();
            o[1] = a.getAccountName();
            o[2] = a.getStreetname();
            o[3] = a.getHouseNumber();
            o[4] = a.getZipcode();
            o[5] = a.getResidence();
            tmAccount.addRow(o);
        }
    }
}
