package com.bridgelabz.censusanalyser.utility;

import com.bridgelabz.censusanalyser.dao.IndiaCensusDAO;
import com.bridgelabz.censusanalyser.exception.CSVBuilderException;
import com.bridgelabz.censusanalyser.model.CSVStateCensus;

import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;

public interface ICSVBuilder {
    public <E> Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CSVBuilderException;
}
