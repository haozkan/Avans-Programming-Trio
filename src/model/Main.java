package model;

import datalayer.SerieDAO;
import view.UserInterface;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        SwingUtilities.invokeLater(ui);

//        EpisodeDAO e = EpisodeDAO.getInstance();
//
//        for (Episode ep : e.getAllEpisodes()) {
//            System.out.println(ep);
//        }

//        ProfileDAO p = ProfileDAO.getInstance();
//
//        for (Profile pr : p.getAllProfiles()) {
//            System.out.println(pr);
//        }
//
//        System.out.println(p.getProfileByID(3));

//        SerieDAO a = SerieDAO.getInstance();
//        System.out.println(a.getSerieByID(1));

    }

}
