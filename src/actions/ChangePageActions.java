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
import platform.Movie;

import static platform.Executable.displayOutputForError;
import static platform.Executable.displayOutputForSuccessfulAction;

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

    /** This function verifies if the user can navigate to the specified page
     and go there if is permitted, otherwise adds the error node in the output array */
    public void execute(final InputAction inAct, final ObjectNode outNode,
                        final ArrayNode outArray) {
        Page currentPage = Executable.getExe().getCurrentPage();

        if (currentPage.validPagesToVisitFromHere().contains(inAct.getPage())) {

            if (Executable.getExe().getCurrentUser() != null && !inAct.getPage().equals("logout")) {
                if (!inAct.getType().equals("back")) {
                    if (currentPage.equals(SeeDetailsPage.getPage())) {
                        Executable.getExe().getCurrentUser().getVisitedPages().push(currentPage);
                        Movie auxMovie = new Movie(Executable.getExe().getCurrentMovieList().get(0));
                        Executable.getExe().getCurrentUser().getVisitedMovies().push(auxMovie);
                    } else {
                        if (inAct.getPage().equals("see details")) {
                            Executable exe = Executable.getExe();
                            for (int i = 0; i < exe.getCurrentMovieList().size(); i++) {
                                if (exe.getCurrentMovieList().get(i).getName().equals(inAct.getMovie())) {
                                    Executable.getExe().getCurrentUser().getVisitedPages().push(currentPage);
                                    break;
                                }
                            }
                        } else {
                            Executable.getExe().getCurrentUser().getVisitedPages().push(currentPage);
                        }
                    }
                }
            }

            switch (inAct.getPage()) {
                case "see details" -> SeeDetailsPage.getPage().pickMovie(inAct, outNode, outArray);
                case "movies" -> MoviesPage.getPage().showMovies(outNode, outArray);
                case "upgrades" -> UpgradesPage.getPage().enterUpgradesPage();
                case "authenticated homepage" -> AuthenticatedHomepage.getPage().enterAuthenticatedHomepage();
                case "logout" -> UnauthenticatedHomepage.getPage().logout();
                case "login" -> LoginPage.getPage().enterLoginPage();
                case "register" -> RegisterPage.getPage().enterRegisterPage();
                default -> System.out.println("Error at getting action's page!");
            }
        } else {
            displayOutputForError(outNode, outArray);
        }
    }
}

