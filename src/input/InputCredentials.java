package input;

public final class InputCredentials {
    private String name;
    private String password;
    private String accountType;
    private String country;
    private String balance;

    public InputCredentials() {
    }

    public InputCredentials(final InputCredentials copyCredentials) {
        name = copyCredentials.getName();
        password = copyCredentials.getPassword();
        accountType = copyCredentials.getAccountType();
        country = copyCredentials.getCountry();
        balance = copyCredentials.getBalance();
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(final String accountType) {
        this.accountType = accountType;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(final String balance) {
        this.balance = balance;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    /** This method is created to make the subtraction from balance easier */
    public void subBalance(final int subtraction) {
        int intBalance = Integer.parseInt(getBalance());
        intBalance -= subtraction;
        balance = Integer.toString(intBalance);
    }
}
