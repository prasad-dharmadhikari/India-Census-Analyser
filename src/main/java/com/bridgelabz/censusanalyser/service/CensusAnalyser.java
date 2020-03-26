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
import java.util.*;

import com.google.gson.Gson;

public class CensusAnalyser {
    ICSVBuilder csvBuilder = new CSVBuilderFactory().createCSVBuilder();
    Collection<Object> censusRecords = null;
    HashMap<Object, Object> censusHashMap = null;

    public int loadCensusData(String filePath , Class csvClass) throws IOException, CSVBuilderException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            censusHashMap = csvBuilder.getCSVFileMap(reader, csvClass);
            return censusHashMap.size();
        } catch (NoSuchFileException e) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.ENTERED_WRONG_FILE_NAME,
                    "FILE NAME IS INCORRECT");
        } catch (RuntimeException e) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.INCORRECT_DELIMITER_OR_HEADER,
                    "FILE DELIMITER OR HEADER IS INCORRECT");
        }
    }

    public static void getFileExtension(File filePath) throws CSVBuilderException {
        String fileName = filePath.getName();
        String extension = null;
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        if (!(extension.equals("csv"))) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.ENTERED_WRONG_FILE_TYPE,
                    "FILE TYPE IS INCORRECT");
        }
    }

    public String getStateWiseSortedData() throws CSVBuilderException {
        if (censusHashMap == null || censusHashMap.size() == 0)
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.NO_CENSUS_DATA, "Data empty");
        Comparator<CSVStateCensus> censusCSVComparator = Comparator.comparing(csvStateCensus -> csvStateCensus.State);
        this.sort(censusCSVComparator , censusHashMap);
        censusRecords = censusHashMap.values();
        String sortedStateCensusJson = new Gson().toJson(censusRecords);
        return sortedStateCensusJson;
    }

    public String getStateCodeWiseSortedData() throws CSVBuilderException {
        if (censusHashMap == null || censusHashMap.size() == 0)
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.NO_CENSUS_DATA, "Data empty");
        Comparator<StateCode> stateCodeCSVComparator = Comparator.comparing(stateCode -> stateCode.stateCode);
        this.sort(stateCodeCSVComparator , censusHashMap);
        censusRecords = censusHashMap.values();
        String sortedStateCodeJson = new Gson().toJson(censusRecords);
        return sortedStateCodeJson;
    }

    public <E> void sort(Comparator<E> censusCSVComparator , HashMap<Object, Object> censusRecords) {
        for (int iterate = 0; iterate < censusRecords.size() - 1; iterate++) {
            for (int Inneriterate = 0; Inneriterate < censusRecords.size() - iterate - 1; Inneriterate++) {
                E census1 = (E) censusRecords.get(Inneriterate);
                E census2 = (E) censusRecords.get(Inneriterate + 1);
                if (censusCSVComparator.compare(census1, census2) > 0) {
                    censusRecords.put(Inneriterate, census2);
                    censusRecords.put(Inneriterate + 1, census1);
                }
            }
        }
    }
}