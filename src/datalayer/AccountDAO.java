package datalayer;

import datalayerinterface.IAccount;
import model.Account;

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
    public List getAllAccounts() {
        return null;
    }

    @Override
    public Account getAccountByID(int id) {
        return null;
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
