package actions;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import input.InputAction;
import pages.Page;
import pages.authenticated_pages.SeeDetailsPage;
import platform.Executable;
import platform.Movie;
import platform.RegisteredUser;

import static platform.Executable.displayOutputForError;

/** This utility class executes the 'back' action */
public class BackAction {

    private BackAction() {
        throw new UnsupportedOperationException("This is a utility class & can't be instantiated");
    }

    /** This function provides the 'back' functionality and allows
     *  the user to go back one page if the conditions are met */
    public static void back(final ObjectNode outputNode, final ArrayNode outputArray) {
        if (Executable.getExe().getCurrentUser() != null) {
            RegisteredUser currentUser = Executable.getExe().getCurrentUser();

            if (currentUser.getVisitedPages().size() != 0) {
                Page lastPage = currentUser.getVisitedPages().pop();
                if (lastPage.equals(SeeDetailsPage.getPage())) {
                    Movie lastMovie = currentUser.getVisitedMovies().pop();
                    InputAction auxAction = new InputAction("back", lastPage.toString(), lastMovie);
                    ChangePageActions.getAction().execute(auxAction, outputNode, outputArray);
                } else {
                    InputAction auxAction = new InputAction("back", lastPage.toString());
                    ChangePageActions.getAction().execute(auxAction, outputNode, outputArray);
                }
            } else {
                displayOutputForError(outputNode, outputArray);
            }
        } else {
            displayOutputForError(outputNode, outputArray);
        }
    }
}
