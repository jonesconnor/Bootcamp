package com.stackroute.javaday6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import com.stackroute.javaday6.model.AccountHolder;
import com.stackroute.javaday6.service.AccountService;
import com.stackroute.javaday6.service.iService;

public class SampleArrayList {

	public static void main(String arg[])
	{
        
          
         iService serviceobj=AccountService.getAccountService();
         
         Scanner scan=new Scanner(System.in);
         int flag=0;
//         int ans=scan.nextInt();
         int	 ans;
          
         while(flag==0)
         {
        	   System.out.println(" 1-add, 2-dele, 3-view");
       ans=scan.nextInt();
        	 switch(ans)
        	 {
        	 case 1:
        		  serviceobj.addAccountHolder(new AccountHolder(20,"Paul",6700));
        		  break;
        	 case 2:
        		 System.out.println("enter id to be deleted");
        		 int id=scan.nextInt();
        		 	serviceobj.deleteHolder(id);
        		 	break;
        	 case 3:
        		 
        		  System.out.println(serviceobj.getAccountHolders());
        		  break;
        	 }
        	 System.out.println("Want to continue 1-no 0-yes");
        	 flag=scan.nextInt();
         }
		
		
	}
}
