package pages.authenticated_pages;

import platform.Executable;
import platform.Movie;
import pages.Page;
import pages.unauthenticated_pages.UnauthenticatedHomepage;
import java.util.ArrayList;

/** The Upgrades Page */
public final class UpgradesPage extends Page {
    private static UpgradesPage page = null;

    private UpgradesPage() {
        super();
    }

    /** Singleton implementation */
    public static UpgradesPage getPage() {
        if (page == null) {
            page = new UpgradesPage();
        }
        return page;
    }

    @Override
    public ArrayList<String> validPagesToVisitFromHere() {
        ArrayList<String> validPages = new ArrayList<>();
        validPages.add(AuthenticatedHomepage.getPage().toString());
        validPages.add(MoviesPage.getPage().toString());
        validPages.add(UpgradesPage.getPage().toString());
        validPages.add(UnauthenticatedHomepage.getPage().toString());
        return validPages;
    }

    /** The method that sends you to the Upgrades Page */
    public void enterUpgradesPage() {
        Executable.getExe().setCurrentPage(UpgradesPage.getPage());
        ArrayList<Movie> emptyList = new ArrayList<>();
        Executable.getExe().setCurrentMovieList(emptyList);
    }

    @Override
    public String toString() {
        return "upgrades";
    }
}
