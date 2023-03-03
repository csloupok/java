package itmo.kasymov.models.accounts;

import itmo.kasymov.exceptions.BanksException;
import itmo.kasymov.models.banks.Bank;
import itmo.kasymov.models.clients.Client;

import java.time.LocalDate;
import java.util.UUID;

public abstract class Account {
    protected final int MIN_AMOUNT_OF_CREDITS = 0;
    private final Client client;
    private final Bank bank;
    private double balance;
    private final UUID id;
    private final LocalDate creationDate;

    protected Account(Client client, Bank bank) throws BanksException {
        if (client == null) throw new BanksException("Client is null.");
        this.client = client;
        if (bank == null) throw new BanksException("Bank is null.");
        this.bank = bank;
        this.balance = MIN_AMOUNT_OF_CREDITS;
        this.id = UUID.randomUUID();
        this.creationDate = LocalDate.now();
    }

    public Client getClient() {
        return client;
    }

    public Bank getBank() {
        return bank;
    }

    public double getBalance() {
        return balance;
    }

    public String getId() {
        return id.toString();
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public abstract void deposit(double amount) throws BanksException;

    public abstract void withdraw(double amount) throws BanksException;

    public abstract void refreshAccount();

    protected void setBalance(double balance) {
        this.balance = balance;
    }
}
