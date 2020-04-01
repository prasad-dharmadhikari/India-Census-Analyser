package com.bridgelabz.censusanalyser.model;

import com.opencsv.bean.CsvBindByName;

public class StateCode {
    @CsvBindByName(column = "SrNo")
    public Integer srNo;

    @CsvBindByName(column = "State Name")
    public String stateName;

    @CsvBindByName(column = "TIN")
    public Integer tin;

    @CsvBindByName(column = "StateCode")
    public String stateCode;

    public StateCode() {

    }

    public StateCode(Integer srNo, String stateName, Integer tin, String stateCode) {
        this.srNo = srNo;
        this.stateName = stateName;
        this.tin = tin;
        this.stateCode = stateCode;
    }

    @Override
    public String toString() {
        return "StateCode{" +
                "srNo=" + srNo +
                ", stateName='" + stateName + '\'' +
                ", tin=" + tin +
                ", stateCode='" + stateCode + '\'' +
                '}';
    }
}
