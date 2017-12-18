package model;

import datalayer.MysqlDAO;
import view.UserInterface;
import javax.swing.*;
import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
//        UserInterface ui = new UserInterface();
//        SwingUtilities.invokeLater(ui);

        Connection conn = MysqlDAO.getInstance().connect();
        System.out.println(conn);
    }

}
