package model;

import java.sql.Date;

public class Profile {

    private String profileName;
    private Date dateOfBirth;
    private int profileID;
    private int accountID;

    public Profile() {

    }

    public Profile(String profileName, Date dateOfBirth) {
        this.profileName = profileName;
        this.dateOfBirth = dateOfBirth;
    }

    public Profile(String profileName, Date dateOfBirth, int profileID) {
        this.profileName = profileName;
        this.dateOfBirth = dateOfBirth;
        this.profileID = profileID;
    }

    public Profile(String profileName, Date dateOfBirth, int profileID, int accountID) {
        this.profileName = profileName;
        this.dateOfBirth = dateOfBirth;
        this.profileID = profileID;
        this.accountID = accountID;
    }

    public Profile(String profileName, Date dateOfBirth, int randomInt, int anotherRandom, int accountID){
        this.profileName=profileName;
        this.dateOfBirth = dateOfBirth;
        this.accountID=accountID;
    }


    public int getProfileID() {
        return this.profileID;
    }

    @Override
    public String toString() {
        return this.getProfileName();
    }

    public void setProfileID(int id) {
        this.profileID = id;
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

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }
}
