package datalayer;

import datalayerinterface.IEpisode;
import model.Episode;
import model.Movie;
import model.Profile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EpisodeDAO implements IEpisode {

    private static EpisodeDAO instance;

    private EpisodeDAO() {
    }

    public static EpisodeDAO getInstance() {
        if (instance == null) {
            instance = new EpisodeDAO();
        }
        return instance;
    }

    @Override
    public List<Episode> getAllEpisodes() {
        ArrayList<Episode> episodes = new ArrayList<>();
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM episode " +
                    "INNER JOIN video ON episode.videoID = video.videoID");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("videoID");
                String title = resultSet.getString("videoTitle");
                String duration = resultSet.getString("durage");
                int season = resultSet.getInt("season");

                Episode e = new Episode(id, title, duration, season);
                episodes.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
        return episodes;
    }

    @Override
    public Episode getEpisodeByID(int id) {
        Connection conn = null;
        Episode epi = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM episode\n" +
                    "INNER JOIN video ON video.videoID = episode.videoID WHERE episode.videoID = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int epId = resultSet.getInt("videoID");
                String title = resultSet.getString("videoTitle");
                String duration = resultSet.getString("durage");
                int season = resultSet.getInt("season");

                epi = new Episode(epId, title, duration, season);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
        return epi;
    }

    @Override
    public List<Episode> getWatchedEpisodesByProfile(Profile p) {
        ArrayList<Episode> episodes = new ArrayList<>();
        Episode epi = null;
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM episode \n" +
                    "INNER JOIN video ON episode.videoID = video.videoID \n" +
                    "INNER JOIN watched ON watched.videoID = episode.videoID \n" +
                    "INNER JOIN profile ON profile.profileID = watched.profileID \n " +
                    "WHERE profile.profileID = ?");
            statement.setInt(1, p.getProfileID());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int epId = resultSet.getInt("videoID");
                String title = resultSet.getString("videoTitle");
                String duration = resultSet.getString("durage");
                int season = resultSet.getInt("season");

                epi = new Episode(epId, title, duration, season);
                episodes.add(epi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }

        return episodes;
    }

    @Override
    public int getAverageWatchtime(Episode e) {
        Connection conn = null;
        int avgWatchTime = 0;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement("SELECT AVG(watched.percentage) AS avgPercentage FROM episode\n" +
                    "LEFT JOIN watched ON watched.videoID = episode.videoID\n" +
                    "WHERE episode.episodeID = ?");
            statement.setInt(1, e.getId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                avgWatchTime = resultSet.getInt("avgPercentage");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
        return avgWatchTime;
    }

    public int getWatchedPercentage(Episode e, Profile p) {
        int watchedpercentage = 0;
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM watched WHERE videoID = ? AND profileID = ?");
            statement.setInt(1, e.getId());
            statement.setInt(2, p.getProfileID());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                watchedpercentage = resultSet.getInt("percentage");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
        return watchedpercentage;
    }

    public void updateWatchedPercentage(Episode e, Profile p, int percentage) {
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement("UPDATE `watched` SET `percentage` = ? WHERE `videoID` = ? AND profileID = ?");
            statement.setInt(1, percentage);
            statement.setInt(2, e.getId());
            statement.setInt(3, p.getProfileID());
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
    }

    public void addWatchedPercentage(Episode e, Profile p, int percentage) {
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement("INSERT INTO `watched`(profileID, videoID, percentage) VALUES(?, ?, ?)");
            statement.setInt(1, p.getProfileID());
            statement.setInt(2, e.getId());
            statement.setInt(3, percentage);
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
    }

    public void deleteWatchedPercentage(Episode e, Profile p) {
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement("DELETE FROM `watched` WHERE `videoID`=? AND profileID = ?");
            statement.setInt(1, e.getId());
            statement.setInt(2, p.getProfileID());
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
    }
}