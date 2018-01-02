package view;

import java.awt.*;
import javax.swing.*;

import static javax.swing.SwingConstants.LEFT;

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
        container.add(leftButtons(), BorderLayout.CENTER);
        // container.add(toolbar(), BorderLayout.NORTH);
        container.add(bottomBar(), BorderLayout.SOUTH);
        //container.add(new JTextArea(),BorderLayout.CENTER);


    }

    public JPanel leftButtons(){
        //Main panel
        JPanel panel = new JPanel();

        //First tab panel
        JPanel panelOne = new JPanel();
        panelOne.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(new JButton("Add"));
        buttonPanel.add(new JButton("Remove"));
        buttonPanel.add(new JButton("Edit"));
        panelOne.add(buttonPanel,BorderLayout.NORTH);
        panelOne.add(new JTable(), BorderLayout.CENTER);

        //Second panel
        JPanel panelTwo = new JPanel();
        panelTwo.add(new JLabel("test2"));

        //Third panel
        JPanel panelThree = new JPanel();
        panelThree.add(new JLabel("test3"));

        //Fourth panel
        JPanel panelFour = new JPanel();
        panelFour.add(new JLabel("test4"));

        //Final results
        JTabbedPane pane = new JTabbedPane();
        pane.setTabPlacement(LEFT);
        pane.addTab("Account", panelOne);
        pane.addTab("Profiel", panelTwo);
        pane.addTab("Movie", panelThree);
        pane.addTab("Serie", panelFour);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(pane);

        return panel;
    }

    public JPanel toolbar(){

        //Original toolbar, discontinued
        JPanel upper = new JPanel();
        upper.setLayout(new FlowLayout());
        upper.add(new JLabel("Select serie"));
        upper.add(new JComboBox(series));

        JPanel lower = new JPanel();
        lower.add(new JLabel("Results: "));

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(upper, BorderLayout.NORTH);
        panel.add(lower,BorderLayout.SOUTH);

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