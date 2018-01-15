package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import static javax.swing.SwingConstants.LEFT;

public class UserInterface implements Runnable {

    private static JFrame frame;

    @Override
    public void run() {
        frame = new JFrame("Netflix statistix");
        createComponents(frame.getContentPane());
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setMinimumSize(new Dimension(600, 300));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        SwingUtilities.updateComponentTreeUI(frame);

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
        container.setLayout(new BorderLayout());
        container.add(leftButtons(), BorderLayout.CENTER);
        container.add(bottomBar(), BorderLayout.SOUTH);
    }

    private JPanel leftButtons() {
        JPanel panel = new JPanel();
        JTabbedPane pane = new JTabbedPane();
        AccountPanel accountpanel = new AccountPanel();
        ProfilePanel profilePanel = new ProfilePanel();
        MoviePanel moviePanel = new MoviePanel();
        SeriePanel seriePanel = new SeriePanel();
        pane.setTabPlacement(LEFT);
        pane.addTab("Account", accountpanel);
        pane.addTab("Profiel", profilePanel);
        pane.addTab("Movie", moviePanel);
        pane.addTab("Serie", seriePanel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(pane);

        return panel;
    }

    private JPanel bottomBar() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(3, 10, 3, 10));
        panel.add(new JLabel("Netflix Statistix", JLabel.LEFT), BorderLayout.WEST);
        panel.add(new JLabel("Informatica klas1E - Djim Oomes (2122380), Marco van Poortvliet (2121236)", JLabel.RIGHT), BorderLayout.EAST);
        return panel;
    }

    public static JFrame getFrame() {
        return frame;
    }
}