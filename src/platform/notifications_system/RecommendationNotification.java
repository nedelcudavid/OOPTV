package platform.notifications_system;

import platform.RegisteredUser;

public class RecommendationNotification extends Notification{

    private String message = "Recommendation";

    public RecommendationNotification(String movieName) {
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
