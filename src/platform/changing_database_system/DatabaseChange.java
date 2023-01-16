package platform.changing_database_system;

import platform.Movie;

import java.util.ArrayList;
import java.util.List;

/** The subject of the observer pattern */
public final class DatabaseChange {
    private final List<Observer> observers = new ArrayList<>();
    private String lastChangeType;
    private Movie movieInCause;

    /** This function gather the info of the database change
     * and call the observer notifier in order to use it */
    public void databaseChangeIsMade(final String changeType, final Movie movie) {
        this.lastChangeType = changeType;
        this.movieInCause = movie;
        notifyAllObservers();
    }

    /** This function attaches an observer to the subject's observers list */
    public void attach(final Observer observer) {
        observers.add(observer);
    }

    /** This function notify all observers that a database change has been requested */
    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update(lastChangeType, movieInCause);
        }
    }
}
