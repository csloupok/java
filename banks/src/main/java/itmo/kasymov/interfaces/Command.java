package itmo.kasymov.interfaces;

import itmo.kasymov.exceptions.BanksException;

public interface Command {
    void execute() throws BanksException;
}
