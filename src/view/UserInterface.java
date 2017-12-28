package view;

import java.awt.*;
import javax.swing.*;

public class UserInterface implements Runnable {

    private JFrame frame;
    private String series [] = {"test1", "test2", "test3"};

    @Override
    public void run() {
        frame = new JFrame("Netflix statistix");
        frame.setPreferredSize(new Dimension(500, 400));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container){
        container.setLayout(new BorderLayout());
        container.add(leftButtons(), BorderLayout.WEST);
        container.add(toolbar(), BorderLayout.NORTH);
        container.add(bottomBar(), BorderLayout.SOUTH);
        container.add(new JTextArea(),BorderLayout.CENTER);


    }

    public JPanel leftButtons(){
        //JPanel panel = new JPanel(new GridLayout(3, 1));
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        //panel.setPreferredSize(new Dimension(100,400));
        panel.add(new JButton("Test"));
        panel.add(new JButton("Test"));
        panel.add(new JButton("Test"));
        return panel;
    }

    public JPanel toolbar(){
        JPanel upper = new JPanel();
        upper.setLayout(new FlowLayout());
        upper.add(new JLabel("Select serie"));
        upper.add(new JComboBox(series));

        JPanel lower = new JPanel();
        lower.add(new JLabel("Results: "));

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(upper, BorderLayout.NORTH);
        panel.add(lower, BorderLayout.SOUTH);

        return panel;

    }

    public JPanel bottomBar(){
        JPanel panel = new JPanel();
        panel.add(new JLabel("Netflix Statistix"));
        panel.add(new JLabel("Informatica klas1E - Djim Oomes, Marco van Poortvliet"));
        return panel;
    }

    public JFrame getFrame(){
        return frame;
    }
}