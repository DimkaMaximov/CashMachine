package project.command;

import project.exception.InterruptOperationException;

interface Command {
    void execute() throws InterruptOperationException;
}
