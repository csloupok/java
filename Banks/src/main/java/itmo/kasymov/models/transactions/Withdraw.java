package itmo.kasymov.models.transactions;

import itmo.kasymov.exceptions.BanksException;
import itmo.kasymov.models.accounts.Account;

public class Withdraw extends Transaction {
    public Withdraw(double credits, Account sender) {
        super(credits, sender, null);
    }

    @Override
    public void execute() throws BanksException {
        if (getSender() == null)
            throw new BanksException("Sender is null.");
        if (isExecuted())
            throw new BanksException("Can't execute operation twice.");
        if (isCanceled())
            throw new BanksException("Operation was canceled.");
        getSender().withdraw(getCredits());
        executed();
    }

    @Override
    public void cancel() throws BanksException {
        if (getSender() == null)
            throw new BanksException("Sender is null.");
        if (!isExecuted())
            throw new BanksException("Operation was not executed.");
        if (isCanceled())
            throw new BanksException("Can't cancel operation twice.");
        getSender().deposit(getCredits());
        canceled();
    }
}
