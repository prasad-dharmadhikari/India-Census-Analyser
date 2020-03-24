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
import java.util.Comparator;
import java.util.List;

import com.google.gson.Gson;

public class CensusAnalyser {
    ICSVBuilder csvBuilder = new CSVBuilderFactory().createCSVBuilder();
    List<CSVStateCensus> stateCensusRecords = null;
    List<StateCode> stateCodeRecords = null;

    public int loadStateCensusCSVFileData(String filePath) throws CSVBuilderException, IOException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            stateCensusRecords = csvBuilder.getCSVFileList(reader, CSVStateCensus.class);
            return stateCensusRecords.size();
        } catch (NoSuchFileException e) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.ENTERED_WRONG_FILE_NAME,
                    "FILE NAME IS INCORRECT");
        } catch (RuntimeException e) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.INCORRECT_DELIMITER_OR_HEADER,
                    "FILE DELIMITER OR HEADER IS INCORRECT");
        }
    }

    public int loadStateCodeCSVFileData(String filePath) throws IOException, CSVBuilderException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            stateCodeRecords = csvBuilder.getCSVFileList(reader, StateCode.class);
            return stateCodeRecords.size();
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
        if (stateCensusRecords == null || stateCensusRecords.size() == 0)
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.NO_CENSUS_DATA, "Data empty");

        Comparator<CSVStateCensus> censusCSVComparator = Comparator.comparing(csvStateCensus ->
                csvStateCensus.State);
        this.sort(censusCSVComparator);
        String sortedStateCensusJson = new Gson().toJson(stateCensusRecords);
        return sortedStateCensusJson;
    }

    public void sort(Comparator<CSVStateCensus> censusCSVComparator) {
        for (int iterate = 0; iterate < stateCensusRecords.size() - 1; iterate++) {
            for (int Inneriterate = 0; Inneriterate < stateCensusRecords.size() - iterate - 1; Inneriterate++) {
                CSVStateCensus census1 = stateCensusRecords.get(Inneriterate);
                CSVStateCensus census2 = stateCensusRecords.get(Inneriterate + 1);
                if (censusCSVComparator.compare(census1, census2) > 0) {
                    stateCensusRecords.set(Inneriterate, census2);
                    stateCensusRecords.set(Inneriterate + 1, census1);
                }
            }
        }
    }
}