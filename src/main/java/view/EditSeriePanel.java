package view;

import datalayer.AccountDAO;
import datalayer.ProfileDAO;

import javax.swing.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import datalayer.SerieDAO;
import model.Account;
import model.Profile;
import model.Serie;

public class EditSeriePanel extends JFrame implements ActionListener {
    private JDialog frame;

    EditSeriePanel(int id) {
        frame = new JDialog(UserInterface.getFrame(), "Edit serie");
        frame.setPreferredSize(new Dimension(400, 250));
        frame.setLocationRelativeTo(UserInterface.getFrame());
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setResizable(Boolean.FALSE);
        createComponents(frame.getContentPane(), id);
        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container, int ID) {
        container.setLayout(new BorderLayout());

        // Add padding to dialog
        JPanel panel = (JPanel) frame.getContentPane();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));

        // Get serie
        Serie s = SerieDAO.getInstance().getSerieByID(ID);

        // Create textfields and fill them with Object properties
        JPanel inputFields = new JPanel();
        inputFields.setLayout(new GridLayout(0, 1));
        JTextField name = new JTextField(s.getName());

        // Add Input fields to Panel
        inputFields.add(new JLabel("Naam"));
        inputFields.add(name);
        container.add(inputFields, BorderLayout.CENTER);

        // Update button updates Profile and exits dialog
        JPanel bottomButtons = new JPanel();
        bottomButtons.setLayout(new FlowLayout());
        JButton editButton = new JButton("Edit");
        bottomButtons.add(editButton);
        editButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Update serie
                    Serie s = new Serie(name.getText());
                    SerieDAO.getInstance().updateSerie(s);

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

    }
}
