package com.bridgelabz.censusanalyser.utility;

public class CSVBuilderFactory
{
    public ICSVBuilder createCSVBuilder()
    {
        return new CSVBuilder();
    }
}
