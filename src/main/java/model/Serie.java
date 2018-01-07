package model;

public class Serie {

    private int id;
    private String name;
    private int ageRating;
    private String language;
    private String genre;
    private String suggestion;

    public Serie() {

    }

    public Serie(int id, String name, int ageRating, String language, String genre, String suggestion) {
        this.id = id;
        this.name = name;
        this.ageRating = ageRating;
        this.language = language;
        this.genre = genre;
        this.suggestion = suggestion;
    }

    public int getID(){
        return id;
    }

    public void setID(int id){
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(int ageRating) {
        this.ageRating = ageRating;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    @Override
    public String toString() {
        return "Serie{" +
                "serieID=" + id +
                ", serieName='" + name + '\'' +
                ", ageRating='" + ageRating + '\'' +
                ", language='" + language + '\'' +
                ", genre='" + genre + '\'' +
                ", suggestion='" + suggestion + '\'' +
                '}';
    }
}
