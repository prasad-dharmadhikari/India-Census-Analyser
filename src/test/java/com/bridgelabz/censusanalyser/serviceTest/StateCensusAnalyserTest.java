package com.bridgelabz.censusanalyser.serviceTest;

import com.bridgelabz.censusanalyser.exception.CSVBuilderException;
import com.bridgelabz.censusanalyser.model.IndiaStateCensusCSV;
import com.bridgelabz.censusanalyser.model.USCensus;
import com.bridgelabz.censusanalyser.service.CensusAnalyser;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class StateCensusAnalyserTest {
    CensusAnalyser IndiaCensusAnalyser = new CensusAnalyser(CensusAnalyser.COUNTRY.INDIA);
    CensusAnalyser USCensusAnalyser = new CensusAnalyser(CensusAnalyser.COUNTRY.US);
    String PATH_OF_CSV_FILE = "./src/test/resources/StateCensusData.csv";
    String PATH_OF_CSV_FILE_FOR_FILE_NOT_FOUND_EXCEPTION = "./src/test/resources/StateCensu.csv";
    String PATH_OF_CSV_FILE_FOR_INCORRECT_TYPE_EXCEPTION = "/home/bridgelabz/Desktop/IndiaCensusAnalyser/src/test" +
            "/resources/StateCensusData.docx";
    String PATH_OF_CSV_FILE_FOR_INCORRECT_DELIMITER = "./src/test/resources/StateCensusDataCopy.csv";
    String PATH_OF_CSV_FILE_FOR_INCORRECT_HEADER = "./src/test/resources/StateCensusDataCopy2.csv";
    String PATH_OF_STATE_CODE_CSV_FILE = "./src/test/resources/StateCode.csv";
    String PATH_OF_STATE_CODE_CSV_FILE_FOR_FILE_NOT_FOUND = "./src/test/resources/Sta.csv";
    String PATH_OF_STATE_CODE_CSV_FILE_FOR_INCORRECT_TYPE = "/home/bridgelabz/Desktop/IndiaCensusAnalyser/src/test" +
            "/resources/IndiaStateCodeCSV.docx";
    String PATH_OF_STATE_CODE_CSV_FILE_INCORRECT_DELIMITER = "./src/test/resources/StateCodeCopy.csv";
    String PATH_OF_STATE_CODE_CSV_FILE_INCORRECT_HEADER = "./src/test/resources/StateCodeCopy2.csv";
    String PATH_OF_US_CENSUS_CSV_FILE = "./src/test/resources/USCensusData.csv";

    @Test
    public void givenTheStateCensusCSVFile_WhenProper_CheckIfNoOfRecordsMatches() throws IOException, CSVBuilderException {
        int noOfRecords = IndiaCensusAnalyser.loadCensusData(PATH_OF_CSV_FILE);
        Assert.assertEquals(28, noOfRecords);
    }

    @Test
    public void givenTheStateCensusCSVFile_IfIncorrect_ShouldThrowCustomException() throws IOException {
        try {
            IndiaCensusAnalyser.loadCensusData(PATH_OF_CSV_FILE_FOR_FILE_NOT_FOUND_EXCEPTION);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.ENTERED_WRONG_FILE_NAME, e.type);
        }
    }

    @Test
    public void givenTheStateCensusCSVFile_IfTypeIsIncorrect_ShouldThrowCustomException() throws CSVBuilderException, IOException {
        try {
            IndiaCensusAnalyser.getFileExtension(new File(PATH_OF_CSV_FILE_FOR_INCORRECT_TYPE_EXCEPTION));
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.ENTERED_WRONG_FILE_TYPE, e.type);
        }
    }

    @Test
    public void givenTheStateCensusCSVFile_IfDelimiterIsIncorrect_ShouldThrowCustomException() throws IOException, CSVBuilderException {
        try {
            int noOfRecords = IndiaCensusAnalyser.loadCensusData(PATH_OF_CSV_FILE_FOR_INCORRECT_DELIMITER);
            Assert.assertEquals(28, noOfRecords);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.INCORRECT_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenTheStateCensusCSVFile_IfHeaderIsIncorrect_ShouldThrowCustomException() throws IOException {
        try {
            int noOfRecords = IndiaCensusAnalyser.loadCensusData(PATH_OF_CSV_FILE_FOR_INCORRECT_HEADER);
            Assert.assertEquals(28, noOfRecords);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.INCORRECT_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenTheStateCodeCSVFile_WhenProper_CheckIfNoOfRecordsMatches() throws IOException, CSVBuilderException {
        int noOfRecords = IndiaCensusAnalyser.loadCensusData(PATH_OF_CSV_FILE, PATH_OF_STATE_CODE_CSV_FILE);
        Assert.assertEquals(37, noOfRecords);
    }

    @Test
    public void givenTheStateCodeCSVFile_IfIncorrect_ShouldThrowCustomException() throws IOException {
        try {
            int noOfRecords = IndiaCensusAnalyser.loadCensusData(PATH_OF_CSV_FILE,
                                                                 PATH_OF_STATE_CODE_CSV_FILE_FOR_FILE_NOT_FOUND);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.ENTERED_WRONG_FILE_NAME, e.type);
        }
    }

    @Test
    public void givenTheStateCodeCSVFile_IfTypeIsIncorrect_ShouldThrowCustomException() throws CSVBuilderException, IOException {
        try {
            IndiaCensusAnalyser.getFileExtension(new File(PATH_OF_STATE_CODE_CSV_FILE_FOR_INCORRECT_TYPE));
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.ENTERED_WRONG_FILE_TYPE, e.type);
        }
    }

    @Test
    public void givenTheStateCodeCSVFile_IfDelimiterIsIncorrect_ShouldThrowCustomException() throws IOException {
        try {
            int noOfRecords = IndiaCensusAnalyser.loadCensusData(PATH_OF_CSV_FILE,
                                                                 PATH_OF_STATE_CODE_CSV_FILE_INCORRECT_DELIMITER);
            Assert.assertEquals(37, noOfRecords);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.INCORRECT_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenTheStateCodeCSVFile_IfHeaderIsIncorrect_ShouldThrowCustomException() throws IOException {
        try {
            int noOfRecords = IndiaCensusAnalyser.loadCensusData(PATH_OF_CSV_FILE,
                                                                 PATH_OF_STATE_CODE_CSV_FILE_INCORRECT_HEADER);
            Assert.assertEquals(37, noOfRecords);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.INCORRECT_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenTheStateCensusCSVFile_WhenSortedOnState_ShouldReturnSortedList() throws IOException,
            CSVBuilderException {
        IndiaCensusAnalyser.loadCensusData(PATH_OF_CSV_FILE);
        String sortedCensusData = IndiaCensusAnalyser.getSortedCensusData(CensusAnalyser.SortingMode.STATE);
        IndiaStateCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaStateCensusCSV[].class);
        Assert.assertEquals("Andhra Pradesh", censusCSV[0].State);
        Assert.assertEquals("West Bengal", censusCSV[27].State);
    }

    @Test
    public void givenTheStateCodeCSVFile_WhenSortedOnStateCode_ShouldReturnSortedList() throws IOException, CSVBuilderException {
        IndiaCensusAnalyser.loadCensusData(PATH_OF_CSV_FILE, PATH_OF_STATE_CODE_CSV_FILE);
        String sortedStateCodeData = IndiaCensusAnalyser.getSortedCensusData(CensusAnalyser.SortingMode.STATECODE);
        IndiaStateCensusCSV[] censusCSV = new Gson().fromJson(sortedStateCodeData, IndiaStateCensusCSV[].class);
        Assert.assertEquals("AD", censusCSV[0].stateCode);
        Assert.assertEquals("WB", censusCSV[36].stateCode);
    }

    @Test
    public void givenTheStateCensusCSVFile_WhenSortedOnPopulation_ShouldReturnSortedList() throws IOException {
        try {
            IndiaCensusAnalyser.loadCensusData(PATH_OF_CSV_FILE);
            String sortedStatePopulationData = IndiaCensusAnalyser.getSortedCensusData(CensusAnalyser.SortingMode.POPULATION);
            IndiaStateCensusCSV[] censusCSV = new Gson().fromJson(sortedStatePopulationData, IndiaStateCensusCSV[].class);
            Assert.assertEquals("Uttar Pradesh", censusCSV[0].State);
            Assert.assertEquals("Sikkim", censusCSV[27].State);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenTheStateCensusCSVFile_WhenSortedOnPopulationDensity_ShouldReturnSortedList() throws IOException {
        try {
            IndiaCensusAnalyser.loadCensusData(PATH_OF_CSV_FILE);
            String sortedStatePopulationData = IndiaCensusAnalyser.getSortedCensusData(CensusAnalyser.SortingMode.DENSITY);
            IndiaStateCensusCSV[] censusCSV = new Gson().fromJson(sortedStatePopulationData, IndiaStateCensusCSV[].class);
            Assert.assertEquals("Bihar", censusCSV[0].State);
            Assert.assertEquals("Mizoram", censusCSV[27].State);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenTheStateCensusCSVFile_WhenSortedOnArea_ShouldReturnSortedList() throws IOException {
        try {
            IndiaCensusAnalyser.loadCensusData(PATH_OF_CSV_FILE);
            String sortedStatePopulationData = IndiaCensusAnalyser.getSortedCensusData(CensusAnalyser.SortingMode.AREA);
            IndiaStateCensusCSV[] censusCSV = new Gson().fromJson(sortedStatePopulationData, IndiaStateCensusCSV[].class);
            Assert.assertEquals("Rajasthan", censusCSV[0].State);
            Assert.assertEquals("Goa", censusCSV[27].State);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUSCensusCSVFile_WhenProper_CheckIfNoOfRecordsMatches() throws IOException, CSVBuilderException {
        int noOfRecords = USCensusAnalyser.loadCensusData(PATH_OF_US_CENSUS_CSV_FILE);
        Assert.assertEquals(51, noOfRecords);
    }

    @Test
    public void givenTheUSCensusCSVFile_WhenSortedOnPopulation_ShouldReturnSortedList() throws IOException {
        try {
            USCensusAnalyser.loadCensusData(PATH_OF_US_CENSUS_CSV_FILE);
            String sortedStatePopulationData = USCensusAnalyser.getSortedCensusData(CensusAnalyser.SortingMode.POPULATION);
            USCensus[] censusCSV = new Gson().fromJson(sortedStatePopulationData, USCensus[].class);
            Assert.assertEquals("California", censusCSV[0].state);
            Assert.assertEquals("Wyoming", censusCSV[50].state);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenTheUSCensusCSVFile_WhenSortedOnStateCode_ShouldReturnSortedList() throws IOException {
        try {
            USCensusAnalyser.loadCensusData(PATH_OF_US_CENSUS_CSV_FILE);
            String sortedStatePopulationData = USCensusAnalyser.getSortedCensusData(CensusAnalyser.SortingMode.STATECODE);
            USCensus[] censusCSV = new Gson().fromJson(sortedStatePopulationData, USCensus[].class);
            Assert.assertEquals("AK", censusCSV[0].stateID);
            Assert.assertEquals("WY", censusCSV[50].stateID);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenTheUSCensusCSVFile_WhenSortedOnArea_ShouldReturnSortedList() throws IOException {
        try {
            USCensusAnalyser.loadCensusData(PATH_OF_US_CENSUS_CSV_FILE);
            String sortedStatePopulationData = USCensusAnalyser.getSortedCensusData(CensusAnalyser.SortingMode.AREA);
            USCensus[] censusCSV = new Gson().fromJson(sortedStatePopulationData, USCensus[].class);
            Assert.assertEquals("Alaska", censusCSV[0].state);
            Assert.assertEquals("District of Columbia", censusCSV[50].state);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenTheUSCensusCSVFile_WhenSortedOnPopulationDensity_ShouldReturnSortedList() throws IOException {
        try {
            USCensusAnalyser.loadCensusData(PATH_OF_US_CENSUS_CSV_FILE);
            String sortedStatePopulationData = USCensusAnalyser.getSortedCensusData(CensusAnalyser.SortingMode.DENSITY);
            USCensus[] censusCSV = new Gson().fromJson(sortedStatePopulationData, USCensus[].class);
            Assert.assertEquals("District of Columbia", censusCSV[0].state);
            Assert.assertEquals("Alaska", censusCSV[50].state);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCensusCSVFile_WhenSortedPopulationAndDensity_ShouldReturnSortedList() throws IOException {
        try {
            IndiaCensusAnalyser.loadCensusData(PATH_OF_CSV_FILE);
            String sortedPopulationAndDensityData = IndiaCensusAnalyser.getDualSortByPopulationAndDensity();
            IndiaStateCensusCSV[] censusCSV = new Gson().fromJson(sortedPopulationAndDensityData, IndiaStateCensusCSV[].class);
            Assert.assertEquals("Uttar Pradesh", censusCSV[0].State);
            Assert.assertEquals("Sikkim", censusCSV[27].State);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUSCensusCSVFile_WhenSortedPopulationAndDensity_ShouldReturnSortedList() throws IOException {
        try {
            USCensusAnalyser.loadCensusData(PATH_OF_US_CENSUS_CSV_FILE);
            String sortedPopulationAndDensityData = USCensusAnalyser.getDualSortByPopulationAndDensity();
            USCensus[] censusCSV = new Gson().fromJson(sortedPopulationAndDensityData, USCensus[].class);
            Assert.assertEquals("California", censusCSV[0].state);
            Assert.assertEquals("Wyoming", censusCSV[50].state);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }
}
