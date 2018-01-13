package datalayer;

import datalayerinterface.ISerie;
import model.Episode;
import model.Profile;
import model.Serie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SerieDAO implements ISerie {

    private static SerieDAO instance;

    private SerieDAO() {
    }

    public static SerieDAO getInstance() {
        if (instance == null) {
            instance = new SerieDAO();
        }
        return instance;
    }

    @Override
    public List<Serie> getAllSeries() {
        ArrayList<Serie> series = new ArrayList<>();
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM serie");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("serieId");
                String name = resultSet.getString("serieName");
                int ageRating = resultSet.getInt("ageClassification");
                String language = resultSet.getString("language");
                String genre = resultSet.getString("genre");
                String suggestion = resultSet.getString("suggestion");

                Serie s = new Serie(id, name, ageRating, language, genre, suggestion);
                series.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
        return series;
    }

    @Override
    public Serie getSerieByID(int s) {
        Connection conn = null;
        Serie serie = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM serie WHERE serieID = ?");
            statement.setInt(1, s);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("serieId");
                String name = resultSet.getString("serieName");
                int ageRating = resultSet.getInt("ageClassification");
                String language = resultSet.getString("language");
                String genre = resultSet.getString("genre");
                String suggestion = resultSet.getString("suggestion");

                serie = new Serie(id, name, ageRating, language, genre, suggestion);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
        return serie;
    }

    @Override
    public List<Episode> getAllEpisodesBySerie(Serie s) {
        ArrayList<Episode> episodes = new ArrayList<>();
        Connection conn = null;
        Serie serie = null;
        Episode episode = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM serie\n" +
                    "INNER JOIN episode ON episode.serieID = serie.serieID\n" +
                    "INNER JOIN video ON video.videoID = episode.videoID\n" +
                    "WHERE serie.serieID = ?");
            statement.setInt(1, s.getID());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("episodeID");
                String title = resultSet.getString("videoTitle");
                String duration = resultSet.getString("durage");
                int season = resultSet.getInt("season");

                Episode e = new Episode(id, title, duration, season);
                episodes.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return episodes;
    }

    @Override
    public int getAverageWatchTime(Serie s) {
        Connection conn = null;
        int avgWatchTime = 0;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement("SELECT AVG(percentage) AS avgPercentage FROM watched\n" +
                    "INNER JOIN episode ON episode.videoID = watched.videoID\n" +
                    "WHERE SerieID = ?");
            statement.setInt(1, s.getID());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                avgWatchTime = resultSet.getInt("avgPercentage");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
        return avgWatchTime;
    }

    @Override
    public List<Serie> getWatchedSeriesByProfile(Profile p) {
        ArrayList<Serie> series = new ArrayList<>();
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM serie \n" +
                    "INNER JOIN episode ON episode.serieID = serie.serieID \n" +
                    "INNER JOIN watched ON watched.videoID = episode.videoID \n" +
                    "INNER JOIN profile ON profile.profileID = watched.profileID \n " +
                    "WHERE profile.profileID = ?");
            statement.setInt(1, p.getProfileID());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("serieID");
                String name = resultSet.getString("serieName");
                int age = resultSet.getInt("ageClassification");
                String language = resultSet.getString("language");
                String genre = resultSet.getString("genre");

                Serie s = new Serie(id, name, age, language, genre);
                series.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }

        return series;
    }
}
