import itmo.kasymov.exceptions.BanksException;
import itmo.kasymov.models.accounts.Account;
import itmo.kasymov.models.accounts.DebitAccount;
import itmo.kasymov.models.accounts.DepositAccount;
import itmo.kasymov.models.banks.Bank;
import itmo.kasymov.models.banks.CentralBank;
import itmo.kasymov.models.banks.Terms;
import itmo.kasymov.models.clients.Client;
import itmo.kasymov.models.transactions.Deposit;
import itmo.kasymov.models.transactions.Transaction;
import itmo.kasymov.models.transactions.Transfer;
import itmo.kasymov.models.transactions.Withdraw;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BanksTest {
    @Test
    public void depositMoneyToAccountAndWithdraw() throws BanksException {
        final int DEPOSIT = 100000;
        final int WITHDRAW = 500;
        CentralBank centralBank = CentralBank.getInstance();
        Terms terms = new Terms(10, 10, 10, 10, 1000, 1, 500);
        Bank bank = new Bank("EldaBank", terms);
        Client client = new Client.Builder().firstName("Eldar").lastName("Kasymov").address("Pushkina Street, Kolotushkina House 1").passport("2281337").create();
        Account account = bank.createDebitAccount(client);
        Transaction depositTransaction = new Deposit(DEPOSIT, account);
        Transaction withdrawTransaction = new Withdraw(WITHDRAW, account);

        centralBank.registerClient(client);
        centralBank.registerBank(bank);
        centralBank.executeTransaction(depositTransaction);
        centralBank.executeTransaction(withdrawTransaction);

        assertEquals(DEPOSIT - WITHDRAW, account.getBalance());
    }

    @Test
    public void withdrawTooMuchMoneyFromCreditAccount() throws BanksException {
        final int WITHDRAW = 10001;
        CentralBank centralBank = CentralBank.getInstance();
        Terms terms = new Terms(10, 10, 10, 10, 1000, 1, 500);
        Bank bank = new Bank("EldaBank", terms);
        Client client = new Client.Builder().firstName("Eldar").lastName("Kasymov").address("Pushkina Street, Kolotushkina House 1").passport("2281337").create();
        Account account = bank.createCreditAccount(client);
        Transaction transaction = new Withdraw(WITHDRAW, account);

        centralBank.registerClient(client);
        centralBank.registerBank(bank);

        BanksException exception = assertThrows(BanksException.class, () -> centralBank.executeTransaction(transaction));
        assertEquals("Credit limit is exceeded.", exception.getMessage());
    }

    @Test
    public void subscriptionTest() throws BanksException {
        final double newInterest = 25;
        CentralBank centralBank = CentralBank.getInstance();
        Terms terms = new Terms(10, 10, 10, 10, 1000, 1, 500);
        Bank bank = new Bank("EldaBank", terms);
        Client client = new Client.Builder().firstName("Eldar").lastName("Kasymov").address("Pushkina Street, Kolotushkina House 1").passport("2281337").create();
        Account account = new DepositAccount(client, bank);

        centralBank.registerClient(client);
        centralBank.registerBank(bank);
        bank.getTerms().setMaxDepositInterest(newInterest);
        bank.attach(client);
        bank.setNewTerms(bank.getTerms());

        assertTrue(client.isSubscribed());
        assertTrue(bank.getObservers().contains(client));
    }

    @Test
    public void transferTest() throws BanksException {
        final double DEPOSIT = 1001;
        final double TRANSFER = 1000;
        CentralBank centralBank = CentralBank.getInstance();
        Terms terms = new Terms(10, 10, 10, 10, 1000, 1, 500);
        Bank bank = new Bank("EldaBank", terms);
        Client client1 = new Client.Builder().firstName("Eldar").lastName("Kasymov").address("Pushkina Street, Kolotushkina House 1").passport("2281337").create();
        Client client2 = new Client.Builder().firstName("Polina").lastName("Khartanovich").address("Pushkina Street, Kolotushkina House 2").passport("1337228").create();
        Account account1 = new DebitAccount(client1, bank);
        Account account2 = new DebitAccount(client2, bank);
        Transaction deposit = new Deposit(DEPOSIT, account1);
        Transaction transfer = new Transfer(TRANSFER, account1, account2);

        centralBank.registerClient(client1);
        centralBank.registerBank(bank);
        centralBank.executeTransaction(deposit);
        centralBank.executeTransaction(transfer);

        assertEquals(DEPOSIT - TRANSFER, account1.getBalance());
        assertEquals(TRANSFER, account2.getBalance());
    }

    @Test
    public void cancelTransferTest() throws BanksException {
        final double START_CREDITS = 0;
        final double DEPOSIT = 1001;
        final double TRANSFER = 1000;
        CentralBank centralBank = CentralBank.getInstance();
        Terms terms = new Terms(10, 10, 10, 10, 1000, 1, 500);
        Bank bank = new Bank("EldaBank", terms);
        Client client1 = new Client.Builder().firstName("Eldar").lastName("Kasymov").address("Pushkina Street, Kolotushkina House 1").passport("2281337").create();
        Client client2 = new Client.Builder().firstName("Polina").lastName("Khartanovich").address("Pushkina Street, Kolotushkina House 2").passport("1337228").create();
        Account account1 = new DebitAccount(client1, bank);
        Account account2 = new DebitAccount(client2, bank);
        Transaction deposit = new Deposit(DEPOSIT, account1);
        Transaction transfer = new Transfer(TRANSFER, account1, account2);

        centralBank.registerClient(client1);
        centralBank.registerClient(client2);
        centralBank.registerBank(bank);
        centralBank.executeTransaction(deposit);
        centralBank.executeTransaction(transfer);
        centralBank.cancelTransaction(transfer);

        assertEquals(DEPOSIT, account1.getBalance());
        assertEquals(START_CREDITS, account2.getBalance());
    }
}