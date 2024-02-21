package net.ausiasmarch.mobibus.exception;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String msg) {
        super(" \"ERROR: Intent d'accés no autoritzat: " + msg);
    }

}