package com.bridgelabz.censusanalyser.adapter;

import com.bridgelabz.censusanalyser.dao.CensusDAO;
import com.bridgelabz.censusanalyser.exception.CSVBuilderException;
import com.bridgelabz.censusanalyser.model.USCensus;
import com.bridgelabz.censusanalyser.utility.CSVBuilderFactory;
import com.bridgelabz.censusanalyser.utility.ICSVBuilder;

import java.io.IOException;
import java.util.HashMap;

public class USCensusAdapter extends CensusAdapter {

    @Override
    public HashMap<Integer, CensusDAO> loadCensusData(String... csvFilePath) throws CSVBuilderException, IOException {
        return super.loadCensusData(USCensus.class, csvFilePath[0]);
    }
}
