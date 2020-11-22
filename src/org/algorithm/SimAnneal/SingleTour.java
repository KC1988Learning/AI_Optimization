package org.algorithm.SimAnneal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SingleTour {
    private List<City> tour = new ArrayList<>();
    private double distance = 0;

    public SingleTour(){
        for (int i = 0 ; i < Repository.getNumberOfCities(); i++){
            tour.add(null);
        }
    }

    public SingleTour(List<City> tour){
        List<City> currentTour = new ArrayList<>();

        for (int i = 0; i < tour.size(); i++){
            currentTour.add(null);
        }

        for (int i = 0; i < tour.size(); i++){
            currentTour.set(i, tour.get(i));
        }
    }

    public List<City> getTour() {
        return tour;
    }

    public void generateIndividual(){
       for (int cityIndex=0; cityIndex < Repository.getNumberOfCities(); cityIndex++){
           setCity(cityIndex, Repository.getCity(cityIndex));
       }

        Collections.shuffle(tour);
    }

    private void setCity(int cityIndex, City city){
        this.tour.set(cityIndex, city);
        this.distance = 0;
    }
    
}
