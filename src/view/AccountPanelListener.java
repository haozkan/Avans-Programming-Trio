package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountPanelListener extends JFrame implements ActionListener {

    private JFrame frame;

    public AccountPanelListener(){
        frame = new JFrame("Add account");
        frame.setPreferredSize(new Dimension(400,150));

        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(false);
    }

    private void createComponents(Container container){
        container.setLayout(new BorderLayout());

        JPanel topText = new JPanel();
        topText.setLayout(new GridLayout(1,4));
        topText.add(new JLabel("Street"));
        topText.add(new JLabel("Huisnummer"));
        topText.add(new JLabel("Naam"));
        topText.add(new JLabel("En nog iets?"));
        container.add(topText, BorderLayout.NORTH);

        JPanel inputFields = new JPanel();
        inputFields.setLayout(new GridLayout(1,4));
        inputFields.add(new JTextField());
        inputFields.add(new JTextField());
        inputFields.add(new JTextField());
        inputFields.add(new JTextField());
        container.add(inputFields, BorderLayout.CENTER);

        JPanel bottomButtons = new JPanel();
        bottomButtons.setLayout(new FlowLayout());
        JButton addButton = new JButton("Add");
        bottomButtons.add(addButton);
        addButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hier dus de waardes van de inputfield innemen
            }
        });
        JButton cancelButton = new JButton("Cancel");
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

    public JFrame getFrame() {
        return frame;
    }
}
