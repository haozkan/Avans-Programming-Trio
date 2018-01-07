package view;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import datalayer.ProfileDAO;
import model.Profile;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProfilePanelEdit extends JFrame implements ActionListener {


    private JDialog frame;

    ProfilePanelEdit(int profileID) {
        frame = new JDialog(UserInterface.getFrame(), "Edit profile");
        frame.setPreferredSize(new Dimension(400, 250));
        frame.setLocationRelativeTo(UserInterface.getFrame());
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setResizable(Boolean.FALSE);
        createComponents(frame.getContentPane(), profileID);
        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container, int profileID) {
        container.setLayout(new BorderLayout());

        // Add padding to dialog
        JPanel panel = (JPanel) frame.getContentPane();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));

        // Get Profile
        Profile p = ProfileDAO.getInstance().getProfileByID(profileID);

        // Create textfields and fill them with Object properties
        JPanel inputFields = new JPanel();
        inputFields.setLayout(new GridLayout(0, 1));
        JTextField name = new JTextField(p.getProfileName());
        JTextField dateofBirth = new JTextField(p.getDateOfBirth().toString());

        // Add Input fields to Panel
        inputFields.add(new JLabel("Naam"));
        inputFields.add(name);
        inputFields.add(new JLabel("Geboortedatum"));
        inputFields.add(dateofBirth);
        container.add(inputFields, BorderLayout.CENTER);

        // Update button updates Profile and exits dialog
        JPanel bottomButtons = new JPanel();
        bottomButtons.setLayout(new FlowLayout());
        JButton addButton = new JButton("Opslaan");
        bottomButtons.add(addButton);
        addButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String date = dateofBirth.getText();
                    java.sql.Date javaSqlDate = java.sql.Date.valueOf(date);
                    // Update profile
                    Profile p = new Profile(name.getText(),javaSqlDate, profileID);
                    ProfileDAO.getInstance().updateProfile(p);

                    // Update Profile Table
                    UserInterface.getProfilePanel().updateProfileTable();


                    // Update Profile Combobox
                    //UserInterface.getProfilePanel().updateProfileCombox();

                    // Close dialog
                    frame.dispose();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });

        // Cancel button clears fields and exits dialog
        JButton cancelButton = new JButton("Annuleren");
        bottomButtons.add(cancelButton);
        cancelButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        container.add(bottomButtons, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setVisible(true);
    }
}
