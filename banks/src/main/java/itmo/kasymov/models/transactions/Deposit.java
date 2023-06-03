package itmo.kasymov.models.transactions;

import itmo.kasymov.exceptions.BanksException;
import itmo.kasymov.models.accounts.Account;

public class Deposit extends Transaction {


    public Deposit(double credits, Account recipient) {
        super(credits, null, recipient);
    }

    @Override
    public void execute() throws BanksException {
        if (getRecipient() == null)
            throw new BanksException("Recipient is null.");
        if (isExecuted())
            throw new BanksException("Can't execute operation twice.");
        if (isCanceled())
            throw new BanksException("Operation was canceled.");
        getRecipient().deposit(getCredits());
        executed();
    }

    @Override
    public void cancel() throws BanksException {
        if (getRecipient() == null)
            throw new BanksException("Recipient is null.");
        if (!isExecuted())
            throw new BanksException("Operation was not executed.");
        if (isCanceled())
            throw new BanksException("Can't cancel operation twice.");
        getRecipient().withdraw(getCredits());
        canceled();
    }
}
