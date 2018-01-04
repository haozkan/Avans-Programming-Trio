package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountPanelListener extends JFrame implements ActionListener {

    private JFrame frame;

    public AccountPanelListener(){
        frame = new JFrame("Add account");
        frame.setPreferredSize(new Dimension(400,300));

        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(false);
    }

    private void createComponents(Container container){
        container.setLayout(new BorderLayout());
        container.add(new JLabel("Add accounts below"), BorderLayout.NORTH);
        container.add(new JTextArea(), BorderLayout.CENTER);

        JPanel bottomButtons = new JPanel();
        bottomButtons.setLayout(new FlowLayout());
        JButton addButton = new JButton("Add");
        bottomButtons.add(addButton);
        JButton cancelButton = new JButton("Cancel");
        bottomButtons.add(cancelButton);

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
