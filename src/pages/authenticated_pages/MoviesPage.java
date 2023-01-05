package pages.authenticated_pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import platform.Executable;
import platform.Movie;
import platform.RegisteredUser;
import pages.Page;
import pages.unauthenticated_pages.UnauthenticatedHomepage;
import java.util.ArrayList;
import static platform.Executable.displayOutputForSuccessfulAction;

/** The Movies Page */
public final class MoviesPage extends Page {
    private static MoviesPage page = null;

    private MoviesPage() {
        super();
    }

    /** Singleton implementation */
    public static MoviesPage getPage() {
        if (page == null) {
            page = new MoviesPage();
        }
        return page;
    }
    @Override
    public ArrayList<String> validPagesToVisitFromHere() {
        ArrayList<String> validPages = new ArrayList<>();
        validPages.add(AuthenticatedHomepage.getPage().toString());
        validPages.add(SeeDetailsPage.getPage().toString());
        validPages.add(MoviesPage.getPage().toString());
        validPages.add(UpgradesPage.getPage().toString());
        validPages.add(UnauthenticatedHomepage.getPage().toString());
        return validPages;
    }

    /** The method that sends you to the Movies Page and displays all available movies */
    public void showMovies(final ObjectNode outputNode, final ArrayNode outputArray) {
        RegisteredUser currentUser = Executable.getExe().getCurrentUser();
        Executable.getExe().setCurrentPage(MoviesPage.getPage());
        ArrayList<Movie> availableMovies = new ArrayList<>(currentUser.getAvailableMovies());
        Executable.getExe().setCurrentMovieList(availableMovies);
        displayOutputForSuccessfulAction(outputNode, outputArray);
    }

    @Override
    public String toString() {
        return "movies";
    }
}
