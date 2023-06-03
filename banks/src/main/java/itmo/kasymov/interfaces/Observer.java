package itmo.kasymov.interfaces;

import itmo.kasymov.exceptions.BanksException;

public interface Observer {
    void subscribe() throws BanksException;

    void unsubscribe() throws BanksException;

    void update(Subject subject);
}
