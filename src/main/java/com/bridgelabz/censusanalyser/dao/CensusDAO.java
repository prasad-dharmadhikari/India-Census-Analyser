package com.bridgelabz.censusanalyser.dao;

import com.bridgelabz.censusanalyser.model.CSVStateCensus;
import com.bridgelabz.censusanalyser.model.StateCode;
import com.bridgelabz.censusanalyser.model.USCensus;

public class CensusDAO {

    public String state;
    public long population;
    public long AreaInSqKm;
    public int DensityPerSqkm;
    public double area;
    public float density;
    public String stateCode;
    public int tin;
    public int srNo;

    public CensusDAO(StateCode stateCode) {
        this.state = stateCode.stateName;
        this.srNo = stateCode.srNo;
        this.tin = stateCode.tin;
        this.stateCode = stateCode.stateCode;
    }

    public CensusDAO(CSVStateCensus csvStateCensus) {
        this.state = csvStateCensus.State;
        this.population = csvStateCensus.Population;
        this.AreaInSqKm = csvStateCensus.AreaInSqKm;
        this.DensityPerSqkm = csvStateCensus.DensityPerSqkm;
    }

    public CensusDAO(USCensus usCensus) {
        this.state = usCensus.state;
        this.population = usCensus.population;
        area = usCensus.area;
        density = usCensus.populationDensity;
        this.stateCode = usCensus.stateID;
    }

}
