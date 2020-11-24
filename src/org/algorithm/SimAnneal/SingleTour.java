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

        this.tour = currentTour;
    }

    public List<City> getTour() {
        return this.tour;
    }

    public void generateIndividual(){
        for (int cityIndex=0; cityIndex < Repository.getNumberOfCities(); cityIndex++){
            setCity(cityIndex, Repository.getCity(cityIndex));
        }

        // shuffle to randomize the tour that we have initialized.
        Collections.shuffle(this.tour);
    }

    public void setCity(int index, City city){
        this.tour.set(index,city);

        // re-initialize the distance to zero
        this.distance = 0;
    }

    public City getCity(int tourPosition){
        return this.tour.get(tourPosition);
    }

    public int getTourSize(){
        return this.tour.size();
    }

    @Override
    public String toString(){
        String s = "";

        for (int i = 0; i < getTourSize(); i++){
            s += getCity(i) + " -> ";
        }

        return s;
    }

    public double getDistance(){
        if (distance==0){
            int tourDistance = 0;
            for (int cityIndex=0; cityIndex < getTourSize(); cityIndex++){
                City fromCity = getCity(cityIndex);

                City toCity;
                // decide the destination city
                if (cityIndex < getTourSize()-1){
                    toCity = getCity(cityIndex + 1);
                }
                else {
                    toCity = getCity(0);
                }

                tourDistance += fromCity.distanceTo(toCity);
            }
            this.distance = tourDistance;
        }
        return this.distance;
    }
    
}
