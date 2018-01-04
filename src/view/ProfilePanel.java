package view;

import datalayer.ProfileDAO;
import model.Profile;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class ProfilePanel extends JPanel {

    private String[] columnNamesProfile = {"ID", "AccountID", "Naam", "Geboortedatum"};
    private DefaultTableModel tmProfile = new DefaultTableModel(columnNamesProfile, 0);
    private JTable tableProfile = new JTable(tmProfile);

    ProfilePanel() {

        for (Profile p : ProfileDAO.getInstance().getAllProfiles()) {
            Object[] o = new Object[4];
            o[0] = p.getProfileID();
            o[1] = p.getAccountID();
            o[2] = p.getProfileName();
            o[3] = p.getDateOfBirth();
            tmProfile.addRow(o);
        }

        this.setLayout(new BorderLayout());

        JPanel panelTable = new JPanel();
        panelTable.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(tableProfile);
        tableProfile.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableProfile.setDefaultEditor(Object.class, null);
        panelTable.add(scrollPane, BorderLayout.CENTER);

        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        panelButtons.add(addButton);
        panelButtons.add(editButton);
        panelButtons.add(deleteButton);

        this.add(panelButtons, BorderLayout.NORTH);
        this.add(panelTable, BorderLayout.CENTER);
    }

    public void updateProfileTable() {
        tmProfile.setRowCount(0);

        for (Profile p : ProfileDAO.getInstance().getAllProfiles()) {
            Object[] o = new Object[4];
            o[0] = p.getProfileID();
            o[1] = p.getAccountID();
            o[2] = p.getProfileName();
            o[3] = p.getDateOfBirth();
            tmProfile.addRow(o);
        }
    }
}
