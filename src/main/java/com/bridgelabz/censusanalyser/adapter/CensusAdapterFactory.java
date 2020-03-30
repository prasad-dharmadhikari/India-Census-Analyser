package com.bridgelabz.censusanalyser.adapter;

import com.bridgelabz.censusanalyser.service.CensusAnalyser;

public class CensusAdapterFactory {
    public static CensusAdapter getCensusData(CensusAnalyser.COUNTRY country) {
        if (country.equals(CensusAnalyser.COUNTRY.INDIA))
            return new IndiaCensusAdapter();
        if (country.equals(CensusAnalyser.COUNTRY.US))
            return new USCensusAdapter();
        return null;
    }
}
