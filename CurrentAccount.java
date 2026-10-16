public class CurrentAccount extends Account {
    private final double overdraftLimit;
    private static final double MONTHLY_FEE = 10.00;

    public CurrentAccount(String accountNumber, double initialBalance, double overdraftLimit) {
        super(accountNumber, initialBalance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("  [" + accountNumber + "] Withdrawal rejected: amount must be positive.");
            return;
        }
        if (balance - amount < -overdraftLimit) {
            System.out.printf("  [%s] Withdrawal of %.2f rejected: exceeds overdraft limit of %.2f.%n",
                    accountNumber, amount, overdraftLimit);
            return;
        }
        balance -= amount;
        if (balance < 0) {
            System.out.printf("  [%s] Withdrew %.2f. Account is in overdraft. New balance: %.2f%n",
                    accountNumber, amount, balance);
        } else {
            System.out.printf("  [%s] Withdrew %.2f. New balance: %.2f%n", accountNumber, amount, balance);
        }
    }

    @Override
    public void endOfMonth() {
        balance -= MONTHLY_FEE;
        System.out.printf("  [%s] Maintenance fee of %.2f deducted. New balance: %.2f%n",
                accountNumber, MONTHLY_FEE, balance);
    }
}
