package actions.on_page_actions;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import input.InputAction;
import input.InputCredentials;
import platform.RegisteredUser;
import platform.Database;
import platform.Executable;
import platform.Movie;
import pages.authenticated_pages.AuthenticatedHomepage;
import pages.unauthenticated_pages.LoginPage;
import pages.unauthenticated_pages.UnauthenticatedHomepage;
import java.util.ArrayList;
import static platform.Executable.displayOutputForError;
import static platform.Executable.displayOutputForSuccessfulAction;

/** This utility class executes the 'login' action */
public final class LoginAction {
    private LoginAction() {
        throw new UnsupportedOperationException("This is a utility class & can't be instantiated");
    }

    static void login(final InputAction action, final ObjectNode outputNode,
                      final ArrayNode outputArray) {
        if (Executable.getExe().getCurrentPage().equals(LoginPage.getPage())) {
            boolean checkIfUserExists = false;
            for (int i = 0; i < Database.getContent().getUsersDB().size(); i++) {
                InputCredentials user = Database.getContent().getUsersDB().get(i).getCredentials();

                if (user.getName().equals(action.getCredentials().getName())
                        && user.getPassword().equals(action.getCredentials().getPassword())) {
                    checkIfUserExists = true;
                    enterAccount(outputNode, outputArray, i);
                    break;
                }
            }
            if (!checkIfUserExists) {
                Executable.getExe().setCurrentPage(UnauthenticatedHomepage.getPage());
                displayOutputForError(outputNode, outputArray);
            }
        } else {
            displayOutputForError(outputNode, outputArray);
        }
    }
    /* This function verifies if the credentials of the user match with the ones of any user
    from the database and enters the account only if the user exists, it also adds
    an error/success node in the output array accordingly */

    static void actualiseInfo(final RegisteredUser currentUser) {
        for (int idx = 0; idx < Database.getContent().getMoviesDB().size(); idx++) {
            String movieName = Database.getContent().getMoviesDB().get(idx).getName();
            Movie actualisedMovie = new Movie(Database.getContent().getMoviesDB().get(idx));

            actualiseMovieLists(currentUser, movieName, actualisedMovie);

            for (int i = 0; i < currentUser.getRatedMovies().size(); i++) {
                if (currentUser.getRatedMovies().get(i).getName().equals(movieName)) {
                    currentUser.getRatedMovies().set(i, actualisedMovie);
                    break;
                }
                /* Actualises the movie from the rated movie list with info from database*/
            }
        }
    }

    static void actualiseMovieLists(RegisteredUser currentUser, String movieName, Movie actualisedMovie) {
        for (int i = 0; i < currentUser.getPurchasedMovies().size(); i++) {
            if (currentUser.getPurchasedMovies().get(i).getName().equals(movieName)) {
                currentUser.getPurchasedMovies().set(i, actualisedMovie);
                break;
            }
            /* Actualises the movies from the purchased movie list with info from database*/
        }
        for (int i = 0; i < currentUser.getWatchedMovies().size(); i++) {
            if (currentUser.getWatchedMovies().get(i).getName().equals(movieName)) {
                currentUser.getWatchedMovies().set(i, actualisedMovie);
                break;
            }
            /* Actualises the movie from the watched movie list with info from database*/
        }
        for (int i = 0; i < currentUser.getLikedMovies().size(); i++) {
            if (currentUser.getLikedMovies().get(i).getName().equals(movieName)) {
                currentUser.getLikedMovies().set(i, actualisedMovie);
                break;
            }
            /* Actualises the movie from the rated movie list with info from database*/
        }
    }
    /* Function that actualises the movies from the current user's list with info from database*/

    static void enterAccount(final ObjectNode outNode, final ArrayNode outArray, final int i) {
        Executable.getExe().setCurrentPage(AuthenticatedHomepage.getPage());

        RegisteredUser user = new RegisteredUser(Database.getContent().getUsersDB().get(i));
        Executable.getExe().setCurrentUser(user);
        actualiseInfo(Executable.getExe().getCurrentUser());

        ArrayList<Movie> availableMovies = new ArrayList<>(Database.getContent().getMoviesDB());
        String userCountry = Executable.getExe().getCurrentUser().getCredentials().getCountry();
        availableMovies.removeIf(movie -> (movie.getCountriesBanned().contains(userCountry)));
        Executable.getExe().getCurrentUser().setAvailableMovies(availableMovies);

        displayOutputForSuccessfulAction(outNode, outArray);
    }
    /* This function represents the action of entering an account
    Here we set the current user, get the available movie list for him depending on his country and
     actualise the movies he has in his lists (purchased, watched, liked, rated) with the info from
     the database (we do this because if other user liked or rated a movie, the current user should
     have his lists actualised)
     */
}
