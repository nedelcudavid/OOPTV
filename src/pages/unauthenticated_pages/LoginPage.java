package pages.unauthenticated_pages;

import platform.Executable;
import pages.Page;
import java.util.ArrayList;

/** The Login Page */
public final class LoginPage extends Page {
    private static LoginPage page = null;

    private LoginPage() {
    }

    /** Singleton implementation */
    public static LoginPage getPage() {
        if (page == null) {
            page = new LoginPage();
        }
        return page;
    }

    @Override
    public ArrayList<String> validPagesToVisitFromHere() {
        ArrayList<String> validPages = new ArrayList<>();
        validPages.add(RegisterPage.getPage().toString());
        return validPages;
    }

    /** The method that sends you to the Login Page */
    public void enterLoginPage() {
        Executable.getExe().setCurrentPage(LoginPage.getPage());
    }
    @Override
    public String toString() {
        return "login";
    }
}
