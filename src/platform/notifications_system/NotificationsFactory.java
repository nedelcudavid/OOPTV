package platform.notifications_system;

public final class NotificationsFactory {

    /** This function creates any notification by given type */
    public Notification createNotification(final String type, final String message) {
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
