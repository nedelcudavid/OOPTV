package actions.on_page_actions;

import platform.payment_system.PaymentByFreePremiumMovies;
import platform.payment_system.PaymentSystem;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import platform.RegisteredUser;
import platform.Executable;
import platform.Movie;
import pages.authenticated_pages.SeeDetailsPage;
import static platform.Executable.displayOutputForError;

/** This utility class executes the 'purchase' action */
public final class PurchaseAction {
    private PurchaseAction() {
        throw new UnsupportedOperationException("This is a utility class & can't be instantiated");
    }

    /** This function verifies if the conditions for
     * buying a movie are met and if so it places the order */
    static void purchase(final ObjectNode outputNode, final ArrayNode outputArray) {
        RegisteredUser currentUser = Executable.getExe().getCurrentUser();

        if (Executable.getExe().getCurrentPage().equals(SeeDetailsPage.getPage())) {
            Movie currentMovie = Executable.getExe().getCurrentMovieList().get(0);

            if (!currentUser.getPurchasedMoviesNames().contains(currentMovie.getName())) {
                PaymentSystem paymentSystem = new PaymentSystem();
                paymentSystem.setStrategy(new PaymentByFreePremiumMovies());
                paymentSystem.processOrder(currentUser.getCredentials().getAccountType(),
                        outputNode, outputArray);
            } else {
                displayOutputForError(outputNode, outputArray);
            }
        } else {
            displayOutputForError(outputNode, outputArray);
        }
    }
}
