package platform.payment_system;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Setter;
import platform.Executable;
import platform.RegisteredUser;

import static platform.Executable.displayOutputForError;
import static platform.Executable.displayOutputForSuccessfulAction;

@Setter
public class PaymentSystem {
    private PaymentStrategy strategy;

    /** This function verifies if the conditions for this action are met,
     * if they are it buys and adds the current movie to the current user's purchased
     * movie list and adds an error/success node in the output array accordingly */
    public void processOrder(String accountType, ObjectNode outputNode, ArrayNode outputArray) {
        RegisteredUser currentUser = Executable.getExe().getCurrentUser();
        PaymentStrategy byTokens = new PaymentByTokens();

        if (accountType.equals("premium")) {
            if (currentUser.getNumFreePremiumMovies() >= 1) {
                strategy.pay();
                displayOutputForSuccessfulAction(outputNode, outputArray);
            } else {
                if (currentUser.getTokensCount() >= 2) {
                    setStrategy(byTokens);
                    strategy.pay();
                    displayOutputForSuccessfulAction(outputNode, outputArray);
                } else {
                    displayOutputForError(outputNode, outputArray);
                }
            }
        } else {
            if (currentUser.getTokensCount() >= 2) {
                setStrategy(byTokens);
                strategy.pay();
                displayOutputForSuccessfulAction(outputNode, outputArray);
            } else {
                displayOutputForError(outputNode, outputArray);
            }
        }
    }

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }
}
