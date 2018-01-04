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
    private JTableHeader headerProfile = tableProfile.getTableHeader();

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
        this.add(headerProfile, BorderLayout.NORTH);
        this.add(tableProfile, BorderLayout.CENTER);
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
