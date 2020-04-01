package com.bridgelabz.censusanalyser.service;

import com.bridgelabz.censusanalyser.adapter.CensusAdapter;
import com.bridgelabz.censusanalyser.adapter.CensusAdapterFactory;
import com.bridgelabz.censusanalyser.dao.CensusDAO;
import com.bridgelabz.censusanalyser.exception.CSVBuilderException;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.google.gson.Gson;

public class CensusAnalyser {
    HashMap<Integer, CensusDAO> censusHashMap = new HashMap<Integer, CensusDAO>();
    public enum COUNTRY {INDIA, US}

    public enum SortingMode {AREA, STATE, STATECODE, DENSITY, POPULATION}

    private COUNTRY country;

    public CensusAnalyser(COUNTRY country) {
        this.country = country;
    }

    public int loadCensusData(String... filePath) throws IOException, CSVBuilderException {
        CensusAdapter censusDataLoader = CensusAdapterFactory.getCensusData(country);
        censusHashMap = censusDataLoader.loadCensusData(filePath);
        return censusHashMap.size();
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

    public String getSortedCensusData(SortingMode mode) throws CSVBuilderException {
        if (censusHashMap == null || censusHashMap.size() == 0)
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.NO_CENSUS_DATA, "Data empty");
        ArrayList censusDTO = censusHashMap.values().stream()
                .sorted(CensusDAO.getSortComparator(mode))
                .map(censusDAO -> censusDAO.getCensusDTO(country))
                .collect(Collectors.toCollection(ArrayList::new));
        return new Gson().toJson(censusDTO);
    }
}