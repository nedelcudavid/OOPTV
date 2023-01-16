package actions.on_page_actions;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import platform.RegisteredUser;
import platform.Executable;
import pages.authenticated_pages.UpgradesPage;
import static platform.Executable.displayOutputForError;

/** This utility class executes the 'buy premium account' action */
public final class BuyPremiumAccountAction {
    private static final int PREMIUM_ACCOUNT_PRICE = 10;

    private BuyPremiumAccountAction() {
        throw new UnsupportedOperationException("This is a utility class & can't be instantiated");
    }

    /** This function verifies if the conditions for this action are met
     and adds an error/success node in the output array accordingly */
    static void buyPremiumAccount(final ObjectNode outputNode, final ArrayNode outputArray) {
        if (Executable.getExe().getCurrentPage().equals(UpgradesPage.getPage())) {
            int tokensCount = Executable.getExe().getCurrentUser().getTokensCount();
            RegisteredUser currentUser = Executable.getExe().getCurrentUser();

            if (!currentUser.getCredentials().getAccountType().equals("premium")
                    && tokensCount >= PREMIUM_ACCOUNT_PRICE) {
                currentUser.subTokensCount(PREMIUM_ACCOUNT_PRICE);
                currentUser.getCredentials().setAccountType("premium");
            } else {
                displayOutputForError(outputNode, outputArray);
            }
        } else {
            displayOutputForError(outputNode, outputArray);
        }
    }
}
