package platform.changing_database_system;

import platform.Movie;

public abstract class Observer {
    protected DatabaseChange databaseChange;
    public abstract void update(String changeType, Movie movie);
}
