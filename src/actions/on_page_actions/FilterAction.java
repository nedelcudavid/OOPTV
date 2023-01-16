package actions.on_page_actions;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import input.InputAction;
import platform.RegisteredUser;
import platform.Executable;
import platform.Movie;
import pages.authenticated_pages.MoviesPage;
import java.util.ArrayList;
import java.util.Comparator;
import static platform.Executable.displayOutputForError;
import static platform.Executable.displayOutputForSuccessfulAction;

/** This utility class executes the 'filter' action */
public final class FilterAction {
    private FilterAction() {
        throw new UnsupportedOperationException("This is a utility class & can't be instantiated");
    }

    /** This function verifies if the conditions for this action are met
     and adds an error/success node in the output array accordingly */
    static void filter(final InputAction action, final ObjectNode outputNode,
                       final ArrayNode outputArray) {
        if (Executable.getExe().getCurrentPage().equals(MoviesPage.getPage())) {
            RegisteredUser currentUser = Executable.getExe().getCurrentUser();

            ArrayList<Movie> availableMovies = new ArrayList<>(currentUser.getAvailableMovies());
            Executable.getExe().setCurrentMovieList(availableMovies);
            sort(action);
            contains(action);
            displayOutputForSuccessfulAction(outputNode, outputArray);
        } else {
            displayOutputForError(outputNode, outputArray);
        }
    }

    /** This function sorts the movie list by rating and/or duration if is necessary */
    private static void sort(final InputAction action) {
        ArrayList<Movie> currentMovieList = Executable.getExe().getCurrentMovieList();
        if (action.getFilters().getSort() != null) {
            if (action.getFilters().getSort().getRating() != null) {
                if (action.getFilters().getSort().getRating().equals("decreasing")) {
                    currentMovieList.sort(Comparator.comparing(Movie::getRating).reversed());
                } else {
                    currentMovieList.sort(Comparator.comparing(Movie::getRating));
                }
            }
            if (action.getFilters().getSort().getDuration() != null) {
                if (action.getFilters().getSort().getDuration().equals("decreasing")) {
                    currentMovieList.sort(Comparator.comparing(Movie::getDuration).reversed());
                } else {
                    currentMovieList.sort(Comparator.comparing(Movie::getDuration));
                }
            }
        }
    }

    /** This function filters the movie list by actors and/or genre  if is necessary */
    private static void contains(final InputAction action) {
        ArrayList<Movie> currentMovieList = Executable.getExe().getCurrentMovieList();
        if (action.getFilters().getContains() != null) {
            if (action.getFilters().getContains().getActors() != null) {
                for (int i = 0; i < action.getFilters().getContains().getActors().size(); i++) {
                    String actor = action.getFilters().getContains().getActors().get(i);
                    currentMovieList.removeIf(movie -> (!(movie.getActors().contains(actor))));
                }
            }
            if (action.getFilters().getContains().getGenre() != null) {
                for (int i = 0; i < action.getFilters().getContains().getGenre().size(); i++) {
                    String genre = action.getFilters().getContains().getGenre().get(i);
                    currentMovieList.removeIf(movie -> (!(movie.getGenres().contains(genre))));
                }
            }
        }
    }
}
