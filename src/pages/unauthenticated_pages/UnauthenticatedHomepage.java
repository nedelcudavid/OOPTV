package pages.unauthenticated_pages;

import platform.RegisteredUser;
import platform.Database;
import platform.Executable;
import pages.Page;
import java.util.ArrayList;

/** The Unauthenticated Homepage Page */
public final class UnauthenticatedHomepage extends Page {
    private static UnauthenticatedHomepage page = null;

    private UnauthenticatedHomepage() {
    }

    /** Singleton implementation */
    public static UnauthenticatedHomepage getPage() {
        if (page == null) {
            page = new UnauthenticatedHomepage();
        }
        return page;
    }

    @Override
    public ArrayList<String> validPagesToVisitFromHere() {
        ArrayList<String> validPages = new ArrayList<>();
        validPages.add(LoginPage.getPage().toString());
        validPages.add(RegisterPage.getPage().toString());
        return validPages;
    }

    /** The method that logout you and sends you to the Unauthenticated Homepage */
    public void logout() {
        if (Executable.getExe().getCurrentUser() != null) {
            RegisteredUser updatedUser = new RegisteredUser(Executable.getExe().getCurrentUser());
            int userIdx = Database.getContent().findCurrentUserIdx();
            Database.getContent().getUsersDB().set(userIdx, updatedUser);
        }
        Executable.getExe().setCurrentUser(null);
        Executable.getExe().getCurrentMovieList().clear();
        Executable.getExe().setCurrentPage(UnauthenticatedHomepage.getPage());
    }

    @Override
    public String toString() {
        return "logout";
    }
}
