package com.bridgelabz.censusanalyser.serviceTest;

import com.bridgelabz.censusanalyser.exception.CSVBuilderException;
import com.bridgelabz.censusanalyser.model.CSVStateCensus;
import com.bridgelabz.censusanalyser.service.CensusAnalyser;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import java.io.File;
import java.io.IOException;

public class StateCensusAnalyserTest
{
    CensusAnalyser censusAnalyser = new CensusAnalyser();
    String PATH_OF_CSV_FILE = "./src/test/resources/StateCensusData.csv";
    String PATH_OF_CSV_FILE_FOR_FILE_NOT_FOUND_EXCEPTION = "./src/test/resources/StateCensu.csv";
    String PATH_OF_CSV_FILE_FOR_INCORRECT_TYPE_EXCEPTION = "/home/bridgelabz/Desktop/IndiaCensusAnalyser/src/test/resources/StateCensusData.docx";
    String PATH_OF_CSV_FILE_FOR_INCORRECT_DELIMITER = "./src/test/resources/StateCensusDataCopy.csv";
    String PATH_OF_CSV_FILE_FOR_INCORRECT_HEADER = "./src/test/resources/StateCensusDataCopy2.csv";
    String PATH_OF_STATE_CODE_CSV_FILE = "./src/test/resources/StateCode.csv";
    String PATH_OF_STATE_CODE_CSV_FILE_FOR_FILE_NOT_FOUND = "./src/test/resources/Sta.csv";
    String PATH_OF_STATE_CODE_CSV_FILE_FOR_INCORRECT_TYPE = "/home/bridgelabz/Desktop/IndiaCensusAnalyser/src/test/resources/StateCode.docx";
    String PATH_OF_STATE_CODE_CSV_FILE_INCORRECT_DELIMITER = "./src/test/resources/StateCodeCopy.csv";
    String PATH_OF_STATE_CODE_CSV_FILE_INCORRECT_HEADER = "./src/test/resources/StateCodeCopy2.csv";
    @Test
    public void givenTheStateCensusCSVFile_WhenProper_CheckIfNoOfRecordsMatches() throws IOException, CSVBuilderException
    {
        int noOfRecords = censusAnalyser.loadStateCensusCSVFileData(PATH_OF_CSV_FILE);
        Assert.assertEquals(28,noOfRecords);
    }
    @Test
    public void givenTheStateCensusCSVFile_IfIncorrect_ShouldThrowCustomException() throws IOException
    {
        try
        {
            censusAnalyser.loadStateCensusCSVFileData(PATH_OF_CSV_FILE_FOR_FILE_NOT_FOUND_EXCEPTION);
        }
        catch (CSVBuilderException e)
        {
            Assert.assertEquals(CSVBuilderException.ExceptionType.ENTERED_WRONG_FILE_NAME,e.type);
        }
    }
    @Test
    public void givenTheStateCensusCSVFile_IfTypeIsIncorrect_ShouldThrowCustomException() throws CSVBuilderException, IOException
    {
        try
        {
            censusAnalyser.getFileExtension(new File(PATH_OF_CSV_FILE_FOR_INCORRECT_TYPE_EXCEPTION));
        }
        catch (CSVBuilderException e)
        {
            Assert.assertEquals(CSVBuilderException.ExceptionType.ENTERED_WRONG_FILE_TYPE,e.type);
        }
    }
    @Test
    public void givenTheStateCensusCSVFile_IfDelimiterIsIncorrect_ShouldThrowCustomException() throws IOException, CSVBuilderException
    {
        try
        {
            int noOfRecords = censusAnalyser.loadStateCensusCSVFileData(PATH_OF_CSV_FILE_FOR_INCORRECT_DELIMITER);
            Assert.assertEquals(28,noOfRecords);
        }
        catch (CSVBuilderException e)
        {
            Assert.assertEquals(CSVBuilderException.ExceptionType.INCORRECT_DELIMITER_OR_HEADER,e.type);
        }
    }
    @Test
    public void givenTheStateCensusCSVFile_IfHeaderIsIncorrect_ShouldThrowCustomException() throws IOException
    {
        try
        {
            int noOfRecords = censusAnalyser.loadStateCensusCSVFileData(PATH_OF_CSV_FILE_FOR_INCORRECT_HEADER);
            Assert.assertEquals(28,noOfRecords);
        }
        catch (CSVBuilderException e)
        {
            Assert.assertEquals(CSVBuilderException.ExceptionType.INCORRECT_DELIMITER_OR_HEADER,e.type);
        }
    }
    @Test
    public void givenTheStateCodeCSVFile_WhenProper_CheckIfNoOfRecordsMatches() throws IOException, CSVBuilderException
    {
        int noOfRecords = censusAnalyser.loadStateCodeCSVFileData(PATH_OF_STATE_CODE_CSV_FILE);
        Assert.assertEquals(37,noOfRecords);
    }
    @Test
    public void givenTheStateCodeCSVFile_IfIncorrect_ShouldThrowCustomException() throws IOException
    {
        try
        {
            int noOfRecords = censusAnalyser.loadStateCodeCSVFileData(PATH_OF_STATE_CODE_CSV_FILE_FOR_FILE_NOT_FOUND);
        }
        catch (CSVBuilderException e)
        {
            Assert.assertEquals(CSVBuilderException.ExceptionType.ENTERED_WRONG_FILE_NAME,e.type);
        }
    }
    @Test
    public void givenTheStateCodeCSVFile_IfTypeIsIncorrect_ShouldThrowCustomException() throws CSVBuilderException, IOException
    {
        try
        {
            censusAnalyser.getFileExtension(new File(PATH_OF_STATE_CODE_CSV_FILE_FOR_INCORRECT_TYPE));
        }
        catch (CSVBuilderException e)
        {
            Assert.assertEquals(CSVBuilderException.ExceptionType.ENTERED_WRONG_FILE_TYPE,e.type);
        }
    }
    @Test
    public void givenTheStateCodeCSVFile_IfDelimiterIsIncorrect_ShouldThrowCustomException() throws IOException
    {
        try
        {
            int noOfRecords = censusAnalyser.loadStateCodeCSVFileData(PATH_OF_STATE_CODE_CSV_FILE_INCORRECT_DELIMITER);
            Assert.assertEquals(37,noOfRecords);
        }
        catch (CSVBuilderException e)
        {
            Assert.assertEquals(CSVBuilderException.ExceptionType.INCORRECT_DELIMITER_OR_HEADER,e.type);
        }
    }
    @Test
    public void givenTheStateCodeCSVFile_IfHeaderIsIncorrect_ShouldThrowCustomException() throws IOException
    {
        try
        {
            int noOfRecords = censusAnalyser.loadStateCodeCSVFileData(PATH_OF_STATE_CODE_CSV_FILE_INCORRECT_HEADER);
            Assert.assertEquals(37,noOfRecords);
        }
        catch (CSVBuilderException e)
        {
            Assert.assertEquals(CSVBuilderException.ExceptionType.INCORRECT_DELIMITER_OR_HEADER,e.type);
        }
    }
    @Test
    public void givenTheStateCensusCSVFile_WhenSortedOnState_ShouldReturnSortedList() throws IOException,
                                                                                             CSVBuilderException
    {
        censusAnalyser.loadStateCensusCSVFileData(PATH_OF_CSV_FILE);
        String sortedCensusData = censusAnalyser.getStateWiseSortedData();
        CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
        Assert.assertEquals("Andhra Pradesh", censusCSV[0].getState());
        Assert.assertEquals("West Bengal", censusCSV[27].getState());
    }
}
