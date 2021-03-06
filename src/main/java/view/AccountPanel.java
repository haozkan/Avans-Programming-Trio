package view;

import datalayer.AccountDAO;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;
import model.Account;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class AccountPanel extends JPanel {

    private static DefaultTableModel tmAccount;
    private static JTable tableAccount;


    AccountPanel() {

        // Initialize Components
        String[] columnNamesAccount = {"ID", "Naam", "StraatNaam", "Huisnummer", "Postcode", "Woonplaats"};
        tmAccount = new DefaultTableModel(columnNamesAccount, 0);
        tableAccount = new JTable(tmAccount);

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

        // Hide ID
        tableAccount.getColumnModel().getColumn(0).setMinWidth(0);
        tableAccount.getColumnModel().getColumn(0).setMaxWidth(0);

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

        IconFontSwing.register(FontAwesome.getIconFont());

        // Build Icons
        Icon addIcon = IconFontSwing.buildIcon(FontAwesome.PLUS, 15);
        Icon editIcon = IconFontSwing.buildIcon(FontAwesome.PENCIL, 15);
        Icon deleteIcon = IconFontSwing.buildIcon(FontAwesome.TRASH, 15);

        // Create Buttons
        JButton addButton = new JButton();
        JButton editButton = new JButton();
        JButton deleteButton = new JButton();
        JCheckBox oneProfile = new JCheckBox("1 Profiel");

        // Set Button Icons
        addButton.setIcon(addIcon);
        editButton.setIcon(editIcon);
        deleteButton.setIcon(deleteIcon);

        // Add Buttons to panel
        panelButtons.add(oneProfile);
        panelButtons.add(addButton);
        panelButtons.add(editButton);
        panelButtons.add(deleteButton);

        // Edit and Delete buttons are disabled on init
        editButton.setEnabled(false);
        deleteButton.setEnabled(false);

        // Checkbox Action
        oneProfile.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                updateAccountTableOneProfile();
            } else {
                updateAccountTable();
            }
        });

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
                ProfilePanel.updateProfileTable();
                ProfilePanel.updateProfileCombox();
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

    public static void updateAccountTableOneProfile() {
        // Clear Table
        tmAccount.setRowCount(0);

        // Get rows from database
        for (Account a : AccountDAO.getInstance().getAccountsWithOneProfile()) {
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

    public static void updateAccountTable() {
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
