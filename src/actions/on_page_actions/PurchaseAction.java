package actions.on_page_actions;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import platform.RegisteredUser;
import platform.Executable;
import platform.Movie;
import pages.authenticated_pages.SeeDetailsPage;
import static platform.Executable.displayOutputForError;
import static platform.Executable.displayOutputForSuccessfulAction;

/** This utility class executes the 'purchase' action */
public final class PurchaseAction {
    private PurchaseAction() {
        throw new UnsupportedOperationException("This is a utility class & can't be instantiated");
    }

    static void purchase(final ObjectNode outputNode, final ArrayNode outputArray) {
        RegisteredUser currentUser = Executable.getExe().getCurrentUser();

        if (Executable.getExe().getCurrentPage().equals(SeeDetailsPage.getPage())) {
            Movie currentMovie = Executable.getExe().getCurrentMovieList().get(0);

            if (!currentUser.getPurchasedMoviesNames().contains(currentMovie.getName())) {
                if (currentUser.getCredentials().getAccountType().equals("premium")) {
                    if (currentUser.getNumFreePremiumMovies() >= 1) {
                        currentUser.subNumFreePremiumMovies(1);
                        Movie purchasedMovie = new Movie(currentMovie);
                        currentUser.getPurchasedMovies().add(purchasedMovie);
                        currentUser.getPurchasedMoviesNames().add(purchasedMovie.getName());
                        displayOutputForSuccessfulAction(outputNode, outputArray);
                    } else {
                        tryToBuyMovieWithTokens(outputNode, outputArray, currentUser);
                    }
                } else {
                    tryToBuyMovieWithTokens(outputNode, outputArray, currentUser);
                }
            } else {
                displayOutputForError(outputNode, outputArray);
            }
        } else {
            displayOutputForError(outputNode, outputArray);
        }
    }
    /* This function verifies if the conditions for this action are met,
     if they are it adds the current movie to the current user's purchased
     movie list and adds an error/success node in the output array accordingly */

    private static void tryToBuyMovieWithTokens(final ObjectNode outputNode,
                                                final ArrayNode outputArray,
                                                final RegisteredUser currentUser) {
        if (currentUser.getTokensCount() >= 2) {
            currentUser.subTokensCount(2);
            Movie currentMovie = Executable.getExe().getCurrentMovieList().get(0);
            Movie purchasedMovie = new Movie(currentMovie);
            currentUser.getPurchasedMovies().add(purchasedMovie);
            currentUser.getPurchasedMoviesNames().add(purchasedMovie.getName());
            displayOutputForSuccessfulAction(outputNode, outputArray);
        } else {
            displayOutputForError(outputNode, outputArray);
        }
    }
    /* This function is created to avoid duplicated code and it verifies if the user
     can buy the movie with tokens and if he can, the movie is bought and the function
     adds an error/success node in the output array accordingly*/
}
