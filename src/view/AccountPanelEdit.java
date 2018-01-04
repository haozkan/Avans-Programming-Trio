package view;

import datalayer.AccountDAO;
import model.Account;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountPanelEdit implements ActionListener {

    private JDialog frame;

    AccountPanelEdit(int accountID) {
        frame = new JDialog(UserInterface.getFrame(), "Edit account");
        frame.setPreferredSize(new Dimension(400, 250));
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        createComponents(frame.getContentPane(), accountID);
        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container, int accountID) {
        container.setLayout(new BorderLayout());

        // Add padding to dialog
        JPanel panel = (JPanel) frame.getContentPane();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel topText = new JPanel();
        topText.setLayout(new GridLayout(5, 1));
        topText.add(new JLabel("Naam"));
        topText.add(new JLabel("Straat"));
        topText.add(new JLabel("Huisnummer"));
        topText.add(new JLabel("Postcode"));
        topText.add(new JLabel("Woonplaats"));
        container.add(topText, BorderLayout.WEST);

        // Get Account
        Account a = AccountDAO.getInstance().getAccountByID(accountID);

        // Create textfields and fill them with Object properties
        JPanel inputFields = new JPanel();
        inputFields.setLayout(new GridLayout(5, 1));
        JTextField name = new JTextField(a.getAccountName());
        JTextField street = new JTextField(a.getStreetname());
        JTextField houseNumber = new JTextField(a.getHouseNumber());
        JTextField zipcode = new JTextField(a.getZipcode());
        JTextField residence = new JTextField(a.getResidence());

        // Add Input fields to Panel
        inputFields.add(name);
        inputFields.add(street);
        inputFields.add(houseNumber);
        inputFields.add(zipcode);
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
                    UserInterface.getAccountpanel().updateAccountTable();

                    // Close dialog
                    frame.setVisible(false);
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
                frame.setVisible(false);
            }
        });
        container.add(bottomButtons, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setVisible(true);
    }
}
