package com.bridgelabz.censusanalyser.utility;

import com.bridgelabz.censusanalyser.exception.CSVBuilderException;
import java.io.Reader;
import java.util.List;

public interface ICSVBuilder {
    public <E> List<E> getCSVFileList(Reader reader, Class csvClass) throws CSVBuilderException;
}
