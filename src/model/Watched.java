package model;

public class Watched {
    private double percentage;
    private int id;
    private String profileName;

    public double getPercentage(){
        return this.percentage;
    }

    public void setPercentage(double percentage){
        this.percentage = percentage;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getProfileName(){
        return this.profileName;
    }

    public void setProfileName(String name){
        this.profileName = name;
    }
}
