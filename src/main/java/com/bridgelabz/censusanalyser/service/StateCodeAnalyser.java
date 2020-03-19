package com.bridgelabz.censusanalyser.service;

import com.bridgelabz.censusanalyser.model.CSVStateCensus;
import com.bridgelabz.censusanalyser.model.StateCode;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateCodeAnalyser
{
    public static int loadCSVFileData(String filePath) throws IOException
    {
        int noOfRecords = 0;
        try(Reader reader = Files.newBufferedReader(Paths.get(filePath)))
        {
            CSVReader csvReader = new CSVReader(reader);
            CsvToBean<StateCode> csvToBean = new CsvToBeanBuilder<StateCode>(csvReader).
                    withType(StateCode.class).build();
            Iterator<StateCode> csvRecords = csvToBean.iterator();
            while (csvRecords.hasNext())
            {
                noOfRecords++;
                csvRecords.next();
            }
            return noOfRecords;
        }
    }
}
