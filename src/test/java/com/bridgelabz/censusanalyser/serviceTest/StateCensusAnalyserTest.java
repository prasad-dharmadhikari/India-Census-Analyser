package com.bridgelabz.censusanalyser.serviceTest;

import com.bridgelabz.censusanalyser.service.StateCensusAnalyser;
import com.opencsv.exceptions.CsvException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class StateCensusAnalyserTest
{
    private static final String PATH_OF_CSV_FILE = "./src/test/resources/StateCensusData.csv";
    @Test
    public void givenTheStateCensusCSVFile_WhenProper_CheckIfNoOfRecordsMatches() throws IOException, CsvException, IOException
    {
        int noOfRecords = StateCensusAnalyser.loadCSVFileData(PATH_OF_CSV_FILE);
        Assert.assertEquals(28,noOfRecords);
    }
}
