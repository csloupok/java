package itmo.kasymov.models.clients;

import itmo.kasymov.exceptions.BanksException;
import itmo.kasymov.interfaces.Observer;
import itmo.kasymov.interfaces.Subject;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public class Client implements Observer {
    private final String firstName;
    private final String lastName;
    private String address;
    private String passport;
    private final UUID id;
    private boolean subscription;

    private Client(String firstName, String lastName, String passport, String address) throws BanksException {
        if (StringUtils.isBlank(firstName)) throw new BanksException("First name is empty!");
        if (StringUtils.isBlank(lastName)) throw new BanksException("Last name is empty!");
        this.firstName = firstName;
        this.lastName = lastName;
        this.passport = passport;
        this.address = address;
        this.id = UUID.randomUUID();
        this.subscription = false;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassport() {
        return passport;
    }

    public String getAddress() {
        return address;
    }

    public String getId() {
        return id.toString();
    }

    public boolean isSubscribed() {
        return subscription;
    }

    public Client setAddress(String address) throws BanksException {
        if (StringUtils.isBlank(address)) throw new BanksException("Address is empty!");
        if (this.address != null) throw new BanksException("Address is already set.");
        this.address = address;
        return this;
    }

    public Client setPassport(String passport) throws BanksException {
        if (StringUtils.isBlank(passport)) throw new BanksException("Passport is empty!");
        if (this.passport != null) throw new BanksException("Passport is already set.");
        this.passport = passport;
        return this;
    }

    @Override
    public void subscribe() throws BanksException {
        if (this.subscription) throw new BanksException("Already subscribed.");
        subscription = true;
    }

    @Override
    public void unsubscribe() throws BanksException {
        if (!this.subscription) throw new BanksException("Already unsubscribed.");
        this.subscription = false;
    }

    @Override
    public void update(Subject subject) {
        System.out.println("I reacted.");
    }

    public boolean isConfirmed() {
        return this.address != null && this.passport != null;
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String passport;
        private String address;

        public Builder firstName(String firstName) throws BanksException {
            if (StringUtils.isBlank(firstName))
                throw new BanksException("First name is null.");
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) throws BanksException {
            if (StringUtils.isBlank(lastName))
                throw new BanksException("Last name is null.");
            this.lastName = lastName;
            return this;
        }

        public Builder passport(String passport) throws BanksException {
            if (StringUtils.isBlank(passport))
                throw new BanksException("Passport is null.");
            this.passport = passport;
            return this;
        }

        public Builder address(String address) throws BanksException {
            if (StringUtils.isBlank(address))
                throw new BanksException("Address is null.");
            this.address = address;
            return this;
        }

        public Client create() throws BanksException {
            return new Client(firstName, lastName, passport, address);
        }
    }
}
