package datalayerinterface;

import model.Profile;

import java.util.List;

public interface IProfile {

    public List getAllProfiles();

    public Profile getProfileByID(int id);

    public void createProfile(Profile p);

    public void updateProfile(Profile p);

    public void deleteProfile(Profile p);

}
