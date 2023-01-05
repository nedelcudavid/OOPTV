package actions.on_page_actions;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import input.InputAction;
import platform.RegisteredUser;
import platform.Executable;
import platform.Movie;
import pages.authenticated_pages.MoviesPage;
import java.util.ArrayList;
import static platform.Executable.displayOutputForError;
import static platform.Executable.displayOutputForSuccessfulAction;

/** This utility class executes the 'search' action */
public final class SearchAction {
    private SearchAction() {
        throw new UnsupportedOperationException("This is a utility class & can't be instantiated");
    }

    static void search(final InputAction action, final ObjectNode outputNode,
                       final ArrayNode outputArray) {
        if (Executable.getExe().getCurrentPage().equals(MoviesPage.getPage())) {
            RegisteredUser currentUser = Executable.getExe().getCurrentUser();

            ArrayList<Movie> availableMovies = new ArrayList<>(currentUser.getAvailableMovies());
            Executable.getExe().setCurrentMovieList(availableMovies);

            ArrayList<Movie> currentMovieList = Executable.getExe().getCurrentMovieList();
            String startsWith = action.getStartsWith();

            currentMovieList.removeIf(movie -> (!movie.getName().startsWith(startsWith)));
            displayOutputForSuccessfulAction(outputNode, outputArray);
        } else {
            displayOutputForError(outputNode, outputArray);
        }
    }
     /* This function verifies if the names of the movies from the current movie list
     start with a specific string and keeps only the ones who do, then it adds an error/success
     node in the output array accordingly */
}
