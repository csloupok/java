package itmo.kasymov.models.banks;

import itmo.kasymov.exceptions.BanksException;

import java.time.LocalDate;

public class Terms {
    public final double MIN_INTEREST = 0;
    public final double MAX_INTEREST = 25;
    public final double MAX_FEE = 50;
    private final int DAYS_IN_REG_YEAR = 365;
    private final int DAYS_IN_LEAP_YEAR = 366;
    private final double MIN_FEE = 0;
    private final double MIN_LIMIT = 0;
    private double debitInterest;
    private double minDepositCredits = 50000;
    private double maxDepositCredits = 10000;
    private double minDepositInterest;
    private double midDepositInterest;
    private double maxDepositInterest;
    private double creditLimit;
    private double creditFee;
    private double unconfirmedLimit;

    public Terms(
            double debitInterest,
            double minDepositInterest,
            double midDepositInterest,
            double maxDepositInterest,
            double creditLimit,
            double creditFee,
            double unconfirmedLimit) throws BanksException {
        if (debitInterest < MIN_INTEREST || debitInterest > MAX_INTEREST)
            throw new BanksException("Incorrect deposit interest.");
        if (minDepositInterest < MIN_INTEREST || minDepositInterest > MAX_INTEREST)
            throw new BanksException("Incorrect deposit interest.");
        if (minDepositInterest > midDepositInterest || minDepositInterest > maxDepositInterest)
            throw new BanksException("Minimal deposit interest can't be higher than other.");
        if (midDepositInterest > maxDepositInterest)
            throw new BanksException("Middle deposit interest can't be higher than maximal.");
        if (midDepositInterest < MIN_INTEREST | midDepositInterest > MAX_INTEREST)
            throw new BanksException("Incorrect deposit interest.");
        if (maxDepositInterest < MIN_INTEREST || maxDepositInterest > MAX_INTEREST)
            throw new BanksException("Incorrect deposit interest.");
        if (creditLimit < MIN_LIMIT)
            throw new BanksException("Incorrect credit limit.");
        if (unconfirmedLimit < MIN_LIMIT)
            throw new BanksException("Incorrect unconfirmed limit.");
        if (creditFee < MIN_FEE || creditFee > MAX_FEE)
            throw new BanksException("Incorrect credit fee.");
        this.debitInterest = debitInterest;
        this.minDepositInterest = minDepositInterest;
        this.midDepositInterest = midDepositInterest;
        this.maxDepositInterest = maxDepositInterest;
        this.creditLimit = creditLimit;
        this.creditFee = creditFee;
        this.unconfirmedLimit = unconfirmedLimit;
    }

    public double getMIN_INTEREST() {
        return MIN_INTEREST;
    }

    public double getMAX_INTEREST() {
        return MAX_INTEREST;
    }

    public double getMAX_FEE() {
        return MAX_FEE;
    }

    public int getDAYS_IN_REG_YEAR() {
        return DAYS_IN_REG_YEAR;
    }

    public int getDAYS_IN_LEAP_YEAR() {
        return DAYS_IN_LEAP_YEAR;
    }

    public double getMIN_FEE() {
        return MIN_FEE;
    }

    public double getMIN_LIMIT() {
        return MIN_LIMIT;
    }

    public double getDebitInterest() {
        return debitInterest;
    }

    public double getMinDepositCredits() {
        return minDepositCredits;
    }

    public double getMaxDepositCredits() {
        return maxDepositCredits;
    }

    public double getMinDepositInterest() {
        return minDepositInterest;
    }

    public double getMidDepositInterest() {
        return midDepositInterest;
    }

    public double getMaxDepositInterest() {
        return maxDepositInterest;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public double getCreditFee() {
        return creditFee;
    }

    public double getUnconfirmedLimit() {
        return unconfirmedLimit;
    }

    public void setDebitInterest(double value) throws BanksException {
        if (value < MIN_INTEREST || value > MAX_INTEREST)
            throw new BanksException("Incorrect debit interest.");
        debitInterest = value;
    }

    public void setMinDepositInterest(double value) throws BanksException {
        if (value < MIN_INTEREST || value > MAX_INTEREST)
            throw new BanksException("Incorrect deposit interest.");
        if (value > midDepositInterest || value > maxDepositInterest)
            throw new BanksException("Minimal deposit interest can't be higher than other.");
        minDepositInterest = value;
    }

    public void setMidDepositInterest(double value) throws BanksException {
        if (value < MIN_INTEREST || value > MAX_INTEREST)
            throw new BanksException("Incorrect deposit interest.");
        if (value < minDepositInterest || value > maxDepositInterest)
            throw new BanksException("Middle deposit interest can't be higher than maximal or lower than minimal.");
        midDepositInterest = value;
    }

    public void setMaxDepositInterest(double value) throws BanksException {
        if (value < MIN_INTEREST || value > MAX_INTEREST)
            throw new BanksException("Incorrect deposit interest.");
        if (value < midDepositInterest || value < minDepositInterest)
            throw new BanksException("Maximal deposit interest can't be lower than other.");
        maxDepositInterest = value;
    }

    public void setMinDepositCredits(double value) throws BanksException {
        if (value < MIN_LIMIT)
            throw new BanksException("Amount of credits can't be negative.");
        minDepositCredits = value;
    }

    public void setMaxDepositCredits(double value) throws BanksException {
        if (value < MIN_LIMIT)
            throw new BanksException("Amount of credits can't be negative.");
        maxDepositCredits = value;
    }

    public void setCreditLimit(double value) throws BanksException {
        if (value < MIN_LIMIT)
            throw new BanksException("Credit limit can't be less than {MinLimit}.");
        creditLimit = value;
    }

    public void setCreditFee(double value) throws BanksException {
        if (value < MIN_FEE || value > MAX_FEE)
            throw new BanksException("Incorrect credit fee.");
        creditFee = value;
    }

    public void setUnconfirmedLimit(double value) throws BanksException {
        if (value < MIN_LIMIT)
            throw new BanksException("Incorrect unconfirmed limit.");
        unconfirmedLimit = value;
    }

    public double calculateDailyInterest(double interest, LocalDate date) {
        if (date.isLeapYear())
            return interest / 100 / DAYS_IN_LEAP_YEAR;
        return interest / 100 / DAYS_IN_REG_YEAR;
    }
}
