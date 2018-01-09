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
                int id = resultSet.getInt("movieID");
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
                int id = resultSet.getInt("movieID");
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
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM movie\n" +
                    "INNER JOIN video ON movie.videoID = video.videoID\n" +
                    "INNER JOIN watched ON movie.videoID = video.videoID\n" +
                    "WHERE watched.profileID = ?");
            statement.setInt(1, p.getProfileID());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("movieID");
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
}
