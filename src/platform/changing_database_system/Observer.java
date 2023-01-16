package platform.changing_database_system;

import platform.Movie;

public abstract class Observer {
    protected DatabaseChange databaseChange;

    /** This functions updates the database accordingly to the observer which is called */
    public abstract void update(String changeType, Movie movie);
}
