package itmo.kasymov.models.banks;

import itmo.kasymov.exceptions.BanksException;
import itmo.kasymov.interfaces.Observer;
import itmo.kasymov.interfaces.Subject;
import itmo.kasymov.models.accounts.Account;
import itmo.kasymov.models.accounts.CreditAccount;
import itmo.kasymov.models.accounts.DebitAccount;
import itmo.kasymov.models.accounts.DepositAccount;
import itmo.kasymov.models.clients.Client;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Bank implements Subject {
    private String name;
    private Terms terms;
    private UUID id;
    private List<Account> accounts;
    private List<Observer> observers;

    public Bank(String name, Terms terms) throws BanksException {
        if (StringUtils.isBlank(name))
            throw new BanksException("Bank name is null.");
        this.name = name;
        if (terms == null)
            throw new BanksException("Terms are null.");
        this.terms = terms;
        this.id = UUID.randomUUID();
        this.accounts = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public Terms getTerms() {
        return terms;
    }

    public String getName() {
        return name;
    }

    public List<Observer> getObservers() {
        return Collections.unmodifiableList(observers);
    }

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    public String getId() {
        return id.toString();
    }


    /**
     * @param client Client for whom we create account.
     * @return New debit account.
     * @throws BanksException is thrown when given client is null.
     */
    public DebitAccount createDebitAccount(Client client) throws BanksException {
        if (client == null)
            throw new BanksException("Client is null.");
        DebitAccount debitAccount = new DebitAccount(client, this);
        this.accounts.add(debitAccount);
        return debitAccount;
    }

    public DepositAccount createDepositAccount(Client client) throws BanksException {
        if (client == null)
            throw new BanksException("Client is null.");
        DepositAccount depositAccount = new DepositAccount(client, this);
        this.accounts.add(depositAccount);
        return depositAccount;
    }

    public CreditAccount createCreditAccount(Client client) throws BanksException {
        if (client == null)
            throw new BanksException("Client is null.");
        CreditAccount creditAccount = new CreditAccount(client, this);
        this.accounts.add(creditAccount);
        return creditAccount;
    }

    public void setNewTerms(Terms terms) throws BanksException {
        if (terms == null)
            throw new BanksException("Terms are null.");
        this.terms = terms;
        notifyMe();
    }

    /**
     * Utility function that is used to refresh all account when day is passed.
     */
    public void refreshAccounts() {
        for (Account account : accounts) {
            account.refreshAccount();
        }
    }

    /**
     * Function attaches observer by adding it to bank's list and changing boolean field isSubscribed to true.
     * @param observer Subscriber that wants to be notified about events.
     * @throws BanksException when client is already subscribed.
     */
    @Override
    public void attach(Observer observer) throws BanksException {
        if (observer == null)
            throw new BanksException("Subscriber is null.");
        observer.subscribe();
        this.observers.add(observer);
        System.out.println("Bank: Client subscribed.");
    }

    /**
     * Function detaches observer by remocing it from bank's list and changing boolean field isSubscribed to false.
     * @param observer Subscriber that wants to be NOT notified about events anymore.
     * @throws BanksException when client is already unsubscribed.
     */
    @Override
    public void detach(Observer observer) throws BanksException {
        if (observer == null)
            throw new BanksException("Subscriber is null.");
        observer.unsubscribe();
        this.observers.remove(observer);
        System.out.println("Bank: Client unsubscribed.");
    }

    /**
     * Function that notifies all observers subscribed to events.
     */
    @Override
    public void notifyMe() {
        System.out.println("Bank: Notifying clients...");
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}
