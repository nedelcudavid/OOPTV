package platform.payment_system;

import platform.Executable;
import platform.Movie;
import platform.RegisteredUser;

public class PaymentByTokens implements PaymentStrategy{

    /** This function is created in order to pay the
     * movie with tokens and add it to your collection*/
    @Override
    public void pay() {
        RegisteredUser currentUser = Executable.getExe().getCurrentUser();
        currentUser.subTokensCount(2);
        Movie currentMovie = Executable.getExe().getCurrentMovieList().get(0);
        Movie purchasedMovie = new Movie(currentMovie);
        currentUser.getPurchasedMovies().add(purchasedMovie);
        currentUser.getPurchasedMoviesNames().add(purchasedMovie.getName());
    }
}
