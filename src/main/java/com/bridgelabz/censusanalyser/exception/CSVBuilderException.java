package com.bridgelabz.censusanalyser.exception;

public class CSVBuilderException extends Exception {
    public enum ExceptionType {
        ENTERED_WRONG_FILE_NAME, ENTERED_WRONG_FILE_TYPE,
        INCORRECT_DELIMITER_OR_HEADER, UNABLE_TO_PARSE, NO_CENSUS_DATA;
    }
    public ExceptionType type;
    public CSVBuilderException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }
}
