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

    public void setStartsWith(final String startsWith) {
        this.startsWith = startsWith;
    }

    public InputFilters getFilters() {
        return filters;
    }

    public void setFilters(final InputFilters filters) {
        this.filters = filters;
    }

    public String getCount() {
        return count;
    }

    public void setCount(final String count) {
        this.count = count;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(final String movie) {
        this.movie = movie;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(final String objectType) {
        this.objectType = objectType;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(final int rate) {
        this.rate = rate;
    }

    public String getSubscribedGenre() {
        return subscribedGenre;
    }

    public void setSubscribedGenre(String subscribedGenre) {
        this.subscribedGenre = subscribedGenre;
    }

    public InputMovie getAddedMovie() {
        return addedMovie;
    }

    public void setAddedMovie(InputMovie addedMovie) {
        this.addedMovie = addedMovie;
    }

    public String getDeletedMovie() {
        return deletedMovie;
    }

    public void setDeletedMovie(String deletedMovie) {
        this.deletedMovie = deletedMovie;
    }

    public Movie getEntireMovie() {
        return entireMovie;
    }

    public void setEntireMovie(Movie entireMovie) {
        this.entireMovie = entireMovie;
    }
}
