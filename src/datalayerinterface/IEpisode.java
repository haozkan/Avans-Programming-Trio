package datalayerinterface;

import model.Episode;
import model.Profile;

import java.util.List;

public interface IEpisode {

    public List getAllEpisodes();

    public Episode getEpisodeByID(int id);

    public List getWatchedEpisodesByProfile(Profile p);
}
