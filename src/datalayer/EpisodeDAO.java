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
                int id = resultSet.getInt("episodeID");
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
        return null;
    }

    @Override
    public List getWatchedEpisodesByProfile(Profile p) {
        return null;
    }
}
