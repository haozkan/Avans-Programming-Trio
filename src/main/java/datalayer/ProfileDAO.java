package datalayer;

import datalayerinterface.IProfile;
import model.Account;
import model.Profile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
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
    public List<Profile> getAllProfiles() {
        ArrayList<Profile> profiles = new ArrayList<>();
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM profile");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int profileID = resultSet.getInt("profileID");
                int accountID = resultSet.getInt("accountID");
                String profileName = resultSet.getString("profileName");
                Date dateofBirth = resultSet.getDate("dateofBirth");

                Profile p = new Profile(profileName, dateofBirth, profileID, accountID);
                profiles.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
        return profiles;
    }

    @Override
    public Profile getProfileByID(int profileID) {
        Connection conn = null;
        Profile p = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM profile WHERE profileID = ?");
            statement.setInt(1, profileID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("profileID");
                String profileName = resultSet.getString("profileName");
                Date dateofBirth = resultSet.getDate("dateofBirth");
                int accountID = resultSet.getInt("accountID");
                p = new Profile(profileName, dateofBirth, profileID, accountID);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
        return p;
    }

    @Override
    public void createProfile(Profile p) {
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement("" + "INSERT INTO `profile` " +
                    "(`profileID`, `accountID`, `profileName`, `dateofBirth`) VALUES (?,?,?,?)");
            statement.setInt(1, p.getProfileID());
            statement.setInt(2, p.getAccountID());
            statement.setString(3, p.getProfileName());
            statement.setDate(4, p.getDateOfBirth());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }

    }

    @Override
    public void updateProfile(Profile p) {
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement("UPDATE `profile` SET `profileName` = ?," +
                    "`dateofBirth` =? WHERE profileID = ?");
            statement.setString(1, p.getProfileName());
            statement.setDate(2, p.getDateOfBirth());
            statement.setInt(3,p.getProfileID());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
    }

    @Override
    public void deleteProfile(Profile p) {
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement(""
                    + "DELETE FROM profile "
                    + "WHERE profileID = ?");
            statement.setInt(1, p.getProfileID());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
    }

    @Override
    public List<Profile> getProfilesByAccount(Account a) {
        Connection conn = null;
        ArrayList<Profile> profilesAccount = new ArrayList<>();
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM profile WHERE accountID = ?");
            statement.setInt(1, a.getAccountNumber());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int profileID = resultSet.getInt("profileID");
                int accountID = resultSet.getInt("accountID");
                String profileName = resultSet.getString("profileName");
                Date dateofBirth = resultSet.getDate("dateofBirth");

                Profile p = new Profile(profileName, dateofBirth, profileID, accountID);
                profilesAccount.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
        return profilesAccount;
    }
}
