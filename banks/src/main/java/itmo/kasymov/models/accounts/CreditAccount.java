package itmo.kasymov.models.accounts;

import itmo.kasymov.exceptions.BanksException;
import itmo.kasymov.models.banks.Bank;
import itmo.kasymov.models.banks.CentralBank;
import itmo.kasymov.models.clients.Client;

public class CreditAccount extends Account {
    private double feeCredits;

    public CreditAccount(Client client, Bank bank) throws BanksException {
        super(client, bank);
    }

    @Override
    public void deposit(double amount) throws BanksException {
        if (amount < MIN_AMOUNT_OF_CREDITS) throw new BanksException("Number of credits can't be negative.");
        setBalance(getBalance() + amount);
    }

    /**
     * Function checks if client is confirmed and have enough credit limit to work.
     * @param amount Amount to withdraw from account.
     * @throws BanksException when negative amount is entered or when exceeded credit/unconfirmed limit.
     */
    @Override
    public void withdraw(double amount) throws BanksException {
        if (amount < MIN_AMOUNT_OF_CREDITS) throw new BanksException("Number of credits can't be negative.");
        if (!getClient().isConfirmed() && amount > getBank().getTerms().getUnconfirmedLimit())
            throw new BanksException("Unconfirmed limit is exceeded.");
        if (getBalance() - amount < -getBank().getTerms().getCreditLimit())
            throw new BanksException("Credit limit is exceeded.");
        setBalance(getBalance() - amount);
    }

    /**
     * If balance becomes negative function will withdraw credit fee that calculated from bank terms.
     */
    @Override
    public void refreshAccount() {
        if (getBalance() < MIN_AMOUNT_OF_CREDITS)
            feeCredits -= getBalance() * getBank().getTerms().calculateDailyInterest(getBank().getTerms().getCreditFee(), CentralBank.getInstance().getCurrentDate());
        if (CentralBank.getInstance().getCurrentDate().getDayOfMonth() != getCreationDate().getDayOfMonth()) return;
        setBalance(getBalance() + feeCredits);
        feeCredits = 0;
    }
}
