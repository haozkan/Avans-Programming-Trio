package view;

import datalayer.AccountDAO;
import datalayer.MovieDAO;
import datalayer.ProfileDAO;
import datalayer.SerieDAO;
import model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import static javax.swing.SwingConstants.LEFT;

public class UserInterface implements Runnable {

    private static AccountPanel accountpanel = new AccountPanel();
    private static ProfilePanel profilePanel = new ProfilePanel();
    private static MoviePanel moviePanel = new MoviePanel();
    private static SeriePanel seriePanel = new SeriePanel();
    private static JFrame frame;

    @Override
    public void run() {
        frame = new JFrame("Netflix statistix");
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createComponents(frame.getContentPane());
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

    public JPanel leftButtons() {
        JPanel panel = new JPanel();
        JTabbedPane pane = new JTabbedPane();
        pane.setTabPlacement(LEFT);
        pane.addTab("Account", accountpanel);
        pane.addTab("Profiel", profilePanel);
        pane.addTab("Movie", moviePanel);
        pane.addTab("Serie", seriePanel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(pane);

        return panel;
    }

    public JPanel bottomBar() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Netflix Statistix"));
        panel.add(new JLabel("Informatica klas1E - Djim Oomes, Marco van Poortvliet"));
        return panel;
    }

    public static AccountPanel getAccountpanel() {
        return accountpanel;
    }

    public static ProfilePanel getProfilePanel() {
        return profilePanel;
    }

    public static MoviePanel getMoviePanel() {
        return moviePanel;
    }

    public static SeriePanel getSeriePanel() {
        return seriePanel;
    }

    public static JFrame getFrame() {
        return frame;
    }
}