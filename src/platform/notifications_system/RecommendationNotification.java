package platform.notifications_system;

import platform.RegisteredUser;

public final class RecommendationNotification extends Notification {

    private String message = "Recommendation";

    public RecommendationNotification(final String movieName) {
        super(movieName);
    }

    @Override
    public void addNotificationToUser(final RegisteredUser user) {
        user.getNotifications().add(this);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
