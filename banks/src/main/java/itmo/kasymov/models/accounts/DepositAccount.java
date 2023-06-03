package itmo.kasymov.models.accounts;

import itmo.kasymov.exceptions.BanksException;
import itmo.kasymov.models.banks.Bank;
import itmo.kasymov.models.banks.CentralBank;
import itmo.kasymov.models.clients.Client;

import java.time.LocalDate;

public class DepositAccount extends Account {

    private final LocalDate closingDate;
    private double interestCredits;
    private double currentInterest;

    public DepositAccount(Client client, Bank bank) throws BanksException {
        super(client, bank);
        int SPAN_YEARS_OF_ACCOUNT = 1;
        this.closingDate = getCreationDate().plusYears(SPAN_YEARS_OF_ACCOUNT);
        this.interestCredits = MIN_AMOUNT_OF_CREDITS;
        this.currentInterest = bank.getTerms().getMinDepositInterest();
    }

    public double getCurrentInterest() {
        return currentInterest;
    }

    @Override
    public void deposit(double amount) throws BanksException {
        if (amount < MIN_AMOUNT_OF_CREDITS)
            throw new BanksException("Number of credits can't be negative.");
        setBalance(getBalance() + amount);
    }

    @Override
    public void withdraw(double amount) throws BanksException {
        if (CentralBank.getInstance().getCurrentDate().isBefore(closingDate))
            throw new BanksException("Account is not closed yet.");
        if (amount < MIN_AMOUNT_OF_CREDITS)
            throw new BanksException("Number of credits can't be negative.");
        if (getBalance() < amount)
            throw new BanksException("Not enough credits.");
        if (!getClient().isConfirmed() && amount > getBank().getTerms().getUnconfirmedLimit())
            throw new BanksException("Unconfirmed limit is exceeded.");
        setBalance(getBalance() - amount);
    }

    /**
     * Function sets current interest according to balance and adds interest credits calculated from bank terms.
     */
    @Override
    public void refreshAccount() {
        if (getBalance() < getBank().getTerms().getMinDepositCredits())
            this.currentInterest = getBank().getTerms().getMinDepositInterest();
        if (getBalance() > getBank().getTerms().getMinDepositCredits() && getBalance() < getBank().getTerms().getMaxDepositCredits())
            this.currentInterest = getBank().getTerms().getMidDepositInterest();
        if (getBalance() > getBank().getTerms().getMaxDepositCredits())
            this.currentInterest = getBank().getTerms().getMaxDepositInterest();
        this.interestCredits += getBalance() * getBank().getTerms().calculateDailyInterest(getCurrentInterest(), CentralBank.getInstance().getCurrentDate());
        if (CentralBank.getInstance().getCurrentDate().getDayOfMonth() != getCreationDate().getDayOfMonth()) return;
        setBalance(getBalance() + this.interestCredits);
        this.interestCredits = 0;
    }
}
