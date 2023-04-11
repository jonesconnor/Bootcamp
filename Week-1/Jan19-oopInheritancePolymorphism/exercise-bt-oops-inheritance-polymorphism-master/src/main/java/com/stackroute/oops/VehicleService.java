package com.stackroute.oops;
import java.lang.Math;

public class VehicleService {
	
    /*
    create a Car object and return it
     */
    public Car createCar(String name, String modelName, String type) {
    	Car clownCar = new Car(name, modelName, type);
        return clownCar;
    }

    /*
    create a bike object and return it
     */
    public Bike createBike(String name, String modelName, String type) {
    	Bike chopper = new Bike(name, modelName, type);
        return chopper;
    }

    /*
    Method should compare the speed only if the vehicle is of "SPORTS" type.
    Method should return 0 when speeds are equal otherwise should return maximum vehicle speed.
    Method returns -1 if the type is not "SPORTS"
    */
    public int compareMaxSpeed(Vehicle first, Vehicle second) {
        /*
        Vehicle objects should be downcasted to their respective concrete types
        */
    	if (first.getType().equals("sports") && second.getType().equals("sports")) {
    		if (first.maxSpeed(first.getType()) == second.maxSpeed(second.getType())) {
    			return 0;
    		}
    		return Math.max(first.maxSpeed(first.getType()), second.maxSpeed(second.getType()));
    	}
    	
        return -1;
    }
}
