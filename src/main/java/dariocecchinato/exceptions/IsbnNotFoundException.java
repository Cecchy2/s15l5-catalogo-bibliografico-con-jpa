package dariocecchinato.exceptions;

public class IsbnNotFoundException extends RuntimeException {
    public IsbnNotFoundException(String id) {
        super("Il record con id " + id + " non è stato trovato!");
    }
}
