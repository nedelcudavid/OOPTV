package actions.on_page_actions;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import platform.RegisteredUser;
import platform.Database;
import platform.Executable;
import platform.Movie;
import pages.authenticated_pages.SeeDetailsPage;
import static platform.Executable.displayOutputForError;
import static platform.Executable.displayOutputForSuccessfulAction;

/** This utility class executes the 'like' action */
public final class LikeAction {
    private LikeAction() {
        throw new UnsupportedOperationException("This is a utility class & can't be instantiated");
    }

    /** This function verifies if the conditions for this action are met,
     if they are it adds a like to the current movie, adds it to the
     current user's liked movie list and adds an error/success node in
     the output array accordingly */
    static void like(final ObjectNode outputNode, final ArrayNode outputArray) {
        RegisteredUser currentUser = Executable.getExe().getCurrentUser();

        if (Executable.getExe().getCurrentPage().equals(SeeDetailsPage.getPage())) {
            String currentMovieName = Executable.getExe().getCurrentMovieList().get(0).getName();

            if (currentUser.getPurchasedMoviesNames().contains(currentMovieName)) {
                if (currentUser.getWatchedMoviesNames().contains(currentMovieName)) {
                    if (!currentUser.getLikedMoviesNames().contains(currentMovieName)) {
                        addLikeEverywhere();
                        displayOutputForSuccessfulAction(outputNode, outputArray);
                    } else {
                        displayOutputForError(outputNode, outputArray);
                    }
                } else {
                    displayOutputForError(outputNode, outputArray);
                }
            } else {
                displayOutputForError(outputNode, outputArray);
            }
        } else {
            displayOutputForError(outputNode, outputArray);
        }
    }

    /** This function executes the like action itself and
     adds a like everywhere the movie is found */
    private static void addLikeEverywhere() {
        RegisteredUser currentUser = Executable.getExe().getCurrentUser();

        Movie likedMovie = new Movie(Executable.getExe().getCurrentMovieList().get(0));
        likedMovie.addNumLikes(1);
        currentUser.getLikedMovies().add(likedMovie);
        currentUser.getLikedMoviesNames().add(likedMovie.getName());
        /* Adds a like to the movie and adds it to current player's liked movie list */

        String movieName = Executable.getExe().getCurrentMovieList().get(0).getName();
        for (int i = 0; i < currentUser.getPurchasedMovies().size(); i++) {
            if (currentUser.getPurchasedMovies().get(i).getName().equals(movieName)) {
                Movie updatedMovie = new Movie(currentUser.getPurchasedMovies().get(i));
                updatedMovie.addNumLikes(1);
                currentUser.getPurchasedMovies().set(i, updatedMovie);
                break;
            }
            /* Actualises the movie from the purchased movie list (adds 1 like to it) */
        }
        for (int i = 0; i < currentUser.getWatchedMovies().size(); i++) {
            if (currentUser.getWatchedMovies().get(i).getName().equals(movieName)) {
                Movie updatedMovie = new Movie(currentUser.getWatchedMovies().get(i));
                updatedMovie.addNumLikes(1);
                currentUser.getWatchedMovies().set(i, updatedMovie);
                break;
            }
            /* Actualises the movie from the watched movie list (adds 1 like to it) */
        }
        for (int i = 0; i < currentUser.getRatedMovies().size(); i++) {
            if (currentUser.getRatedMovies().get(i).getName().equals(movieName)) {
                Movie updatedMovie = new Movie(currentUser.getRatedMovies().get(i));
                updatedMovie.addNumLikes(1);
                currentUser.getRatedMovies().set(i, updatedMovie);
                break;
            }
            /* Actualises the movie from the rated movie list (adds 1 like to it) */
        }
        for (int i = 0; i < currentUser.getAvailableMovies().size(); i++) {
            if (currentUser.getAvailableMovies().get(i).getName().equals(movieName)) {
                Movie updatedMovie = new Movie(currentUser.getAvailableMovies().get(i));
                updatedMovie.addNumLikes(1);
                currentUser.getAvailableMovies().set(i, updatedMovie);
                break;
            }
            /* Actualises the movie from the user's available movie list (adds 1 like to it) */
        }
        Movie updatedMovie = new Movie(Executable.getExe().getCurrentMovieList().get(0));
        updatedMovie.addNumLikes(1);
        Executable.getExe().getCurrentMovieList().set(0, updatedMovie);
        /* Actualises the movie from the current movie list (adds 1 like to it) */

        int movieIdx = Database.getContent().findCurrentMovieIdx();
        Database.getContent().getMoviesDB().set(movieIdx, updatedMovie);
        /* Actualises the movie from the movie database (adds 1 like to it) */
    }
}
