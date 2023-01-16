package platform.notifications_system;

import platform.RegisteredUser;

public class AddMovieNotification extends Notification{

    private String message = "ADD";

    public AddMovieNotification(String movieName) {
        super(movieName);
    }

    @Override
    public void addNotificationToUser(RegisteredUser user) {
        user.getNotifications().add(this);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
