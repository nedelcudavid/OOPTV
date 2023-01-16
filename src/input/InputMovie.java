package input;

import java.util.ArrayList;

public class InputMovie {
    private String name;
    private String year;
    private int duration;
    private ArrayList<String> genres;
    private ArrayList<String> actors;
    private ArrayList<String> countriesBanned;

    public InputMovie() {
    }
    public InputMovie(final InputMovie inputMovie) {
        name = inputMovie.getName();
        year = inputMovie.getYear();
        duration = inputMovie.getDuration();
        genres = inputMovie.getGenres();
        actors = inputMovie.getActors();
        countriesBanned = inputMovie.getCountriesBanned();
    }

    public InputMovie(final String title) {
        name = title;
    }

    public final String getName() {
        return name;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public final String getYear() {
        return year;
    }

    public final void setYear(final String year) {
        this.year = year;
    }

    public final int getDuration() {
        return duration;
    }

    public final void setDuration(final int duration) {
        this.duration = duration;
    }

    public final ArrayList<String> getGenres() {
        return genres;
    }

    public final void setGenres(final ArrayList<String> genres) {
        this.genres = genres;
    }

    public final ArrayList<String> getActors() {
        return actors;
    }

    public final void setActors(final ArrayList<String> actors) {
        this.actors = actors;
    }

    public final ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }

    public final void setCountriesBanned(final ArrayList<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }
}
