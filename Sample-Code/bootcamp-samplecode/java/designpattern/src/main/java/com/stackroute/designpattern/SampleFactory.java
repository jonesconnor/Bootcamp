package com.stackroute.designpattern;

interface iRoomOccupancy
{
  float	showCharges();
	
}

class Daycare implements iRoomOccupancy
{

	public float showCharges() {
		
		return 100.50f;
	}
	   
}

class SingleRoom implements iRoomOccupancy
{

	public float showCharges() {
		 
		return 750f;
	}
	
	
}

class RoomFactoryProducer
{
	static  iRoomOccupancy getRoomData(String type)
	{
		if (type.equalsIgnoreCase("daycare"))
					return new Daycare();
		else 
			return new SingleRoom();
	}
	

}

public class SampleFactory {

	public static void main(String[] args) {
							//	new RoomFactoryprodcuer().getroomdata();
								
		iRoomOccupancy roomobj=RoomFactoryProducer.getRoomData("daycare");
		System.out.println("Daycare charges are " + roomobj.showCharges());

	}

}








