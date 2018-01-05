package view;

import datalayer.AccountDAO;
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
