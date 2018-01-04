package view;

import datalayer.ProfileDAO;
import model.Profile;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class ProfilePanel extends JPanel {
    ProfilePanel() {
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

        this.setLayout(new BorderLayout());
        this.add(headerProfile, BorderLayout.NORTH);
        this.add(tableProfile, BorderLayout.CENTER);
    }
}
