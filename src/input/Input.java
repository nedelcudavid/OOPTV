package input;

import java.util.ArrayList;

/** This class and all its children classes are designed for receiving the input */
public final class Input {
    private ArrayList<InputUser> users;
    private ArrayList<InputMovie> movies;
    private ArrayList<InputAction> actions;

    public Input() {
    }

    public ArrayList<InputUser> getUsers() {
        return users;
    }

    public void setUsers(final ArrayList<InputUser> users) {
        this.users = users;
    }

    public ArrayList<InputMovie> getMovies() {
        return movies;
    }

    public void setMovies(final ArrayList<InputMovie> movies) {
        this.movies = movies;
    }

    public ArrayList<InputAction> getActions() {
        return actions;
    }

    public void setActions(final ArrayList<InputAction> actions) {
        this.actions = actions;
    }
}
