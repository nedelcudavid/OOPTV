package pages.unauthenticated_pages;

import platform.Executable;
import pages.Page;
import java.util.ArrayList;

/** The Register Page */
public final class RegisterPage extends Page {
    private static RegisterPage page = null;

    private RegisterPage() {
    }

    /** Singleton implementation */
    public static RegisterPage getPage() {
        if (page == null) {
            page = new RegisterPage();
        }
        return page;
    }

    @Override
    public ArrayList<String> validPagesToVisitFromHere() {
        ArrayList<String> validPages = new ArrayList<>();
        validPages.add(LoginPage.getPage().toString());
        return validPages;
    }

    /** The method that sends you to the Register Page */
    public void enterRegisterPage() {
        Executable.getExe().setCurrentPage(RegisterPage.getPage());
    }

    @Override
    public String toString() {
        return "register";
    }
}
