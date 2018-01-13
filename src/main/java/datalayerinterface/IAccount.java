package datalayerinterface;

import model.Account;

import java.util.List;

public interface IAccount {

    public List getAllAccounts();

    public Account getAccountByID(int id);

    public void createAccount(Account a);

    public void updateAccount(Account a);

    public void deleteAccount(Account a);

    public List getAccountsWithOneProfile();


}
