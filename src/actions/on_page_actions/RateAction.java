package actions.on_page_actions;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import input.InputAction;
import platform.RegisteredUser;
import platform.Database;
import platform.Executable;
import platform.Movie;
import pages.authenticated_pages.SeeDetailsPage;

import static actions.on_page_actions.LoginAction.actualiseMovieLists;
import static platform.Executable.displayOutputForError;
import static platform.Executable.displayOutputForSuccessfulAction;

/** This utility class executes the 'rate' action */
public final class RateAction {
    private static final int MAX_RATING = 5;
    private static final int MIN_RATING = 1;
    private RateAction() {
        throw new UnsupportedOperationException("This is a utility class & can't be instantiated");
    }

    static void rate(final InputAction action, final ObjectNode outputNode,
                     final ArrayNode outputArray) {
        RegisteredUser currentUser = Executable.getExe().getCurrentUser();

        if (Executable.getExe().getCurrentPage().equals(SeeDetailsPage.getPage())) {
            String currentMovieName = Executable.getExe().getCurrentMovieList().get(0).getName();

            if (currentUser.getPurchasedMoviesNames().contains(currentMovieName)) {
                if (currentUser.getWatchedMoviesNames().contains(currentMovieName)) {
                        if (action.getRate() >= MIN_RATING && action.getRate() <= MAX_RATING) {
                            addRateEverywhere(action.getRate());
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
    /* This function verifies if the conditions for this action are met,
     if they are it adds a new rating to the current movie, adds it to
     the current user's rated movie list and adds an error/success node in
     the output array accordingly */

    private static void addRateEverywhere(final int newRating) {
        RegisteredUser currentUser = Executable.getExe().getCurrentUser();

        Movie ratedMovie = new Movie(Executable.getExe().getCurrentMovieList().get(0));
        ratedMovie.addUsersWhoRated(1);
        ratedMovie.addNumRating(1);
        ratedMovie.addRating(newRating);

        if (currentUser.getRatedMoviesNames().contains(ratedMovie.getName())) {
            ratedMovie.subNumRating(1);
            currentUser.getRatedMoviesNames().remove(ratedMovie.getName());
            int actualiseIdx = 0;
            for (int i = 0; i < currentUser.getRatedMovies().size(); i++) {
                if (currentUser.getRatedMovies().get(i).getName().equals(ratedMovie.getName())) {
                    actualiseIdx = i;
                }
            }
            currentUser.getRatedMovies().set(actualiseIdx, ratedMovie);
        } else {
            currentUser.getRatedMovies().add(ratedMovie);
            currentUser.getRatedMoviesNames().add(ratedMovie.getName());
            /* Adds a rate to the movie and adds it to current player's rated movie list */
        }

        Executable.getExe().getCurrentMovieList().set(0, ratedMovie);
        /* Actualises the movie from the current movie list (adds the new rating to it) */

        String movieName = Executable.getExe().getCurrentMovieList().get(0).getName();

        actualiseMovieLists(currentUser, movieName, ratedMovie);

        for (int i = 0; i < currentUser.getAvailableMovies().size(); i++) {
            if (currentUser.getAvailableMovies().get(i).getName().equals(movieName)) {
                currentUser.getAvailableMovies().set(i, ratedMovie);
                break;
            }
            /* Actualises the movie from the available movie list (adds the new rating to it) */
        }

        int movieIdx = Database.getContent().findCurrentMovieIdx();
        Database.getContent().getMoviesDB().set(movieIdx, ratedMovie);
        /* Actualises the movie from the movie database (adds the new rating to it) */
    }
    /* This function executes the rate action itself and
     adds the new rate everywhere the movie is found */
}
