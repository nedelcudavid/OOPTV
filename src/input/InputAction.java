package input;

import platform.Movie;

public final class InputAction {
    private String type;
    private String page;
    private InputCredentials credentials;
    private String feature;
    private String startsWith;
    private InputFilters filters;
    private String count;
    private String movie;
    private String objectType;
    private int rate;
    private String subscribedGenre;
    private InputMovie addedMovie;
    private String deletedMovie;
    private Movie entireMovie;

    public InputAction() {

    }

    public InputAction(final String type, final String pageName, final Movie entireMovie) {
        this.type = type;
        this.page = pageName;
        this.entireMovie = new Movie(entireMovie);
    }

    public InputAction(final String type, final String pageName) {
        this.type = type;
        this.page = pageName;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getPage() {
        return page;
    }

    public void setPage(final String page) {
        this.page = page;
    }

    public InputCredentials getCredentials() {
        return credentials;
    }

    public void setCredentials(final InputCredentials credentials) {
        this.credentials = credentials;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(final String feature) {
        this.feature = feature;
    }

    public String getStartsWith() {
        return startsWith;
    }

    public InputFilters getFilters() {
        return filters;
    }

    public String getCount() {
        return count;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(final String movie) {
        this.movie = movie;
    }

    public int getRate() {
        return rate;
    }

    public String getSubscribedGenre() {
        return subscribedGenre;
    }

    public InputMovie getAddedMovie() {
        return addedMovie;
    }

    public String getDeletedMovie() {
        return deletedMovie;
    }

    public Movie getEntireMovie() {
        return entireMovie;
    }

}
