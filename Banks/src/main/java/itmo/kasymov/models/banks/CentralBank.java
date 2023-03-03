package itmo.kasymov.models.banks;

import itmo.kasymov.exceptions.BanksException;
import itmo.kasymov.models.accounts.Account;
import itmo.kasymov.models.clients.Client;
import itmo.kasymov.models.transactions.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CentralBank {
    private final int MIN_COUNT = 0;
    private static CentralBank centralBank;
    private List<Bank> banks;
    private List<Client> clients;
    private List<Transaction> transactions;
    private LocalDate currentDate;

    private CentralBank() {
        banks = new ArrayList<>();
        clients = new ArrayList<>();
        transactions = new ArrayList<>();
        currentDate = LocalDate.now();
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    /**
     * @return instance of singleton CentralBank.
     */
    public static CentralBank getInstance() {
        if (centralBank == null) {
            centralBank = new CentralBank();
        }
        return centralBank;
    }

    public void executeTransaction(Transaction transaction) throws BanksException {
        if (transaction == null)
            throw new BanksException("Transaction is null.");
        transaction.execute();
        transactions.add(transaction);
    }

    public void cancelTransaction(Transaction transaction) throws BanksException {
        if (transaction == null)
            throw new BanksException("Transaction is null.");
        transaction.cancel();
    }

    public void registerBank(Bank bank) throws BanksException {
        if (bank == null)
            throw new BanksException("Bank is null.");
        banks.add(bank);
    }

    public void registerClient(Client client) throws BanksException {
        if (client == null)
            throw new BanksException("Client is null.");
        clients.add(client);
    }

    /**
     * Function that used to skip days.
     * @param count Number of days to skip.
     * @throws BanksException
     */
    public void rewindDay(int count) throws BanksException {
        if (count <= MIN_COUNT)
            throw new BanksException("Count of days can't be negative.");
        for (int i = MIN_COUNT; i < count; i++) {
            currentDate.plusDays(count);
            for (Bank bank : banks) {
                bank.refreshAccounts();
            }
        }
    }

    /**
     * Function that used to skip months (uses rewindDay method).
     * @param count Number of months to skip.
     * @throws BanksException
     */
    public void rewindMonth(int count) throws BanksException {
        if (count <= MIN_COUNT)
            throw new BanksException("Count of months can't be negative.");
        for (int i = MIN_COUNT; i < count; i++)
            rewindDay(currentDate.lengthOfMonth());
    }

    /**
     * Function that used to skip years (uses rewindMonth method).
     * @param count Number of years to skip.
     * @throws BanksException
     */
    public void rewindYear(int count) throws BanksException {
        if (count <= MIN_COUNT)
            throw new BanksException("Count of years can't be negative.");
        int NUMBER_OF_MONTHS_IN_YEAR = 12;
        for (int i = MIN_COUNT; i < count; i++)
            rewindMonth(NUMBER_OF_MONTHS_IN_YEAR);
    }

    public Bank findBank(String name) {
        return banks.stream()
                .filter(bank -> name.equals(bank.getName()))
                .findAny()
                .orElse(null);

    }

    public Client findClient(String id) {
        return clients.stream()
                .filter(client -> id.equals(client.getId()))
                .findAny()
                .orElse(null);
    }

    public Account findAccount(Bank bank, String id) throws BanksException {
        if (bank == null)
            throw new BanksException("Bank is null.");
        return bank.getAccounts().stream()
                .filter(account -> id.equals(account.getId()))
                .findAny()
                .orElse(null);
    }
}
