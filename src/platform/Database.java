package platform;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import input.InputAction;
import input.InputMovie;
import platform.changing_database_system.AddMovieObserver;
import platform.changing_database_system.DatabaseChange;
import platform.changing_database_system.DeleteMovieObserver;

import java.util.ArrayList;
import static platform.Executable.displayOutputForError;

/** This singleton class represents a database that holds all movies, users and actions */
public final class Database {

    private final ArrayList<Movie> moviesDB;
    private final ArrayList<RegisteredUser> usersDB;
    private ArrayList<InputAction> actionsDB;
    private final DatabaseChange databaseChange;
    private static Database content = null;

    private Database() {
        moviesDB = new ArrayList<>();
        usersDB = new ArrayList<>();
        actionsDB = new ArrayList<>();
        databaseChange = new DatabaseChange();
        new AddMovieObserver(databaseChange);
        new DeleteMovieObserver(databaseChange);
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

    public ArrayList<RegisteredUser> getUsersDB() {
        return usersDB;
    }

    public ArrayList<InputAction> getActionsDB() {
        return actionsDB;
    }

    public DatabaseChange getDatabaseChange() {
        return databaseChange;
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

    /** This function calls 2 others to add/delete a movie */
    public void modifyDB(final InputAction inputAction, final ObjectNode outputNode,
                               final ArrayNode outputArray) {
        switch (inputAction.getFeature()) {
            case "add" -> addMovie(inputAction.getAddedMovie(), outputNode, outputArray);
            case "delete" -> deleteMovie(inputAction.getDeletedMovie(), outputNode, outputArray);
            default -> System.out.println("Error at adding/removing movies command!");
        }
    }

    /** This function adds a movie if the conditions are met */
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
            getDatabaseChange().databaseChangeIsMade("ADD", movieToAdd);
        }
    }

    /** This function deletes a movie if the conditions are met */
    private void deleteMovie(final String deletedMovie, final ObjectNode outputNode,
                             final ArrayNode outputArray) {
        boolean movieExists = false;
        for (Movie movie : moviesDB) {
            if (movie.getName().equals(deletedMovie)) {
                movieExists = true;
            }
        }

        if (movieExists) {
            Movie movieToDelete = new Movie(deletedMovie);
            getDatabaseChange().databaseChangeIsMade("DELETE", movieToDelete);
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
