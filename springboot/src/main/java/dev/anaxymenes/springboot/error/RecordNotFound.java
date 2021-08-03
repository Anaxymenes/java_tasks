package dev.anaxymenes.springboot.error;

public class RecordNotFound extends RuntimeException{
    public RecordNotFound(String message) {
        super(message);
    }
}
