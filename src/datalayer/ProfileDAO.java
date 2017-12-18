package datalayer;

import datalayerinterface.IProfile;
import model.Profile;

import java.util.List;

public class ProfileDAO implements IProfile {

    private static ProfileDAO instance;

    private ProfileDAO() {
    }

    public static ProfileDAO getInstance() {
        if (instance == null) {
            instance = new ProfileDAO();
        }
        return instance;
    }

    @Override
    public List getAllProfiles() {
        return null;
    }

    @Override
    public Profile getProfileByID(int id) {
        return null;
    }

    @Override
    public void createProfile(Profile p) {

    }

    @Override
    public void updateProfile(Profile p) {

    }

    @Override
    public void deleteProfile(Profile p) {

    }
}
