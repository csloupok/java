package itmo.kasymov.models.accounts;

import itmo.kasymov.exceptions.BanksException;
import itmo.kasymov.models.banks.Bank;
import itmo.kasymov.models.banks.CentralBank;
import itmo.kasymov.models.clients.Client;

public class DebitAccount extends Account {
    private double interestCredits;

    public DebitAccount(Client client, Bank bank) throws BanksException {
        super(client, bank);
        interestCredits = MIN_AMOUNT_OF_CREDITS;
    }

    @Override
    public void deposit(double amount) throws BanksException {
        if (amount < MIN_AMOUNT_OF_CREDITS)
            throw new BanksException("Number of credits can't be negative.");
        setBalance(getBalance() + amount);
    }

    @Override
    public void withdraw(double amount) throws BanksException {
        if (amount < MIN_AMOUNT_OF_CREDITS)
            throw new BanksException("Number of credits can't be negative.");
        if (getBalance() < amount)
            throw new BanksException("Not enough credits.");
        if (!getClient().isConfirmed() && amount > getBank().getTerms().getUnconfirmedLimit())
            throw new BanksException("Unconfirmed limit is exceeded.");
        setBalance(getBalance() - amount);
    }

    /**
     * Function deposit interest credits that calculated from bank terms.
     */
    @Override
    public void refreshAccount() {
        interestCredits += getBalance() * getBank().getTerms().calculateDailyInterest(getBank().getTerms().getDebitInterest(), CentralBank.getInstance().getCurrentDate());
        if (CentralBank.getInstance().getCurrentDate().getDayOfMonth() != getCreationDate().getDayOfMonth()) return;
        setBalance(getBalance() + interestCredits);
        interestCredits = 0;
    }
}
