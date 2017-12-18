package model;

public class Episode extends Video {

    private int season;

    public Episode(int id, String title, String duration, int season) {
        super(id, title, duration);
        this.season = season;
    }

    public Episode(String title, String duration, int season) {
        super(title, duration);
        this.season = season;
    }

    @Override
    double getWatchedPercentage() {
        return 0;
    }

    @Override
    double getWatchedPercentageByMovie(Movie m) {
        return 0;
    }

    @Override
    double getWatchedPercentageByEpisode(Episode e) {
        return 0;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

}
