package com.bridgelabz.censusanalyser.dao;

import com.bridgelabz.censusanalyser.model.StateCode;

public class StateCodeDAO {

    public String stateCode;
    public int tin;
    public int srNo;
    public String state;

    public StateCodeDAO(StateCode stateCode) {
        this.state = stateCode.stateName;
        this.srNo = stateCode.srNo;
        this.tin = stateCode.tin;
        this.stateCode = stateCode.stateCode;
    }
}
