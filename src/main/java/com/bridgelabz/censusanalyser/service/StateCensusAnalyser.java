package com.bridgelabz.censusanalyser.service;
import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyser.model.CSVStateCensus;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateCensusAnalyser
{
    public static int loadCSVFileData(String filePath) throws CensusAnalyserException, IOException
    {
        int noOfRecords = 0;
        try(Reader reader = Files.newBufferedReader(Paths.get(filePath)))
        {
            CSVReader csvReader = new CSVReader(reader);
            CsvToBean<CSVStateCensus> csvToBean = new CsvToBeanBuilder<CSVStateCensus>(csvReader).
                    withType(CSVStateCensus.class).build();
            Iterator<CSVStateCensus> csvRecords = csvToBean.iterator();
            while (csvRecords.hasNext())
            {
                noOfRecords++;
                csvRecords.next();
            }
        }
        catch (NoSuchFileException e)
        {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.ENTERED_WRONG_FILE_NAME,"FILE NAME IS INCORRECT");
        }
        catch (RuntimeException e)
        {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.INCORRECT_DELIMITER,"FILE DELIMITER IS INCORRECT");
        }
        return noOfRecords;
    }
    public static void getFileExtension(File filePath) throws CensusAnalyserException
    {
        String fileName = filePath.getName();
        String extension = null;
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        {
            extension = fileName.substring(fileName.lastIndexOf(".")+1);
        }
        if (!(extension.equals("csv")))
        {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.ENTERED_WRONG_FILE_TYPE,"FILE TYPE IS INCORRECT");
        }

    }
}
