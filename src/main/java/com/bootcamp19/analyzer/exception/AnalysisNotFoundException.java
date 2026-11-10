package com.bootcamp19.analyzer.exception;

public class AnalysisNotFoundException extends RuntimeException {
    public AnalysisNotFoundException(Long id) {
        super("Data dengan id "+id+" tidak ditemukan");
    }
}
