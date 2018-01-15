package model;

public abstract class Video {

    private int id;
    private String title;
    private String duration;

    public Video() {
    }

    public Video(int id, String title, String duration) {
        this.id = id;
        this.title = title;
        this.duration = duration;
    }

    public Video(String title, String duration) {
        this.title = title;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return this.getTitle();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

}
