package pages.authenticated_pages;

import platform.Executable;
import pages.Page;
import pages.unauthenticated_pages.UnauthenticatedHomepage;
import java.util.ArrayList;

/** The AuthenticatedHomepage */
public final class AuthenticatedHomepage extends Page {
    private static AuthenticatedHomepage page = null;

    private AuthenticatedHomepage() {
    }

    /** Singleton implementation */
    public static AuthenticatedHomepage getPage() {
        if (page == null) {
            page = new AuthenticatedHomepage();
        }
        return page;
    }

    @Override
    public ArrayList<String> validPagesToVisitFromHere() {
        ArrayList<String> validPages = new ArrayList<>();
        validPages.add(MoviesPage.getPage().toString());
        validPages.add(UpgradesPage.getPage().toString());
        validPages.add(AuthenticatedHomepage.getPage().toString());
        validPages.add(UnauthenticatedHomepage.getPage().toString());
        return validPages;
    }

    /** The method that sends you to the Authenticated Homepage */
    public void enterAuthenticatedHomepage() {
        Executable.getExe().setCurrentPage(AuthenticatedHomepage.getPage());
    }

    @Override
    public String toString() {
        return "authenticated homepage";
    }
}
