package actions.on_page_actions;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import platform.RegisteredUser;
import platform.Executable;
import platform.Movie;
import pages.authenticated_pages.SeeDetailsPage;
import static platform.Executable.displayOutputForError;
import static platform.Executable.displayOutputForSuccessfulAction;

/** This utility class executes the 'watch' action */
public final class WatchAction {
    private WatchAction() {
        throw new UnsupportedOperationException("This is a utility class & can't be instantiated");
    }

    static void watch(final ObjectNode outputNode, final ArrayNode outputArray) {
        RegisteredUser currentUser = Executable.getExe().getCurrentUser();

        if (Executable.getExe().getCurrentPage().equals(SeeDetailsPage.getPage())) {
            Movie currentMovie = Executable.getExe().getCurrentMovieList().get(0);

            if (currentUser.getPurchasedMoviesNames().contains(currentMovie.getName())) {
                if (!currentUser.getWatchedMoviesNames().contains(currentMovie.getName())) {
                    Movie watchedMovie = new Movie(currentMovie);
                    currentUser.getWatchedMovies().add(watchedMovie);
                    currentUser.getWatchedMoviesNames().add(watchedMovie.getName());
                    displayOutputForSuccessfulAction(outputNode, outputArray);
                } else {
                    displayOutputForSuccessfulAction(outputNode, outputArray);
                }
            } else {
                displayOutputForError(outputNode, outputArray);
            }
        } else {
            displayOutputForError(outputNode, outputArray);
        }
    }
    /* This function verifies if the conditions for this action are met,
     if they are it adds the current movie to the current user's watched
     movie list and adds an error/success node in the output array accordingly */
}
