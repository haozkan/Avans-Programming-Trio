package datalayerinterface;

import model.Movie;
import model.Profile;

import java.util.List;

public interface IMovie {

    public List getAllMovies();

    public Movie getMovieByID(int id);

    public List getWatchedMoviesByProfile(Profile p);
}
