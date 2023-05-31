package itmo.kasymov.models.transactions;

import itmo.kasymov.exceptions.BanksException;
import itmo.kasymov.interfaces.Command;
import itmo.kasymov.models.accounts.Account;

import java.util.UUID;

public abstract class Transaction implements Command {
    private double credits;
    private UUID id;
    private boolean isExecuted;
    private boolean isCanceled;
    private Account sender;
    private Account recipient;

    public Transaction(double credits, Account sender, Account recipient) {
        this.credits = credits;
        id = UUID.randomUUID();
        isExecuted = false;
        isCanceled = false;
        this.sender = sender;
        this.recipient = recipient;
    }

    public double getCredits() {
        return credits;
    }

    public String getId() {
        return id.toString();
    }

    public boolean isExecuted() {
        return isExecuted;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public Account getSender() {
        return sender;
    }

    public Account getRecipient() {
        return recipient;
    }

    public abstract void execute() throws BanksException;

    public abstract void cancel() throws BanksException;

    protected void executed() {
        isExecuted = true;
    }

    protected void canceled() {
        isCanceled = true;
    }
}
