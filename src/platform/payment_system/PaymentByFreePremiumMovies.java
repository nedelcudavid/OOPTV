package platform.payment_system;

import platform.Executable;
import platform.Movie;
import platform.RegisteredUser;

public class PaymentByFreePremiumMovies implements PaymentStrategy{

    /** This function is created in order to pay the movie with
     *  free premium movies and add it to your collection*/
    @Override
    public void pay() {
        Movie currentMovie = Executable.getExe().getCurrentMovieList().get(0);
        RegisteredUser currentUser = Executable.getExe().getCurrentUser();
        currentUser.subNumFreePremiumMovies(1);
        Movie purchasedMovie = new Movie(currentMovie);
        currentUser.getPurchasedMovies().add(purchasedMovie);
        currentUser.getPurchasedMoviesNames().add(purchasedMovie.getName());
    }
}
