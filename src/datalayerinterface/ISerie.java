package datalayerinterface;

import model.Serie;

import java.util.List;

public interface ISerie {

    public List getAllSeries();

    public Serie getSerieByID(Serie s);

    public List getAllEpisodesBySerie(Serie s);
}
