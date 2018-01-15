package view;

import controller.PercentageVerifier;
import datalayer.*;

import javax.swing.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.*;

public class ManageWatchedPanel extends JFrame {

    private JDialog frame;
    private Movie m;
    private Episode e;

    ManageWatchedPanel(int videoID, int profileID) {
        frame = new JDialog(UserInterface.getFrame(), "Edit serie");
        frame.setPreferredSize(new Dimension(400, 150));
        frame.setLocationRelativeTo(UserInterface.getFrame());
        frame.setResizable(Boolean.FALSE);
        createComponents(frame.getContentPane(), videoID, profileID);
        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container, int videoID, int profileID) {
        container.setLayout(new BorderLayout());

        // Add padding to dialog
        JPanel panel = (JPanel) frame.getContentPane();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));

        // Components
        JPanel buttonPanel = new JPanel();
        JPanel percentagePanel = new JPanel();
        JLabel percentageLabel = new JLabel("Percentage");
        JTextField percentage = new JTextField();
        JButton saveBtn = new JButton("Save");
        JButton cancelBtn = new JButton("Cancel");
        JButton deleteButton = new JButton("Delete");

        percentagePanel.setLayout(new GridLayout(2, 1));
        buttonPanel.setLayout(new FlowLayout());

        e = null;
        m = null;
        Profile p = ProfileDAO.getInstance().getProfileByID(profileID);

        // Episode
        if (EpisodeDAO.getInstance().getEpisodeByID(videoID) != null) {
            e = EpisodeDAO.getInstance().getEpisodeByID(videoID);

            percentage.setText(String.valueOf(EpisodeDAO.getInstance().getWatchedPercentage(e, p)));

            saveBtn.addActionListener(e1 -> {
                EpisodeDAO.getInstance().updateWatchedPercentage(e, p, Integer.parseInt(percentage.getText()));
                frame.dispose();
            });

            deleteButton.addActionListener(ee -> {
                EpisodeDAO.getInstance().deleteWatchedPercentage(e, p);
                frame.dispose();
            });

            ProfilePanel.updateMovieTable();

        }

        // Movie
        else if (MovieDAO.getInstance().getMovieByID(videoID) != null) {
            m = MovieDAO.getInstance().getMovieByID(videoID);

            percentage.setText(String.valueOf(MovieDAO.getInstance().getWatchedPercentage(m, p)));

            saveBtn.addActionListener(e1 -> {
                MovieDAO.getInstance().updateWatchedPercentage(m, p, Integer.parseInt(percentage.getText()));
                frame.dispose();
            });

            deleteButton.addActionListener(e -> {
                MovieDAO.getInstance().deleteWatchedPercentage(m, p);
                frame.dispose();
            });

            ProfilePanel.updateMovieTable();

        } else {
            System.out.println("No video found");
        }

        // Add Components
        buttonPanel.add(saveBtn);
        buttonPanel.add(cancelBtn);
        buttonPanel.add(deleteButton);
        percentagePanel.add(percentageLabel);
        percentagePanel.add(percentage);
        panel.add(percentagePanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        cancelBtn.addActionListener(e -> {
            frame.dispose();
        });
    }

}
