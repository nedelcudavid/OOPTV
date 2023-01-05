package platform;

import com.fasterxml.jackson.annotation.JsonIgnore;
import input.InputCredentials;
import input.InputUser;
import java.util.ArrayList;

/** This class represents the registered user's characteristics */
public final class RegisteredUser extends InputUser {
    private int tokensCount;
    private int numFreePremiumMovies;
    private ArrayList<Movie> purchasedMovies;
    @JsonIgnore
    private ArrayList<String> purchasedMoviesNames;
    private ArrayList<Movie> watchedMovies;
    @JsonIgnore
    private ArrayList<String> watchedMoviesNames;
    private ArrayList<Movie> likedMovies;
    @JsonIgnore
    private ArrayList<String> likedMoviesNames;
    private ArrayList<Movie> ratedMovies;
    @JsonIgnore
    private ArrayList<String> ratedMoviesNames;
    @JsonIgnore
    private ArrayList<Movie> availableMovies;
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

    public void setPurchasedMoviesNames(final ArrayList<String> purchasedMoviesNames) {
        this.purchasedMoviesNames = purchasedMoviesNames;
    }

    public ArrayList<String> getWatchedMoviesNames() {
        return watchedMoviesNames;
    }

    public void setWatchedMoviesNames(final ArrayList<String> watchedMoviesNames) {
        this.watchedMoviesNames = watchedMoviesNames;
    }

    public ArrayList<String> getLikedMoviesNames() {
        return likedMoviesNames;
    }

    public void setLikedMoviesNames(final ArrayList<String> likedMoviesNames) {
        this.likedMoviesNames = likedMoviesNames;
    }

    public ArrayList<String> getRatedMoviesNames() {
        return ratedMoviesNames;
    }

    public void setRatedMoviesNames(final ArrayList<String> ratedMoviesNames) {
        this.ratedMoviesNames = ratedMoviesNames;
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
