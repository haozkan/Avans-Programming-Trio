package model;

import datalayer.AccountDAO;
import datalayer.EpisodeDAO;

public class Main {

    public static void main(String[] args) {
//        UserInterface ui = new UserInterface();
//        SwingUtilities.invokeLater(ui);

        EpisodeDAO e = EpisodeDAO.getInstance();

        for (Episode ep : e.getAllEpisodes()) {
            System.out.println(ep);
        }

    }

}
