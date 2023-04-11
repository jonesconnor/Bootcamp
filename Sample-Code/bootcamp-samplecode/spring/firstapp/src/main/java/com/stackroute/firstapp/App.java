package com.stackroute.firstapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
         ApplicationContext appcontext=new ClassPathXmlApplicationContext("mybeans.xml");
         
         
         // Product pboj=new Product();
//         Product pobj=appcontext.getBean("productbean",Product.class);
//         System.out.println(pobj.getProductName());
//         
         
//         
//         Customer customer=appcontext.getBean("customerbean",Customer.class);
//         
//         System.out.println("customer name " + customer.getCustomerName() + " Product " + customer.getProduct().getProductName());
//    
    
    
//         
//         Customer customer2=appcontext.getBean("customer2",Customer.class);
//         customer2.getProduct().setProductName("Lpatop");
//         System.out.println("customer name " + customer2.getCustomerName() + "Mobiel " + customer2.getMobile() + " Product " + customer2.getProduct().getProductName());
//     
         
         
         Billing bill=appcontext.getBean("billbean",Billing.class);
         
         System.out.println(bill.getFeedback().get("g"));
         System.out.println("bill date " + bill.getBilldate() + " Product " + bill.getProductbean().getProductName());
     
         
         
    
    }
}
