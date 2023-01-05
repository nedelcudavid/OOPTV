package input;

public class InputUser {
    private InputCredentials credentials;

    public InputUser() {
    }

    public InputUser(final InputUser copyUser) {
        credentials = new InputCredentials(copyUser.getCredentials());
    }

    public final  InputCredentials getCredentials() {
        return credentials;
    }

    public final void setCredentials(final InputCredentials credentials) {
        this.credentials = credentials;
    }
}
