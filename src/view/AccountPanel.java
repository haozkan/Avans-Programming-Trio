package view;

import datalayer.AccountDAO;
import model.Account;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class AccountPanel extends JPanel {

    AccountPanel() {
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
}
