package model;

import datalayer.MysqlDAO;
import datalayer.SerieDAO;
import view.UserInterface;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        if (MysqlDAO.getInstance().connect() != null) {
            UserInterface ui = new UserInterface();
            SwingUtilities.invokeLater(ui);
        } else {
            System.out.println("Geen database connection");
        }



    }

}
