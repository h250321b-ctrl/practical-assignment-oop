import java.util.ArrayList;
import java.util.List;

public class BankDemo {
    public static void main(String[] args) {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new SavingsAccount("SAV-001", 1000.00, 100.00));
        accounts.add(new CurrentAccount("CUR-001", 200.00, 500.00));
        accounts.add(new SavingsAccount("SAV-002", 150.00, 100.00));
        accounts.add(new CurrentAccount("CUR-002", 50.00, 300.00));

        System.out.println("=== Withdrawals (polymorphic) ===");
        double[] withdrawals = {300.00, 600.00, 100.00, 400.00};
        for (int i = 0; i < accounts.size(); i++) {
            Account acc = accounts.get(i);
            acc.withdraw(withdrawals[i]);
        }

        System.out.println("\n=== End of month (polymorphic) ===");
        for (Account acc : accounts) {
            acc.endOfMonth();
        }

        System.out.println("\n=== Edge cases ===");
        System.out.println("Savings withdrawal rejected (would breach minimum):");
        accounts.get(2).withdraw(100.00);   // SAV-002: 153 -> 53 < 100 minimum

        System.out.println("Current account overdraft within limit:");
        accounts.get(3).withdraw(100.00);   // CUR-002: 40 -> -60, within 300 limit

        System.out.println("Current account overdraft beyond limit rejected:");
        accounts.get(1).withdraw(1000.00);

        System.out.println("Invalid deposit rejected:");
        accounts.get(0).deposit(-50.00);

        System.out.println("\n=== Final balances ===");
        for (Account acc : accounts) {
            System.out.printf("  %s: %.2f%n", acc.getAccountNumber(), acc.getBalance());
        }
    }
}
