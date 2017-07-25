package com.example.developer.myapplication.Beans;

/**
 * Created by Developer on 23-09-2016.
 */

public class CityService {

    public CityService(String id, String name, String country) {
        Id = id;
        Name = name;
        Country = country;
    }

    String Id,Name,Country;

    public CityService() {
        Id = null;
        Name = null;
        Country = null;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }
}
