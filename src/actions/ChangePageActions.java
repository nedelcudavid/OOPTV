package actions;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import input.InputAction;
import platform.Executable;
import pages.Page;
import pages.authenticated_pages.AuthenticatedHomepage;
import pages.authenticated_pages.MoviesPage;
import pages.authenticated_pages.SeeDetailsPage;
import pages.authenticated_pages.UpgradesPage;
import pages.unauthenticated_pages.LoginPage;
import pages.unauthenticated_pages.RegisterPage;
import pages.unauthenticated_pages.UnauthenticatedHomepage;
import static platform.Executable.displayOutputForError;

/** This singleton class holds all the 'change page' actions */
public final class ChangePageActions {
    private static ChangePageActions action = null;

    private ChangePageActions() {
    }

    /** Singleton implementation */
    public static ChangePageActions getAction() {
        if (action == null) {
            action = new ChangePageActions();
        }
        return action;
    }

    /** This function executes any 'change page' action */
    public void execute(final InputAction inAct, final ObjectNode outNode,
                        final ArrayNode outArray) {
        Page currentPage = Executable.getExe().getCurrentPage();

        if (currentPage.validPagesToVisitFromHere().contains(inAct.getPage())) {
            switch (inAct.getPage()) {
                case "see details" -> SeeDetailsPage.getPage().pickMovie(inAct, outNode, outArray);
                case "movies" -> MoviesPage.getPage().showMovies(outNode, outArray);
                case "upgrades" -> UpgradesPage.getPage().enterUpgradesPage();
                case "home" -> AuthenticatedHomepage.getPage().enterAuthenticatedHomepage();
                case "logout" -> UnauthenticatedHomepage.getPage().logout();
                case "login" -> LoginPage.getPage().enterLoginPage();
                case "register" -> RegisterPage.getPage().enterRegisterPage();
                default -> System.out.println("Error at getting action's page!");
            }
        } else {
            displayOutputForError(outNode, outArray);
        }
    }
    /* This function verifies if the user can navigate to the specified page
     and go there if is permitted, otherwise adds the error node in the output array */
}

