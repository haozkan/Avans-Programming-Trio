package view;

import datalayer.EpisodeDAO;
import datalayer.MovieDAO;
import datalayer.ProfileDAO;
import model.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Objects;

public class AddWatchedPanel extends JFrame {

    private JDialog frame;

    public AddWatchedPanel() {
        frame = new JDialog(UserInterface.getFrame(), "Add Watched");
        frame.setPreferredSize(new Dimension(400, 250));
        frame.setLocationRelativeTo(UserInterface.getFrame());
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

        // Create inputfields
        JPanel inputFields = new JPanel();
        inputFields.setLayout(new GridLayout(0, 1));
        JComboBox<String> videoType = new JComboBox<>();
        JComboBox<Movie> video = new JComboBox<>();
        JComboBox<Episode> episode = new JComboBox<>();
        JComboBox<Profile> profile = new JComboBox<>();
        JTextField percentage = new JTextField();

        inputFields.add(videoType);
        videoType.addItem("Episode");
        videoType.addItem("Film");
        inputFields.add(new JLabel("Video"));
        inputFields.add(video);
        inputFields.add(episode);
        inputFields.add(new JLabel("Profiel"));
        inputFields.add(profile);
        inputFields.add(new JLabel("Percentage"));
        inputFields.add(percentage);
        container.add(inputFields, BorderLayout.CENTER);

        JPanel bottomButtons = new JPanel();
        bottomButtons.setLayout(new FlowLayout());

        JButton addButton = new JButton("Opslaan");
        JButton cancelButton = new JButton("Annuleren");

        bottomButtons.add(addButton);
        bottomButtons.add(cancelButton);

        videoType.addItemListener(e -> {
            if (Objects.equals(videoType.getSelectedItem(), "Film")) {
                video.removeAllItems();
                episode.setEnabled(false);
                video.setEnabled(true);

                for (Movie m : MovieDAO.getInstance().getAllMovies()) {
                    video.addItem(m);
                }

            } else if (Objects.equals(videoType.getSelectedItem(), "Episode")) {
                episode.removeAllItems();
                video.setEnabled(false);
                episode.setEnabled(true);

                for (Episode ep : EpisodeDAO.getInstance().getAllEpisodes()) {
                    episode.addItem(ep);
                }

            }
        });

        for (Profile p : ProfileDAO.getInstance().getAllProfiles()) {
            profile.addItem(p);
        }

        addButton.addActionListener(e -> {
            if (Objects.equals(videoType.getSelectedItem(), "Episode")) {
                Profile p = (Profile) profile.getSelectedItem();
                Episode ep = (Episode) episode.getSelectedItem();
                int per = Integer.valueOf(percentage.getText());
                EpisodeDAO.getInstance().addWatchedPercentage(ep, p, per);
            } else if (Objects.equals(videoType.getSelectedItem(), "Film")) {
                Profile p = (Profile) profile.getSelectedItem();
                Movie m = (Movie) video.getSelectedItem();
                int per = Integer.valueOf(percentage.getText());
                MovieDAO.getInstance().addWatchedPercentage(m, p, per);
            }

            ProfilePanel.updateMovieTable();
            frame.dispose();
        });

        cancelButton.addActionListener(e -> {
            frame.dispose();
        });

        container.add(bottomButtons, BorderLayout.SOUTH);
    }

}
