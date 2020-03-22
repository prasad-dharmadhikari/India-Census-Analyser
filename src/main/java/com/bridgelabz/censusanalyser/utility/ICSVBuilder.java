package com.bridgelabz.censusanalyser.utility;

import com.bridgelabz.censusanalyser.exception.CSVBuilderException;
import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder
{
    public <E> Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CSVBuilderException;
    public <E> int getCount(Iterator<E> csvRecords);
}
