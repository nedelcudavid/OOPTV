package platform;

import com.fasterxml.jackson.annotation.JsonIgnore;
import input.InputMovie;
import java.text.DecimalFormat;
import java.util.ArrayList;

/** This class represents all the characteristics of a movie */
public final class Movie extends InputMovie {
    private int numLikes;
    private double rating;
    private int numRatings;
    @JsonIgnore
    private final ArrayList<Integer> allRatings;
    @JsonIgnore
    private static final DecimalFormat DF = new DecimalFormat("0.00");

    public Movie(final InputMovie inputMovie) {
        super(inputMovie);
        numLikes = 0;
        rating = 0;
        numRatings = 0;
        allRatings = new ArrayList<>();
    }

    public Movie(final Movie copyMovie) {
        super(copyMovie);
        numLikes = copyMovie.getNumLikes();
        rating = copyMovie.getRating();
        numRatings = copyMovie.getNumRatings();
        allRatings = new ArrayList<>(copyMovie.getAllRatings());
    }

    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(final int numLikes) {
        this.numLikes = numLikes;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(final double rating) {
        this.rating = rating;
    }

    public int getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(final int numRatings) {
        this.numRatings = numRatings;
    }

    public ArrayList<Integer> getAllRatings() {
        return allRatings;
    }

    /** This method is created to make the addition to numLikes easier */
    public void addNumLikes(final int addition) {
        this.numLikes += addition;
    }

    /** This method is created to make the addition to numRatings easier */
    public void addNumRating(final int addition) {
        this.numRatings += addition;
    }

    /** This method is created to rate a movie and calculate the new rating easier */
    public void addRating(final int newRating) {
        allRatings.add(newRating);
        int ratingsSum = 0;
        for (Integer allRating : allRatings) {
            ratingsSum += allRating;
        }
        double freshRating = (double) ratingsSum / numRatings;
        String formattedString = DF.format(freshRating);
        rating = Double.parseDouble(formattedString);
    }
}
