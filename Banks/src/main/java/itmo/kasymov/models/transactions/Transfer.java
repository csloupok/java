package itmo.kasymov.models.transactions;

import itmo.kasymov.exceptions.BanksException;
import itmo.kasymov.models.accounts.Account;

public class Transfer extends Transaction {
    public Transfer(double credits, Account sender, Account recipient) {
        super(credits, sender, recipient);
    }

    @Override
    public void execute() throws BanksException {
        if (getSender() == null)
            throw new BanksException("Sender is null.");
        if (getRecipient() == null)
            throw new BanksException("Recipient is null.");
        if (isExecuted())
            throw new BanksException("Can't execute operation twice.");
        if (isCanceled())
            throw new BanksException("Operation was canceled.");
        getSender().withdraw(getCredits());
        getRecipient().deposit(getCredits());
        executed();
    }

    @Override
    public void cancel() throws BanksException {
        if (getSender() == null)
            throw new BanksException("Sender is null.");
        if (getRecipient() == null)
            throw new BanksException("Recipient is null.");
        if (!isExecuted())
            throw new BanksException("Operation was not executed.");
        if (isCanceled())
            throw new BanksException("Can't cancel operation twice.");
        getSender().deposit(getCredits());
        getRecipient().withdraw(getCredits());
        canceled();
    }
}
