package view;

import datalayer.AccountDAO;
import model.Account;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountPanelAdd extends JFrame implements ActionListener {

    private JDialog frame;

    AccountPanelAdd() {
        frame = new JDialog(UserInterface.getFrame(), "Add account");
        frame.setPreferredSize(new Dimension(400, 250));
        frame.setLocationRelativeTo(UserInterface.getFrame());
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setResizable(Boolean.FALSE);
        createComponents(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
        container.setLayout(new BorderLayout());

        // Add padding to dialog
        JPanel panel = (JPanel) frame.getContentPane();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel inputFields = new JPanel();
        inputFields.setLayout(new GridLayout(0, 1));
        JTextField name = new JTextField();
        JTextField street = new JTextField();
        JTextField houseNumber = new JTextField();
        JTextField zipcode = new JTextField();
        JTextField residence = new JTextField();
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

        JPanel bottomButtons = new JPanel();
        bottomButtons.setLayout(new FlowLayout());
        JButton addButton = new JButton("Opslaan");
        bottomButtons.add(addButton);
        addButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Account a = new Account(name.getText(), street.getText(), houseNumber.getText(), zipcode.getText(), residence.getText());
                AccountDAO.getInstance().createAccount(a);
                UserInterface.getAccountpanel().updateAccountTable();
                UserInterface.getProfilePanel().updateProfileCombox();
                frame.dispose();
            }
        });
        JButton cancelButton = new JButton("Annuleren");
        bottomButtons.add(cancelButton);
        cancelButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                name.setText("");
                street.setText("");
                houseNumber.setText("");
                zipcode.setText("");
                residence.setText("");
            }
        });

        container.add(bottomButtons, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setVisible(true);
    }

    public JDialog getFrame() {
        return frame;
    }
}
