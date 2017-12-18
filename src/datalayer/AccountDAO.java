package datalayer;

import datalayerinterface.IAccount;
import model.Account;
import model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO implements IAccount {

    private static AccountDAO instance;

    private AccountDAO() {
    }

    public static AccountDAO getInstance() {
        if (instance == null) {
            instance = new AccountDAO();
        }
        return instance;
    }

    @Override
    public List<Account> getAllAccounts() {
        ArrayList<Account> accounts = new ArrayList<>();
        Connection conn = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM account");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int accountID = resultSet.getInt("accountID");
                String accountName = resultSet.getString("accountName");
                String streetName = resultSet.getString("streetName");
                String houseNumber = resultSet.getString("houseNumber");
                String zipCode = resultSet.getString("zipcode");
                String residence = resultSet.getString("residence");

                Account a = new Account(accountID, accountName, streetName, houseNumber, zipCode, residence);
                accounts.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
        return accounts;
    }

    @Override
    public Account getAccountByID(int accountID) {
        Connection conn = null;
        Account a = null;
        try {
            conn = MysqlDAO.getInstance().connect();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM account WHERE accountID = ?");
            statement.setInt(1, accountID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("accountID");
                String accountName = resultSet.getString("accountName");
                String streetName = resultSet.getString("streetName");
                String houseNumber = resultSet.getString("houseNumber");
                String zipCode = resultSet.getString("zipcode");
                String residence = resultSet.getString("residence");

                a = new Account(id, accountName, streetName, houseNumber, zipCode, residence);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MysqlDAO.getInstance().closeConnection(conn);
        }
        return a;
    }

    @Override
    public void createAccount(Account a) {

    }

    @Override
    public void updateAccount(Account a) {

    }

    @Override
    public void deleteAccount(Account a) {

    }
}
