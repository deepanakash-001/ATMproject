import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATM {
    private List<Account> accounts;

    public ATM() {
        this.accounts = new ArrayList<>();
        // Initialize some dummy accounts for testing
        accounts.add(new Account(1234, 1000, "John Doe", 1234));
        accounts.add(new Account(5678, 2000, "Jane Smith", 5678));
    }

    public boolean authenticate(int accountNumber, int pin) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber && account.getPin() == pin) {
                return true;
            }
        }
        return false;
    }

    public void displayOptions() {
        System.out.println("Options:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    public void displayBalance(int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                System.out.println("Balance: $" + account.getBalance());
                return;
            }
        }
        System.out.println("Account not found.");
    }

    public void deposit(int accountNumber, double amount) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                account.deposit(amount);
                System.out.println("Deposit successful. New balance: $" + account.getBalance());
                return;
            }
        }
        System.out.println("Account not found.");
    }

    public void withdraw(int accountNumber, double amount) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                if (account.getBalance() >= amount) {
                    account.withdraw(amount);
                    System.out.println("Withdrawal successful. New balance: $" + account.getBalance());
                } else {
                    System.out.println("Insufficient balance.");
                }
                return;
            }
        }
        System.out.println("Account not found.");
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the ATM");
            System.out.print("Enter account number: ");
            int accountNumber = scanner.nextInt();
            System.out.print("Enter PIN: ");
            int pin = scanner.nextInt();

            if (atm.authenticate(accountNumber, pin)) {
                System.out.println("Authentication successful.");
                atm.displayOptions();

                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        atm.displayBalance(accountNumber);
                        break;
                    case 2:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        atm.deposit(accountNumber, depositAmount);
                        break;
                    case 3:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        atm.withdraw(accountNumber, withdrawAmount);
                        break;
                    case 4:
                        System.out.println("Thank you for using the ATM.");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice.");
                }
            } else {
                System.out.println("Authentication failed. Please try again.");
            }
        }
    }
}

class Account {
    private int accountNumber;
    private double balance;
    private String ownerName;
    private int pin;

    public Account(int accountNumber, double balance, String ownerName, int pin) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.ownerName = ownerName;
        this.pin = pin;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    public int getPin() {
        return pin;
    }
}
