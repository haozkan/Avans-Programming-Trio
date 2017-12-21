package model;

import datalayer.AccountDAO;
import datalayer.EpisodeDAO;
import datalayer.ProfileDAO;

public class Main {

    public static void main(String[] args) {
//        UserInterface ui = new UserInterface();
//        SwingUtilities.invokeLater(ui);

//        EpisodeDAO e = EpisodeDAO.getInstance();
//
//        for (Episode ep : e.getAllEpisodes()) {
//            System.out.println(ep);
//        }

        ProfileDAO p = ProfileDAO.getInstance();

        for (Profile pr : p.getAllProfiles()) {
            System.out.println(pr);
        }

        p.deleteProfile(p.getProfileByID(1));

    }

}
