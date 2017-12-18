package model;

import datalayer.MovieDAO;
import datalayer.MysqlDAO;
import view.UserInterface;
import javax.swing.*;
import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
//        UserInterface ui = new UserInterface();
//        SwingUtilities.invokeLater(ui);

        MovieDAO movieDAO = MovieDAO.getInstance();

        for (Movie m : movieDAO.getAllMovies()) {
            System.out.println(m);
        }

    }

}
