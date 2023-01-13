package pages.authenticated_pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import input.InputAction;
import platform.Executable;
import platform.Movie;
import pages.Page;
import pages.unauthenticated_pages.UnauthenticatedHomepage;
import java.util.ArrayList;
import static platform.Executable.displayOutputForError;
import static platform.Executable.displayOutputForSuccessfulAction;

/** The See Details Page */
public final class SeeDetailsPage extends Page {
    private static SeeDetailsPage page = null;

    private SeeDetailsPage() {
        super();
    }

    /** Singleton implementation */
    public static SeeDetailsPage getPage() {
        if (page == null) {
            page = new SeeDetailsPage();
        }
        return page;
    }

    @Override
    public ArrayList<String> validPagesToVisitFromHere() {
        ArrayList<String> validPages = new ArrayList<>();
        validPages.add(MoviesPage.getPage().toString());
        validPages.add(UpgradesPage.getPage().toString());
        validPages.add(SeeDetailsPage.getPage().toString());
        validPages.add(AuthenticatedHomepage.getPage().toString());
        validPages.add(UnauthenticatedHomepage.getPage().toString());
        return validPages;
    }

    /** The method that sends you to the See Details Page and displays the movie you picked */
    public void pickMovie(final InputAction inAct, final ObjectNode outputNode,
                          final ArrayNode outputArray) {
        Executable exe = Executable.getExe();

        if (!inAct.getType().equals("back")) {
            boolean checkIfMovieExists = false;
            for (int i = 0; i < exe.getCurrentMovieList().size(); i++) {
                if (exe.getCurrentMovieList().get(i).getName().equals(inAct.getMovie())) {
                    Movie auxMovie = new Movie(exe.getCurrentMovieList().get(i));
                    Executable.getExe().setCurrentPage(SeeDetailsPage.getPage());
                    exe.getCurrentMovieList().clear();
                    exe.getCurrentMovieList().add(auxMovie);
                    checkIfMovieExists = true;
                    displayOutputForSuccessfulAction(outputNode, outputArray);
                    break;
                }
            }
            if (!checkIfMovieExists) {
                displayOutputForError(outputNode, outputArray);
            }
        } else {
            Movie auxMovie = new Movie(inAct.getEntireMovie());
            Executable.getExe().setCurrentPage(SeeDetailsPage.getPage());
            exe.getCurrentMovieList().clear();
            exe.getCurrentMovieList().add(auxMovie);
            displayOutputForSuccessfulAction(outputNode, outputArray);
        }
    }

    @Override
    public String toString() {
        return "see details";
    }
}
