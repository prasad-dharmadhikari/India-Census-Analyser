package com.bridgelabz.censusanalyser.service;
import com.bridgelabz.censusanalyser.exception.CSVBuilderException;
import com.bridgelabz.censusanalyser.model.CSVStateCensus;
import com.bridgelabz.censusanalyser.model.StateCode;
import com.bridgelabz.censusanalyser.utility.CSVBuilderFactory;
import com.bridgelabz.censusanalyser.utility.ICSVBuilder;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

public class CensusAnalyser
{
    ICSVBuilder csvBuilder = new CSVBuilderFactory().createCSVBuilder();
    public int loadStateCensusCSVFileData(String filePath) throws CSVBuilderException, IOException
    {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath)))
        {
            List<CSVStateCensus> csvRecords = csvBuilder.getCSVFileList(reader , CSVStateCensus.class);
            return csvRecords.size();
        }
        catch (NoSuchFileException e)
        {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.ENTERED_WRONG_FILE_NAME,
                                              "FILE NAME IS INCORRECT");
        }
        catch (RuntimeException e)
        {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.INCORRECT_DELIMITER_OR_HEADER,
                                              "FILE DELIMITER OR HEADER IS INCORRECT");
        }
    }
    public int loadStateCodeCSVFileData(String filePath) throws IOException, CSVBuilderException
    {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath)))
        {
            List<CSVStateCensus> csvRecords = csvBuilder.getCSVFileList(reader, StateCode.class);
            return csvRecords.size();
        }
        catch (NoSuchFileException e)
        {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.ENTERED_WRONG_FILE_NAME,
                                              "FILE NAME IS INCORRECT");
        }
        catch (RuntimeException e)
        {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.INCORRECT_DELIMITER_OR_HEADER,
                                              "FILE DELIMITER OR HEADER IS INCORRECT");
        }
    }
    public static void getFileExtension(File filePath) throws CSVBuilderException
    {
        String fileName = filePath.getName();
        String extension = null;
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        {
            extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        if (!(extension.equals("csv")))
        {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.ENTERED_WRONG_FILE_TYPE,
                                             "FILE TYPE IS INCORRECT");
        }
    }
}