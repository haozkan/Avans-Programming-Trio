package view;

import datalayer.AccountDAO;
import model.Account;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountPanel extends JPanel {

    private String[] columnNamesAccount = {"ID", "Naam", "StraatNaam", "Huisnummer", "Postcode", "Woonplaats"};
    private DefaultTableModel tmAccount = new DefaultTableModel(columnNamesAccount, 0);
    private JTable tableAccount = new JTable(tmAccount);
    private JTableHeader headerAccount = tableAccount.getTableHeader();

    AccountPanel() {
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

        JPanel panelTable = new JPanel();
        panelTable.setLayout(new BorderLayout());
        panelTable.add(headerAccount, BorderLayout.NORTH);
        panelTable.add(tableAccount, BorderLayout.CENTER);

        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout());
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        panelButtons.add(addButton);
        panelButtons.add(editButton);
        panelButtons.add(deleteButton);

        addButton.addActionListener(new AccountPanelListener());
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Reset values
                int selectedRow = 0;
                int selectedID = -1;

                // Get selected row and ID
                selectedRow = tableAccount.getSelectedRow();
                selectedID = Integer.parseInt(tableAccount.getValueAt(selectedRow, 0).toString());

                // Show User Diaglog
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
                    AccountDAO.getInstance().deleteAccountByID(selectedID);
                }

                // Refresh Account and Profile Tables
                updateAccountTable();
                UserInterface.getProfilePanel().updateProfileTable();

            }
        });

        this.setLayout(new BorderLayout());
        this.add(panelButtons, BorderLayout.NORTH);
        this.add(panelTable, BorderLayout.CENTER);
    }

    public void updateAccountTable() {
        tmAccount.setRowCount(0);
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
