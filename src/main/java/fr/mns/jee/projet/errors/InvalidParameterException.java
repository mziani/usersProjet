package fr.mns.jee.projet.errors;

public class InvalidParameterException extends Exception {

    public InvalidParameterException(String params) {
        super("Invalid parameters " + params);
    }
}
