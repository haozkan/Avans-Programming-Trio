package datalayer;

import datalayerinterface.IMovie;
import model.Movie;
import model.Profile;
import model.Video;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO implements IMovie {

    private static MovieDAO instance;

    private MovieDAO() {
    }

    public static MovieDAO getInstance() {
        if (instance == null) {
            instance = new MovieDAO();
        }
        return instance;
    }

    @Override
    public List<Movie> getAllMovies() {
        ArrayList<Movie> movies = new ArrayList<>();
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM movie\n" +
                    "INNER JOIN video ON movie.videoID = video.videoID");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("videoID");
                String title = resultSet.getString("videoTitle");
                String duration = resultSet.getString("durage");
                String genre = resultSet.getString("genre");
                String language = resultSet.getString("language");
                int ageRating = resultSet.getInt("ageClassification");

                Movie m = new Movie(id, title, duration, genre, language, ageRating);
                movies.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
        return movies;
    }

    @Override
    public Movie getMovieByID(int movieID) {
        Connection conn = null;
        Movie m = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM movie\n" +
                    "INNER JOIN video ON movie.videoID = video.videoID WHERE movie.videoID = ?");
            statement.setInt(1, movieID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("videoID");
                String title = resultSet.getString("videoTitle");
                String duration = resultSet.getString("durage");
                String genre = resultSet.getString("genre");
                String language = resultSet.getString("language");
                int ageRating = resultSet.getInt("ageClassification");

                m = new Movie(id, title, duration, genre, language, ageRating);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
        return m;
    }

    @Override
    public List<Movie> getWatchedMoviesByProfile(Profile p) {
        ArrayList<Movie> movies = new ArrayList<>();
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM watched\n" +
                    "JOIN movie ON watched.videoID = movie.videoID\n" +
                    "JOIN video ON movie.videoID = video.videoID\n" +
                    "WHERE profileID = ?;");
            statement.setInt(1, p.getProfileID());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("videoID");
                String title = resultSet.getString("videoTitle");
                String duration = resultSet.getString("durage");
                String genre = resultSet.getString("genre");
                String language = resultSet.getString("language");
                int ageRating = resultSet.getInt("ageClassification");

                Movie m = new Movie(id, title, duration, genre, language, ageRating);
                movies.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
        return movies;
    }

    public int getCountWatched(Movie m) {
        Connection conn = null;
        int watchcount = -1;
        try {

            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement("SELECT COUNT(watched.videoID) AS count FROM watched\n" +
                    "JOIN movie ON watched.videoID = movie.videoID\n" +
                    "WHERE movie.videoID = ?");
            statement.setInt(1, m.getId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                watchcount = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
        return watchcount;
    }

    public int getWatchedPercentage(Movie m, Profile p) {
        int watchedpercentage = 0;
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM watched WHERE videoID = ? AND profileID = ?");
            statement.setInt(1, m.getId());
            statement.setInt(2, p.getProfileID());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                watchedpercentage = resultSet.getInt("percentage");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
        return watchedpercentage;
    }

    public void updateWatchedPercentage(Movie m, Profile p, int percentage) {
        int watchedpercentage = 0;
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement("UPDATE `watched` SET `percentage` =? WHERE `videoID`=? AND profileID = ?");
            statement.setInt(1, percentage);
            statement.setInt(2, m.getId());
            statement.setInt(3, p.getProfileID());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
    }

    public void addWatchedPercentage(Movie m, Profile p, int percentage) {
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement("INSERT INTO `watched`(percentage, videoID, profileID) VALUES(?, ?, ?)");
            statement.setInt(1, percentage);
            statement.setInt(2, m.getId());
            statement.setInt(3, p.getProfileID());
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
    }

    public void deleteWatchedPercentage(Movie m, Profile p) {
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement("DELETE FROM `watched` WHERE `videoID`=? AND profileID = ?");
            statement.setInt(1, m.getId());
            statement.setInt(2, p.getProfileID());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
    }
}
