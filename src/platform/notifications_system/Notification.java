package platform.notifications_system;

import platform.RegisteredUser;

public abstract class Notification {
    private String movieName;

    public Notification(final String movieName) {
        this.movieName = movieName;
    }

    /** This function adds the notification witch calls
     *  it to the specified user's notifications list */
    public abstract void addNotificationToUser(RegisteredUser user);

    /** movieName getter */
    public String getMovieName() {
        return movieName;
    }

    /** movieName setter */
    public void setMovieName(final String movieName) {
        this.movieName = movieName;
    }

}
