package com.bootcamp19.analyzer.exception;

public class QuoteUnavailableException extends RuntimeException {
    public QuoteUnavailableException(String message) {
        super(message);
    }
}
