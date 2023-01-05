package platform;

import actions.ChangePageActions;
import actions.on_page_actions.OnPageActions;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import input.InputAction;
import pages.Page;
import pages.unauthenticated_pages.UnauthenticatedHomepage;
import java.util.ArrayList;

/** This singleton class represents the execution of the program and holds
 * the current page, movie list and user in real time on the streaming platform */
public final class Executable {
    private Page currentPage;
    private ArrayList<Movie> currentMovieList;
    private RegisteredUser currentUser;
    private static Executable exe = null;

    private Executable() {
        currentPage = UnauthenticatedHomepage.getPage();
        currentMovieList = new ArrayList<>();
        currentUser = null;
    }

    /** Singleton implementation */
    public static Executable getExe() {
        if (exe == null) {
            exe = new Executable();
        }
        return exe;
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(final Page currentPage) {
        this.currentPage = currentPage;
    }

    public ArrayList<Movie> getCurrentMovieList() {
        return currentMovieList;
    }

    public void setCurrentMovieList(final ArrayList<Movie> currentMovieList) {
        this.currentMovieList = currentMovieList;
    }

    public RegisteredUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(final RegisteredUser currentUser) {
        this.currentUser = currentUser;
    }

    /** This method puts the streaming platform in service :)
     * It starts the execution of the program */
    public void run(final ArrayNode outputArray) {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<InputAction> actions = Database.getContent().getActionsDB();

        for (int cnt = 0; cnt < Database.getContent().getActionsDB().size(); cnt++) {
            ObjectNode outputNode = objectMapper.createObjectNode();

            switch (actions.get(cnt).getType()) {
                case "change page" -> ChangePageActions.getAction().execute(actions.get(cnt),
                        outputNode, outputArray);
                case "on page" -> OnPageActions.getAction().execute(actions.get(cnt), outputNode,
                        outputArray);
                default -> System.out.println("Error at getting action type!");
            }
        }
        UnauthenticatedHomepage.getPage().logout();
        Database.getContent().free();
    }

    /** This method fills an object node with data for a successful
     * action accordingly and adds it to the output array */
    public static void displayOutputForSuccessfulAction(final ObjectNode outputNode,
                                                        final ArrayNode outputArray) {
        outputNode.put("error", (String) null);

        ArrayList<Movie> deepCopyCurrentMovieList = new ArrayList<>(exe.getCurrentMovieList());
        outputNode.putPOJO("currentMoviesList", deepCopyCurrentMovieList);

        RegisteredUser deepCopyCurrentUser = new RegisteredUser(exe.getCurrentUser());
        outputNode.putPOJO("currentUser", deepCopyCurrentUser);
        outputArray.add(outputNode);

    }

    /** This method fills an object node with data for an error
     * accordingly and adds it to the output array */
    public static void displayOutputForError(final ObjectNode outputNode,
                                             final ArrayNode outputArray) {
        outputNode.put("error", "Error");
        ArrayList<Movie> emptyList = new ArrayList<>();
        outputNode.putPOJO("currentMoviesList", emptyList);
        outputNode.putPOJO("currentUser", null);
        outputArray.add(outputNode);
    }
}
