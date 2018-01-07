package model;

public class Movie extends Video {

    private String genre;
    private String language;
    private int ageRating;

    public Movie() {

    }

    public Movie(int id, String title, String duration, String genre, String language, int ageRating) {
        super(id, title, duration);
        this.genre = genre;
        this.language = language;
        this.ageRating = ageRating;
    }

    public Movie(String title, String duration, String genre, String language, int ageRating) {
        super(title, duration);
        this.genre = genre;
        this.language = language;
        this.ageRating = ageRating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "genre='" + genre + '\'' +
                ", language='" + language + '\'' +
                ", ageRating=" + ageRating +
                "} " + super.toString();
    }

    @Override
    double getWatchedPercentage() {
        return 0;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(int ageRating) {
        this.ageRating = ageRating;
    }
}
