package dariocecchinato.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String id) {
        super("Il record con id " + id + " non è stato trovato!");
    }
}
