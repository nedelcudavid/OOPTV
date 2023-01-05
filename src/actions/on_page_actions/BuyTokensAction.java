package actions.on_page_actions;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import input.InputAction;
import platform.RegisteredUser;
import platform.Executable;
import pages.authenticated_pages.UpgradesPage;
import static platform.Executable.displayOutputForError;

/** This utility class executes the 'buy tokens' action */
public final class BuyTokensAction {
    private BuyTokensAction() {
        throw new UnsupportedOperationException("This is a utility class & can't be instantiated");
    }

    static void buyTokens(final InputAction action, final ObjectNode outputNode,
                          final ArrayNode outputArray) {
        if (Executable.getExe().getCurrentPage().equals(UpgradesPage.getPage())) {
            RegisteredUser currentUser = Executable.getExe().getCurrentUser();
            int balance = Integer.parseInt(currentUser.getCredentials().getBalance());
            int count = Integer.parseInt(action.getCount());

            if (balance >= count) {
                currentUser.addTokensCount(count);
                currentUser.getCredentials().subBalance(count);
            } else {
                displayOutputForError(outputNode, outputArray);
            }
        } else {
            displayOutputForError(outputNode, outputArray);
        }
    }
    /* This function verifies if the conditions for this action are met
     and adds an error/success node in the output array accordingly */
}
