package sims.character;

public class Account {
    private double balance;
    private int numWithdrawals = 0;

    public Account(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive!");
        }
        this.balance += amount;
    }

    public boolean withdraw(double amount) {
        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive!");
        }
        if (this.balance - amount >= 0) {
            this.balance -= amount;
            this.numWithdrawals += 1;
            return true;
        }
        return false;
    }

    public int getNumWithdrawals() {
        return numWithdrawals;
    }

    public double getBalance() {
        return balance;
    }
}
