package platform.changing_database_system;

import platform.Movie;

import java.util.ArrayList;
import java.util.List;

public class DatabaseChange {
    private final List<Observer> observers = new ArrayList<>();
    private String lastChangeType;
    private Movie movieInCause;

    public void databaseChangeIsMade(final String changeType, final Movie movieInCause) {
        this.lastChangeType = changeType;
        this.movieInCause = movieInCause;
        notifyAllObservers();
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update(lastChangeType, movieInCause);
        }
    }
}
