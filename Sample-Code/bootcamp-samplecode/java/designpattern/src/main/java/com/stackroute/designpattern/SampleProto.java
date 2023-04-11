package com.stackroute.designpattern;

import java.util.ArrayList;
import java.util.List;

class PrimaryIllness implements Cloneable 
{
	
	List<String> ailments=new ArrayList();
	
	PrimaryIllness()
	{}
	
	public PrimaryIllness(List<String> ailments)
	{
		this.ailments=ailments;
	}
	
	public void setAilments()
	{
		ailments.add("Cataract");
		ailments.add("stone");
	}
	

	public List<String> getAilments()
	{
		return ailments;
	}
	
	protected Object clone() throws CloneNotSupportedException 
	{
		
		List<String> temp=new ArrayList();
		
		for(String ailment : ailments)
			temp.add(ailment);
		
			
		return new PrimaryIllness(temp);
		
	}
	
	
}


public class SampleProto {

	public static void main(String[] args) throws CloneNotSupportedException {
	
		PrimaryIllness primary=new PrimaryIllness();
		primary.setAilments();
		
		PrimaryIllness operationobj= (PrimaryIllness) primary.clone();
		List<String> ailments=operationobj.getAilments();
			ailments.add("Bone");
		System.out.println(operationobj.getAilments());
		
		
		PrimaryIllness daycareobj= (PrimaryIllness) primary.clone();
				
		
				List<String> ailments1=daycareobj.getAilments();
				
				ailments1.add("Sinus");
				ailments1.add("Tonsils");
			System.out.println(daycareobj.getAilments());

	}

}
