package actions.on_page_actions;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import input.InputAction;
import input.InputCredentials;
import platform.RegisteredUser;
import platform.Database;
import platform.Executable;
import pages.unauthenticated_pages.RegisterPage;
import pages.unauthenticated_pages.UnauthenticatedHomepage;
import static platform.Executable.displayOutputForError;

/** This utility class executes the 'register' action */
public final class RegisterAction {
    private RegisterAction() {
        throw new UnsupportedOperationException("This is a utility class & can't be instantiated");
    }

    /** This function verifies if the credentials of the user match with the ones of any user
     from the database and registers the account only if the user doesn't exists, it also adds
     an error/success node in the output array accordingly */
    static void register(final InputAction action, final ObjectNode outputNode,
                         final ArrayNode outputArray) {
        if (Executable.getExe().getCurrentPage().equals(RegisterPage.getPage())) {
            boolean checkIfUserAlreadyExists = false;
            for (int i = 0; i < Database.getContent().getUsersDB().size(); i++) {
                InputCredentials user = Database.getContent().getUsersDB().get(i).getCredentials();

                if (user.getName().equals(action.getCredentials().getName())) {
                    checkIfUserAlreadyExists = true;
                    break;
                }
            }
            if (!checkIfUserAlreadyExists) {
                RegisteredUser newUser = new RegisteredUser(action.getCredentials());

                Database.getContent().getUsersDB().add(newUser);
                int last = Database.getContent().getUsersDB().size() - 1;
                LoginAction.enterAccount(outputNode, outputArray, last);

            } else {
                Executable.getExe().setCurrentPage(UnauthenticatedHomepage.getPage());
                displayOutputForError(outputNode, outputArray);
            }
        } else {
            displayOutputForError(outputNode, outputArray);
        }
    }
}
