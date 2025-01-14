package actions.on_page_actions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import input.InputAction;
import pages.authenticated_pages.SeeDetailsPage;
import platform.Database;
import platform.Executable;
import platform.Movie;
import platform.RegisteredUser;
import platform.notifications_system.Notification;
import platform.notifications_system.NotificationsFactory;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.Comparator;

import static platform.Executable.displayOutputForError;

/** This utility class executes the 'search' action */
public final class SubscribeAction {

    private SubscribeAction() {
        throw new UnsupportedOperationException("This is a utility class & can't be instantiated");
    }

    /** This function verifies if the conditions for this action are met,
     if they are it adds one of the current movie's genres to the current user's
     subscribedGenres list and adds an error/success node in the output array in necessary */
    static void subscribe(final InputAction action, final ObjectNode outputNode,
                          final ArrayNode outputArray) {
        if (Executable.getExe().getCurrentPage().equals(SeeDetailsPage.getPage())) {
            if (Executable.getExe().getCurrentUser() != null) {
                RegisteredUser currentUser = Executable.getExe().getCurrentUser();

                if (Executable.getExe().getCurrentMovieList().get(0).getGenres()
                        .contains(action.getSubscribedGenre())) {
                    if (!currentUser.getSubscribedGenres().contains(action.getSubscribedGenre())) {
                        currentUser.getSubscribedGenres().add(action.getSubscribedGenre());
                        int userIdx = Database.getContent().findCurrentUserIdx();
                        Database.getContent().getUsersDB().get(userIdx)
                                .getSubscribedGenres().add(action.getSubscribedGenre());
                    } else {
                        displayOutputForError(outputNode, outputArray);
                    }
                } else {
                    displayOutputForError(outputNode, outputArray);
                }
            } else {
                displayOutputForError(outputNode, outputArray);
            }
        } else {
            displayOutputForError(outputNode, outputArray);
        }
    }

    /** This function verifies if the conditions for this action are met,
     if they are it adds a recommendation notification to the current user's
     notifications list */
    public static void giveRecommendation(final ArrayNode outputArray) {
        if (Executable.getExe().getCurrentUser()
                .getCredentials().getAccountType().equals("premium")) {
            ArrayList<String> allGenres = new ArrayList<>();
            for (Movie movie : Database.getContent().getMoviesDB()) {
                for (String genre : movie.getGenres()) {
                    if (!allGenres.contains(genre)) {
                        allGenres.add(genre);
                    }
                }
            }
            Collections.sort(allGenres);

            HashMap<String, Integer> topGenres = new HashMap<>();
            RegisteredUser currentUser = Executable.getExe().getCurrentUser();
            for (String genre : allGenres) {
                int genreLikes = 0;
                for (Movie likedMovie : currentUser.getLikedMovies()) {
                    if (likedMovie.getGenres().contains(genre)) {
                        genreLikes++;
                    }
                }
                if (genreLikes != 0) {
                    topGenres.put(genre, genreLikes);
                }
            }

            if (topGenres.size() > 0) {
                Set<Map.Entry<String, Integer>> entries = topGenres.entrySet();
                Comparator<Map.Entry<String, Integer>> valueComparator = (o1, o2) -> {
                    Integer v1 = o1.getValue();
                    Integer v2 = o2.getValue();
                    return v1.compareTo(v2);
                };
                List<Map.Entry<String, Integer>> listOfEntries = new ArrayList<>(entries);
                listOfEntries.sort(valueComparator);
                ArrayList<String> genresSortedByValue = new ArrayList<>(listOfEntries.size());
                for (int i = listOfEntries.size() - 1; i >= 0; i--) {
                    genresSortedByValue.add(listOfEntries.get(i).getKey());
                }

                String recommendationName = "No recommendation";

                for (String genre : genresSortedByValue) {
                    ArrayList<Movie> availableMovies =
                            new ArrayList<>(Database.getContent().getMoviesDB());
                    String userCountry =
                            Executable.getExe().getCurrentUser().getCredentials().getCountry();
                    availableMovies.removeIf(movie ->
                            (movie.getCountriesBanned().contains(userCountry)));

                    ArrayList<Movie> moviesSortedByLikes = new ArrayList<>();
                    while (availableMovies.size() > 0) {
                        Movie mostLikedMovie = null;
                        int mostLikes = -1;
                        int removeIdx = 0;
                        for (int i = 0; i < availableMovies.size(); i++) {
                            if (availableMovies.get(i).getGenres().contains(genre)
                                    && availableMovies.get(i).getNumLikes() > mostLikes) {
                                mostLikedMovie = availableMovies.get(i);
                                mostLikes = availableMovies.get(i).getNumLikes();
                                removeIdx = i;
                            }
                        }
                        moviesSortedByLikes.add(mostLikedMovie);
                        availableMovies.remove(removeIdx);
                    }

                    for (Movie movie : moviesSortedByLikes) {
                        if (!currentUser.getWatchedMoviesNames().contains(movie.getName())) {
                            recommendationName = movie.getName();
                            break;
                        }
                    }

                    if (!recommendationName.equals("No recommendation")) {
                        break;
                    }
                }
                addRecommendationNotification(outputArray, currentUser, recommendationName);
            } else {
                addRecommendationNotification(outputArray, currentUser, "No recommendation");
            }
        }
    }

    /** This function adds a recommendation notification to the user's notifications list */
    private static void addRecommendationNotification(final ArrayNode outputArray,
                                                      final RegisteredUser currentUser,
                                                      final String message) {
        NotificationsFactory notificationsFactory = new NotificationsFactory();
        Notification notificationToAdd =
                notificationsFactory.createNotification("Recommendation", message);

        notificationToAdd.addNotificationToUser(currentUser);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode outputNode = objectMapper.createObjectNode();
        outputNode.put("error", (String) null);
        outputNode.put("currentMoviesList", (String) null);
        RegisteredUser deepCopyCurrentUser = new RegisteredUser(currentUser);
        outputNode.putPOJO("currentUser", deepCopyCurrentUser);
        outputArray.add(outputNode);
    }
}
