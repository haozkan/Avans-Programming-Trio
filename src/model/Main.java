package model;

import datalayer.AccountDAO;

public class Main {

    public static void main(String[] args) {
//        UserInterface ui = new UserInterface();
//        SwingUtilities.invokeLater(ui);

        AccountDAO dao = AccountDAO.getInstance();

        Account a = dao.getAccountByID(1);
        a.setAccountName("Henk");
        dao.updateAccount(a);

    }

}
