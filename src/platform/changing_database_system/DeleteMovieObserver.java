package platform.changing_database_system;

import platform.Database;
import platform.Movie;
import platform.RegisteredUser;
import platform.notifications_system.Notification;
import platform.notifications_system.NotificationsFactory;

public class DeleteMovieObserver extends Observer {

    public DeleteMovieObserver(final DatabaseChange databaseChange) {
        this.databaseChange = databaseChange;
        this.databaseChange.attach(this);
    }

    /** When this observer is notified that a database
     *  change is made it verifies if it is "DELETE" type and
     *  if so it updates the database accordingly */
    @Override
    public void update(final String changeType, final Movie movieToDelete) {
        if (changeType.equals("DELETE")) {
            for (int i = 0; i < Database.getContent().getMoviesDB().size(); i++) {
                if (Database.getContent().getMoviesDB().get(i).getName()
                        .equals(movieToDelete.getName())) {
                    Database.getContent().getMoviesDB().remove(i);
                    break;
                }
            }

            NotificationsFactory notificationsFactory = new NotificationsFactory();
            Notification notificationToAdd =
                    notificationsFactory.createNotification("DELETE", movieToDelete.getName());

            for (RegisteredUser user : Database.getContent().getUsersDB()) {
                if (user.getPurchasedMoviesNames().contains(movieToDelete.getName())) {
                    for (int i = 0; i < user.getPurchasedMovies().size(); i++) {
                        if (user.getPurchasedMovies().get(i).getName()
                                .equals(movieToDelete.getName())) {
                            user.getPurchasedMovies().remove(i);
                            break;
                        }
                    }

                    for (int i = 0; i < user.getWatchedMovies().size(); i++) {
                        if (user.getWatchedMovies().get(i).getName()
                                .equals(movieToDelete.getName())) {
                            user.getWatchedMovies().remove(i);
                            break;
                        }
                    }

                    for (int i = 0; i < user.getLikedMovies().size(); i++) {
                        if (user.getLikedMovies().get(i).getName()
                                .equals(movieToDelete.getName())) {
                            user.getLikedMovies().remove(i);
                            break;
                        }
                    }

                    for (int i = 0; i < user.getRatedMovies().size(); i++) {
                        if (user.getRatedMovies().get(i).getName()
                                .equals(movieToDelete.getName())) {
                            user.getRatedMovies().remove(i);
                            break;
                        }
                    }

                    if (user.getCredentials().getAccountType().equals("premium")) {
                        user.addNumFreePremiumMovies(1);
                    } else {
                        user.addTokensCount(2);
                    }
                    notificationToAdd.addNotificationToUser(user);
                }
            }
        }
    }
}
