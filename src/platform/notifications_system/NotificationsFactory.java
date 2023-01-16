package platform.notifications_system;

public class NotificationsFactory {
    public Notification createNotification(String type, String message) {
        if (type == null) {
            return null;
        }

        if (type.equals("ADD")) {
            return new AddMovieNotification(message);
        } else if (type.equals("DELETE")) {
            return new DeleteMovieNotification(message);
        } else if (type.equals("Recommendation")) {
            return new RecommendationNotification(message);
        }

        return null;
    }
}
