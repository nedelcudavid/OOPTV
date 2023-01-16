package platform.changing_database_system;

import platform.Database;
import platform.Executable;
import platform.Movie;
import platform.RegisteredUser;
import platform.notifications_system.Notification;
import platform.notifications_system.NotificationsFactory;

import java.util.ArrayList;

public class AddMovieObserver extends Observer{

    public AddMovieObserver(DatabaseChange databaseChange) {
        this.databaseChange = databaseChange;
        this.databaseChange.attach(this);
    }

    @Override
    public void update(String changeType, Movie movieToAdd) {
        if (changeType.equals("ADD")) {
            Database.getContent().getMoviesDB().add(movieToAdd);

            NotificationsFactory notificationsFactory = new NotificationsFactory();
            Notification notificationToAdd = notificationsFactory.createNotification("ADD", movieToAdd.getName());

            for (RegisteredUser user : Database.getContent().getUsersDB()) {
                if (containsAny(user.getSubscribedGenres(), movieToAdd.getGenres()) &&
                        !movieToAdd.getCountriesBanned().contains(user.getCredentials().getCountry())) {
                    notificationToAdd.addNotificationToUser(user);
                    RegisteredUser currentUser = Executable.getExe().getCurrentUser();
                    if (currentUser.getCredentials().getName().equals(user.getCredentials().getName())) {
                        notificationToAdd.addNotificationToUser(currentUser);
                    }
                }
            }
        }
    }

    private static boolean containsAny(ArrayList<String> l1, ArrayList<String> l2) {
        for (String elem : l1) {
            if (l2.contains(elem)) {
                return true;
            }
        }
        return false;
    }
}
