package platform.payment_system;

public interface PaymentStrategy {

    /** This is the pay method used in both payment ways
     *  available (by tokens / by free premium movie) */
    void pay();

}
