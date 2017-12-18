package datalayer;

import datalayerinterface.ISerie;
import model.Serie;

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
    public List getAllSeries() {
        return null;
    }

    @Override
    public Serie getSerieByID(Serie s) {
        return null;
    }

    @Override
    public List getAllEpisodesBySerie(Serie s) {
        return null;
    }
}
