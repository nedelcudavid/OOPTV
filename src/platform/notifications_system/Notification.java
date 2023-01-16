package platform.notifications_system;

import platform.RegisteredUser;

public abstract class Notification {
    private String movieName;

    public Notification(final String movieName) {
        this.movieName = movieName;
    }

    public abstract void addNotificationToUser(RegisteredUser user);

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

}
