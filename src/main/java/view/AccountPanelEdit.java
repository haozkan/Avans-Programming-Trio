package view;

import datalayer.AccountDAO;
import model.Account;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class AccountPanelEdit extends JFrame implements ActionListener {

    private JDialog frame;

    AccountPanelEdit(int accountID) {
        frame = new JDialog(UserInterface.getFrame(), "Edit account");
        frame.setPreferredSize(new Dimension(400, 250));
        frame.setLocationRelativeTo(UserInterface.getFrame());
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setResizable(Boolean.FALSE);
        createComponents(frame.getContentPane(), accountID);
        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container, int accountID) {
        container.setLayout(new BorderLayout());

        // Add padding to dialog
        JPanel panel = (JPanel) frame.getContentPane();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));

        // Get Account
        Account a = AccountDAO.getInstance().getAccountByID(accountID);

        // Create textfields and fill them with Object properties
        JPanel inputFields = new JPanel();
        inputFields.setLayout(new GridLayout(0, 1));
        JTextField name = new JTextField(a.getAccountName());
        JTextField street = new JTextField(a.getStreetname());
        JTextField houseNumber = new JTextField(a.getHouseNumber());
        JTextField zipcode = new JTextField(a.getZipcode());
        JTextField residence = new JTextField(a.getResidence());

        // Add Input fields to Panel
        inputFields.add(new JLabel("Naam"));
        inputFields.add(name);
        inputFields.add(new JLabel("Straat"));
        inputFields.add(street);
        inputFields.add(new JLabel("Huisnummer"));
        inputFields.add(houseNumber);
        inputFields.add(new JLabel("Postcode"));
        inputFields.add(zipcode);
        inputFields.add(new JLabel("Woonplaats"));
        inputFields.add(residence);
        container.add(inputFields, BorderLayout.CENTER);

        // Update button updates Account and exits dialog
        JPanel bottomButtons = new JPanel();
        bottomButtons.setLayout(new FlowLayout());
        JButton addButton = new JButton("Opslaan");
        bottomButtons.add(addButton);
        addButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Update Account
                    Account a = new Account(accountID, name.getText(), street.getText(), houseNumber.getText(), zipcode.getText(), residence.getText());
                    AccountDAO.getInstance().updateAccount(a);

                    // Update Account Table
                    AccountPanel.updateAccountTable();

                    // Update Account Combobox
                    ProfilePanel.updateProfileCombox();

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
