package actions.on_page_actions;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import input.InputAction;
import static actions.on_page_actions.BuyPremiumAccountAction.buyPremiumAccount;
import static actions.on_page_actions.BuyTokensAction.buyTokens;
import static actions.on_page_actions.FilterAction.filter;
import static actions.on_page_actions.LikeAction.like;
import static actions.on_page_actions.LoginAction.login;
import static actions.on_page_actions.PurchaseAction.purchase;
import static actions.on_page_actions.RateAction.rate;
import static actions.on_page_actions.RegisterAction.register;
import static actions.on_page_actions.SearchAction.search;
import static actions.on_page_actions.SubscribeAction.subscribe;
import static actions.on_page_actions.WatchAction.watch;

/** This singleton class holds all the 'on page' actions */
public final class OnPageActions {
    private static OnPageActions action = null;

    private OnPageActions() {
    }

    /** Singleton implementation */
    public static OnPageActions getAction() {
        if (action == null) {
            action = new OnPageActions();
        }
        return action;
    }


    /** This function executes any 'on page' action it is called if it exists */
    public void execute(final InputAction inputAction, final ObjectNode outputNode,
                        final ArrayNode outputArray) {
        switch (inputAction.getFeature()) {
            case "login" -> login(inputAction, outputNode, outputArray);
            case "register" -> register(inputAction, outputNode, outputArray);
            case "search" -> search(inputAction, outputNode, outputArray);
            case "filter" -> filter(inputAction, outputNode, outputArray);
            case "buy tokens" -> buyTokens(inputAction, outputNode, outputArray);
            case "buy premium account" -> buyPremiumAccount(outputNode, outputArray);
            case "purchase" -> purchase(outputNode, outputArray);
            case "watch" -> watch(outputNode, outputArray);
            case "like" -> like(outputNode, outputArray);
            case "rate" -> rate(inputAction, outputNode, outputArray);
            case "subscribe" -> subscribe(inputAction, outputNode, outputArray);
            default -> System.out.println("There are actions to implement remaining!");
        }
    }
}
