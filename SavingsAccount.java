public class SavingsAccount extends Account {
    private final double minimumBalance;
    private static final double INTEREST_RATE = 0.02; // 2% per month

    public SavingsAccount(String accountNumber, double initialBalance, double minimumBalance) {
        super(accountNumber, initialBalance);
        this.minimumBalance = minimumBalance;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("  [" + accountNumber + "] Withdrawal rejected: amount must be positive.");
            return;
        }
        if (balance - amount < minimumBalance) {
            System.out.printf("  [%s] Withdrawal of %.2f rejected: balance would fall below the minimum of %.2f.%n",
                    accountNumber, amount, minimumBalance);
            return;
        }
        balance -= amount;
        System.out.printf("  [%s] Withdrew %.2f. New balance: %.2f%n", accountNumber, amount, balance);
    }

    @Override
    public void endOfMonth() {
        double interest = balance * INTEREST_RATE;
        balance += interest;
        System.out.printf("  [%s] Interest of %.2f (%.0f%%) applied. New balance: %.2f%n",
                accountNumber, interest, INTEREST_RATE * 100, balance);
    }
}
