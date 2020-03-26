package com.bridgelabz.censusanalyser.dao;

import com.bridgelabz.censusanalyser.model.CSVStateCensus;
import com.bridgelabz.censusanalyser.model.StateCode;

public class CSVStateCensusDAO {
    public String state;
    public long population;
    public long AreaInSqKm;
    public int DensityPerSqkm;

    public CSVStateCensusDAO(CSVStateCensus csvStateCensus) {
        this.state = csvStateCensus.State;
        this.population = csvStateCensus.Population;
        AreaInSqKm = csvStateCensus.AreaInSqKm;
        DensityPerSqkm = csvStateCensus.DensityPerSqkm;
    }

}
