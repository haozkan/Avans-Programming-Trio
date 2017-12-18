package datalayer;

import datalayerinterface.IEpisode;
import model.Episode;
import model.Profile;

import java.util.List;

public class EpisodeDAO implements IEpisode{

    private static EpisodeDAO instance;

    private EpisodeDAO() {}

    public static EpisodeDAO getInstance() {
        if (instance == null) {
            instance = new EpisodeDAO();
        }
        return instance;
    }

    @Override
    public List getAllEpisodes() {
        return null;
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
