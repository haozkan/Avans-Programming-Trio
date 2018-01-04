package view;

import datalayer.AccountDAO;
import model.Account;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

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
        panelButtons.add(addButton);
        addButton.addActionListener(new AccountPanelListener());
        panelButtons.add(new JButton("Edit"));
        panelButtons.add(new JButton("Delete"));

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
