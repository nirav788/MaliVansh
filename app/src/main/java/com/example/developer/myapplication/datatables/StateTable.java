package com.example.developer.myapplication.datatables;

/**
 * Created by Developer on 19-09-2016.
 */
public class StateTable {

    String tId;

    String AllInOne;

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public String getAllInOne() {
        return AllInOne;
    }

    public void setAllInOne(String allInOne) {
        AllInOne = allInOne;
    }

    public StateTable() {
        this.tId = null;
        this.AllInOne = null;
    }

    public StateTable(String tId, String allInOne) {
        this.tId = tId;
        AllInOne = allInOne;
    }
}