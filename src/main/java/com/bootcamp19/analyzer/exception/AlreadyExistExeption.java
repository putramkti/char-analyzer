package com.bootcamp19.analyzer.exception;

public class AlreadyExistExeption extends RuntimeException {
    public AlreadyExistExeption(String kalimat) {
        super("Kalimat " + kalimat + " already exists");
    }
}
