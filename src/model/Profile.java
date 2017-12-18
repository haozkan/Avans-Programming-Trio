package model;

import java.util.Date;

public class Profile {

    private String profileName;
    private Date dateOfBirth;

    public Profile() {

    }

    public Profile(String profileName, Date dateOfBirth) {
        this.profileName = profileName;
        this.dateOfBirth = dateOfBirth;
    }

    public String getProfileName() {
        return this.profileName;
    }

    public void setProfileName(String name) {
        this.profileName = name;
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(Date date) {
        this.dateOfBirth = date;
    }
}
