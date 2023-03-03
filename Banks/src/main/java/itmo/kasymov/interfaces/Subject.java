package itmo.kasymov.interfaces;

import itmo.kasymov.exceptions.BanksException;

public interface Subject {
    void attach(Observer observer) throws BanksException;
    void detach(Observer observer) throws BanksException;
    void notifyMe();
}

