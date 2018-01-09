package view;

import datalayer.ProfileDAO;
import model.Profile;
import model.Account;
import datalayer.AccountDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ProfilePanelAdd extends JFrame implements ActionListener{

    private JDialog frame;
    private static JComboBox<Account> accountSelect;
    private Account selected;

    ProfilePanelAdd(){
        frame = new JDialog(UserInterface.getFrame(), "Add profile");
        frame.setPreferredSize(new Dimension(400, 250));
        frame.setLocationRelativeTo(UserInterface.getFrame());
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setResizable(Boolean.FALSE);
        createComponents(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container){
        container.setLayout(new BorderLayout());

        // Add padding to dialog
        JPanel panel = (JPanel) frame.getContentPane();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));


        JPanel inputFields = new JPanel();
        inputFields.setLayout(new GridLayout(0, 1));
        JTextField name = new JTextField();
        JTextField dateofBirth = new JTextField();
        inputFields.add(new JLabel("Naam"));
        inputFields.add(name);
        inputFields.add(new JLabel("Geboorte Datum"));
        inputFields.add(dateofBirth);

        container.add(inputFields, BorderLayout.CENTER);

        //Combobox starts here
        JPanel panelBox = new JPanel();
        panelBox.setLayout(new BorderLayout());

        accountSelect= new JComboBox<Account>();
        panelBox.add(accountSelect,BorderLayout.CENTER);

        JLabel giveSelection=new JLabel("Select account to add profile to");
        panelBox.add(giveSelection,BorderLayout.NORTH);

        //Fill combobox with accounts
        for (Account a : AccountDAO.getInstance().getAllAccounts()) {
            accountSelect.addItem(a);
        }
        accountSelect.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = (Account) accountSelect.getSelectedItem();
            }
        });

        container.add(panelBox,BorderLayout.NORTH);

        JPanel bottomButtons = new JPanel();
        bottomButtons.setLayout(new FlowLayout());
        JButton addButton = new JButton("Opslaan");
        bottomButtons.add(addButton);
        addButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = dateofBirth.getText();
                java.sql.Date javaSqlDate = java.sql.Date.valueOf(date);



                Profile p = new Profile(name.getText(), javaSqlDate,0,0,selected.getAccountNumber());
                ProfileDAO.getInstance().createProfile(p);
                ProfilePanel.updateProfileTable();
                ProfilePanel.updateProfileCombox();
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
                dateofBirth.setText("");
            }
        });


        container.add(bottomButtons, BorderLayout.SOUTH);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setVisible(true);
    }
}
