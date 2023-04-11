package com.stackroute.oops;

public class Bike extends AbstractManufacturer implements Vehicle {
    public Bike(String name, String modelName, String bikeType) {
    	this.name = name;
    	this.modelName = modelName;
    	this.type = bikeType;
    }

    /*
   Method returns maximum speed depending upon their types
   For Sports-300kmh
   For cruiser-170kmh
    */
    @Override
    public int maxSpeed(String bikeType) {
    	if (bikeType.equals("sports")) {
    		return 300;
    	}
        return 170;
    }
    
    @Override
    public String getType() {
    	return type;
    }

    /*
    should return in the format : Bike{Manufacturer name:'name',Model Name:'modelName',Type:'type'}
     */
    @Override
    public String getManufacturerInformation() {
//        return "Bike{Manufacturer name: '" + getName() + "',Model Name:'" + getModelName() + "',Type:'" + getType() + "'}";
    	return "Bike{Manufacturer name:" + getName() + ",Model Name:" + getModelName() + ",Type:" + getType() + "}";
    }
}
