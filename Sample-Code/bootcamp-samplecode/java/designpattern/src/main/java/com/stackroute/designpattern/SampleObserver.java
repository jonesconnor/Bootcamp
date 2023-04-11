package com.stackroute.designpattern;

import java.util.ArrayList;
import java.util.List;

abstract class Observer
{
	InPatient patient;
	abstract void dischargeSummary();
}

class InPatient
{
	String patientId;
	float charges;
	List<Observer> observers=new ArrayList();
	InPatient(String patid, float deposit)
	{
		this.patientId=patid;
		this.charges=deposit;
	}
	
	float discharge(float overallfees)
	{	
		this.charges=overallfees-charges;
		patientNotifyAll();
		
		return this.charges;
	}
	
	public void add(Observer obs)
	{
		this.observers.add(obs);
	}
	
	public void patientNotifyAll()
	{
	for (Observer obj : observers)
	{
		obj.dischargeSummary();
	}
	}

}


class EmailObs extends Observer
{

	EmailObs(InPatient patient)
	{
		this.patient=patient;
		this.patient.add(this);
		
	}
	
	
	@Override
	void dischargeSummary() {
	System.out.println("Mailing : discharge summary : date  : 24th jan, subj : Discharge " + this.patient.patientId );
		
	}
	
}

class SmsObs extends Observer
{

		SmsObs(InPatient patient)
		{
			this.patient=patient;
			this.patient.add(this);
		}
	
	@Override
	void dischargeSummary() {
		System.out.println("Message : 24th jan - Phone number :112223333  Patient discharged " +  this.patient.patientId );
		
	}
	
}





public class SampleObserver {

	public static void main(String[] args) {
		InPatient patient=new InPatient("Ruban",5000);
   
   new EmailObs(patient);
   new SmsObs(patient);
   patient.discharge(15000);

	}

}
