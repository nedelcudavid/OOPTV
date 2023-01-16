package platform;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import input.InputAction;
import input.InputMovie;
import platform.notifications_system.AddMovieNotification;
import platform.notifications_system.DeleteMovieNotification;
import platform.notifications_system.Notification;
import platform.notifications_system.NotificationsFactory;

import java.util.ArrayList;
import static platform.Executable.displayOutputForError;

/** This singleton class represents a database that holds all movies, users and actions */
public final class Database {

    private ArrayList<Movie> moviesDB;
    private ArrayList<RegisteredUser> usersDB;
    private ArrayList<InputAction> actionsDB;
    private static Database content = null;

    private Database() {
        moviesDB = new ArrayList<>();
        usersDB = new ArrayList<>();
        actionsDB = new ArrayList<>();
    }

    /** Singleton implementation */
    public static Database getContent() {
        if (content == null) {
            content = new Database();
        }
        return content;
    }

    public ArrayList<Movie> getMoviesDB() {
        return moviesDB;
    }

    public void setMoviesDB(final ArrayList<Movie> moviesDB) {
        this.moviesDB = moviesDB;
    }

    public ArrayList<RegisteredUser> getUsersDB() {
        return usersDB;
    }

    public void setUsersDB(final ArrayList<RegisteredUser> usersDB) {
        this.usersDB = usersDB;
    }

    public ArrayList<InputAction> getActionsDB() {
        return actionsDB;
    }

    public void setActionsDB(final ArrayList<InputAction> actionsDB) {
        this.actionsDB = actionsDB;
    }

    /** This method is created to find easier the current user
     * in the database in order to make changes to it */
    public int findCurrentUserIdx() {
        int wantedIdx;
        for (int i = 0; i < Database.getContent().getUsersDB().size(); i++) {
            if (Database.getContent().getUsersDB().get(i).getCredentials().getName()
                    .equals(Executable.getExe().getCurrentUser().getCredentials().getName())) {
                wantedIdx = i;
                return wantedIdx;
            }
        }
        return -1;
    }

    /** This method is created to find easier the current movie
     * in the database in order to make changes to it */
    public int findCurrentMovieIdx() {
        int wantedIdx;
        for (int i = 0; i < Database.getContent().getMoviesDB().size(); i++) {
            if (Database.getContent().getMoviesDB().get(i).getName()
                    .equals(Executable.getExe().getCurrentMovieList().get(0).getName())) {
                wantedIdx = i;
                return wantedIdx;
            }
        }
        return -1;
    }

    public void modifyDB(final InputAction inputAction, final ObjectNode outputNode,
                               final ArrayNode outputArray) {
        switch (inputAction.getFeature()) {
            case "add" -> addMovie(inputAction.getAddedMovie(), outputNode, outputArray);
            case "delete" -> deleteMovie(inputAction.getDeletedMovie(), outputNode, outputArray);
            default -> System.out.println("Error at adding/removing movies command!");
        }
    }

    private void addMovie(final InputMovie addedMovie, final ObjectNode outputNode,
                          final ArrayNode outputArray) {
        Movie movieToAdd = new Movie(addedMovie);

        boolean movieAlreadyExists = false;
        for (Movie movie : getMoviesDB()) {
            if (movie.getName().equals(movieToAdd.getName())) {
                movieAlreadyExists = true;
            }
        }

        if (movieAlreadyExists) {
            displayOutputForError(outputNode, outputArray);
        } else {
            NotificationsFactory notificationsFactory = new NotificationsFactory();
            Notification notificationToAdd = notificationsFactory.createNotification("ADD", movieToAdd.getName());

            getMoviesDB().add(movieToAdd);
            for (RegisteredUser user : getUsersDB()) {
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

    private void deleteMovie(final String deletedMovie, final ObjectNode outputNode,
                             final ArrayNode outputArray) {
        boolean movieExists = false;
        for (Movie movie : moviesDB) {
            if (movie.getName().equals(deletedMovie)) {
                movieExists = true;
            }
        }

        if (movieExists) {
            for (int i = 0; i < moviesDB.size(); i++) {
                if (moviesDB.get(i).getName().equals(deletedMovie)) {
                    moviesDB.remove(i);
                    break;
                }
            }

            NotificationsFactory notificationsFactory = new NotificationsFactory();
            Notification notificationToAdd = notificationsFactory.createNotification("DELETE", deletedMovie);

            for (RegisteredUser user : usersDB) {
                if (user.getPurchasedMoviesNames().contains(deletedMovie)) {
                    for (int i = 0; i < user.getPurchasedMovies().size(); i++) {
                        if (user.getPurchasedMovies().get(i).getName().equals(deletedMovie)) {
                            user.getPurchasedMovies().remove(i);
                            break;
                        }
                    }

                    for (int i = 0; i < user.getWatchedMovies().size(); i++) {
                        if (user.getWatchedMovies().get(i).getName().equals(deletedMovie)) {
                            user.getWatchedMovies().remove(i);
                            break;
                        }
                    }

                    for (int i = 0; i < user.getLikedMovies().size(); i++) {
                        if (user.getLikedMovies().get(i).getName().equals(deletedMovie)) {
                            user.getLikedMovies().remove(i);
                            break;
                        }
                    }

                    for (int i = 0; i < user.getRatedMovies().size(); i++) {
                        if (user.getRatedMovies().get(i).getName().equals(deletedMovie)) {
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
        } else {
            displayOutputForError(outputNode, outputArray);
        }
    }

    /** This method is created to free the database when needed */
    public void free() {
        moviesDB.clear();
        usersDB.clear();
        actionsDB.clear();
    }
}
