package datalayerinterface;

import model.Profile;
import model.Serie;

import java.util.List;

public interface ISerie {

    public List getAllSeries();

    public Serie getSerieByID(int s);

    public List getAllEpisodesBySerie(Serie s);

    public int getAverageWatchTime(Serie s);

    public List getWatchedSeriesByProfile(Profile p);

}
