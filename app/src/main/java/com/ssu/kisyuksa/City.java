package com.ssu.kisyuksa;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.List;

//Each custom class must have a public constructor that takes no arguments. In addition, the class must include a public getter for each property.
/// 필드 잘 만들고 getter setter도 잘 만들어야 한다
public class City {


    private String name;
    private String state;
    private String country;
    private boolean capital;
    private long population;
    private List<String> regions;
    @ServerTimestamp private Timestamp timestamp; // server timestamp

    public City() {}

    public City(String name, String state, String country, boolean capital, long population, List<String> regions) {
        this.name = name;
        this.state = state;
        this.country = country;
        this.capital = capital;
        this.population = population;
        this.regions = regions;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public boolean isCapital() {
        return capital;
    }

    public long getPopulation() {
        return population;
    }

    public List<String> getRegions() {
        return regions;
    }

    public Timestamp getTimestamp() { return timestamp; }
}
