package datalayer;

import datalayerinterface.IMovie;
import model.Movie;
import model.Profile;

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
    public List getAllMovies() {
        return null;
    }

    @Override
    public Movie getMovieByID(int id) {
        return null;
    }

    @Override
    public List getWatchedMoviesByProfile(Profile p) {
        return null;
    }
}
