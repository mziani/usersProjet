package fr.mns.jee.projet.errors;

public class NotFoundException extends Exception {

    public NotFoundException(Long id) {
        super("Entity "+id+" not found");
    }
}
