package platform;

import com.fasterxml.jackson.annotation.JsonIgnore;
import input.InputCredentials;
import input.InputUser;
import pages.Page;
import platform.notifications_system.Notification;

import java.util.ArrayList;
import java.util.Stack;

/** This class represents the registered user's characteristics */
public final class RegisteredUser extends InputUser {
    private int tokensCount;
    private int numFreePremiumMovies;
    private ArrayList<Movie> purchasedMovies;
    @JsonIgnore
    private final ArrayList<String> purchasedMoviesNames;
    private ArrayList<Movie> watchedMovies;
    @JsonIgnore
    private final ArrayList<String> watchedMoviesNames;
    private ArrayList<Movie> likedMovies;
    @JsonIgnore
    private final ArrayList<String> likedMoviesNames;
    private ArrayList<Movie> ratedMovies;
    @JsonIgnore
    private final ArrayList<String> ratedMoviesNames;
    @JsonIgnore
    private ArrayList<Movie> availableMovies;
    private ArrayList<Notification> notifications;
    @JsonIgnore
    private final ArrayList<String> subscribedGenres;
    @JsonIgnore
    private final Stack<Page> visitedPages;
    @JsonIgnore
    private final Stack<Movie> visitedMovies;
    private static final int NUM_FREE_PREMIUM_MOVIES = 15;

    public RegisteredUser(final InputUser inputUser) {
        setCredentials(new InputCredentials(inputUser.getCredentials()));
        tokensCount = 0;
        numFreePremiumMovies = NUM_FREE_PREMIUM_MOVIES;
        purchasedMovies = new ArrayList<>();
        watchedMovies = new ArrayList<>();
        likedMovies = new ArrayList<>();
        ratedMovies = new ArrayList<>();
        purchasedMoviesNames = new ArrayList<>();
        watchedMoviesNames = new ArrayList<>();
        likedMoviesNames = new ArrayList<>();
        ratedMoviesNames = new ArrayList<>();
        availableMovies = new ArrayList<>();
        notifications = new ArrayList<>();
        subscribedGenres = new ArrayList<>();
        visitedPages = new Stack<>();
        visitedMovies = new Stack<>();
    }

    public RegisteredUser(final RegisteredUser copyUser) {
        setCredentials(new InputCredentials(copyUser.getCredentials()));
        tokensCount = copyUser.getTokensCount();
        numFreePremiumMovies = copyUser.getNumFreePremiumMovies();
        purchasedMovies = new ArrayList<>(copyUser.getPurchasedMovies());
        watchedMovies = new ArrayList<>(copyUser.getWatchedMovies());
        likedMovies = new ArrayList<>(copyUser.getLikedMovies());
        ratedMovies = new ArrayList<>(copyUser.getRatedMovies());
        purchasedMoviesNames = new ArrayList<>(copyUser.getPurchasedMoviesNames());
        watchedMoviesNames = new ArrayList<>(copyUser.getWatchedMoviesNames());
        likedMoviesNames = new ArrayList<>(copyUser.getLikedMoviesNames());
        ratedMoviesNames = new ArrayList<>(copyUser.getRatedMoviesNames());
        availableMovies = new ArrayList<>(copyUser.getAvailableMovies());
        notifications = new ArrayList<>(copyUser.getNotifications());
        subscribedGenres = new ArrayList<>(copyUser.getSubscribedGenres());
        visitedPages = new Stack<>();
        visitedPages.addAll(copyUser.getVisitedPages());
        visitedMovies = new Stack<>();
        visitedMovies.addAll(copyUser.getVisitedMovies());
    }

    public RegisteredUser(final InputCredentials credentials) {
        setCredentials(new InputCredentials(credentials));
        tokensCount = 0;
        numFreePremiumMovies = NUM_FREE_PREMIUM_MOVIES;
        purchasedMovies = new ArrayList<>();
        watchedMovies = new ArrayList<>();
        likedMovies = new ArrayList<>();
        ratedMovies = new ArrayList<>();
        purchasedMoviesNames = new ArrayList<>();
        watchedMoviesNames = new ArrayList<>();
        likedMoviesNames = new ArrayList<>();
        ratedMoviesNames = new ArrayList<>();
        availableMovies = new ArrayList<>();
        notifications = new ArrayList<>();
        subscribedGenres = new ArrayList<>();
        visitedPages = new Stack<>();
        visitedMovies = new Stack<>();
    }

    public int getTokensCount() {
        return tokensCount;
    }

    public void setTokensCount(final int tokensCount) {
        this.tokensCount = tokensCount;
    }

    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public void setNumFreePremiumMovies(final int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    public ArrayList<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }

    public void setPurchasedMovies(final ArrayList<Movie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(final ArrayList<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public ArrayList<Movie> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(final ArrayList<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public ArrayList<String> getPurchasedMoviesNames() {
        return purchasedMoviesNames;
    }

    public ArrayList<String> getWatchedMoviesNames() {
        return watchedMoviesNames;
    }

    public ArrayList<String> getLikedMoviesNames() {
        return likedMoviesNames;
    }

    public ArrayList<String> getRatedMoviesNames() {
        return ratedMoviesNames;
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(final ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    public ArrayList<Movie> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(final ArrayList<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    public ArrayList<Movie> getAvailableMovies() {
        return availableMovies;
    }

    public void setAvailableMovies(final ArrayList<Movie> availableMovies) {
        this.availableMovies = availableMovies;
    }

    public ArrayList<String> getSubscribedGenres() {
        return subscribedGenres;
    }

    public Stack<Page> getVisitedPages() {
        return visitedPages;
    }

    public Stack<Movie> getVisitedMovies() {
        return visitedMovies;
    }

    /** This method is created to make the addition for numFreePremiumMovies easier */
    public void addNumFreePremiumMovies(final int addition) {
        numFreePremiumMovies += addition;
    }

    /** This method is created to make the subtraction from numFreePremiumMovies easier */
    public void subNumFreePremiumMovies(final int subtraction) {
        numFreePremiumMovies -= subtraction;
    }

    /** This method is created to make the addition for tokensCount easier */
    public void addTokensCount(final int addition) {
        tokensCount += addition;
    }

    /** This method is created to make the subtraction from tokensCount easier */
    public void subTokensCount(final int subtraction) {
        tokensCount -= subtraction;
    }
}
