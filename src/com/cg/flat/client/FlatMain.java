package com.cg.flat.client;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.cg.flat.exception.CustomerException;
import com.cg.flat.service.CustomerServiceImpl;
import com.cg.flat.entity.CustomerEntity;
import com.cg.flat.entity.FlatEntity;
import com.cg.flat.exception.CustomerException;
import com.cg.flat.service.CustomerService;
import com.cg.flat.service.CustomerServiceImpl;

public class FlatMain {
	
	static Scanner scanner=new Scanner(System.in);
	static CustomerServiceImpl customerServiceImpl=null;
	static	CustomerService customerService=null;
	
public static void main(String[] args) throws CustomerException, ClassNotFoundException, FileNotFoundException, SQLException {
	
	CustomerEntity customerEntity=null;
	FlatEntity flatEntity=null;
	
	//String cusId=null;
	String cusFlatId=null;
	String cusNum=null;
	
	int opt=0;
	while(true)
	{
		
		System.out.println("Menu");
		System.out.println("1-It Displays available Flats");
		System.out.println("2-Book your flat by entering your details");
		System.out.println("3-view Customer by customer number");
		System.out.println("4-It Displays Flat Details you have booked");
		System.out.println("5-exit");
		System.out.println("select an option");

		
	try
	{
		opt=scanner.nextInt();
		switch(opt) {
		
		case 1: 
			String type=null;
			
				try
				{
					customerServiceImpl=new CustomerServiceImpl();
					
					List<FlatEntity> li=new ArrayList<FlatEntity>();
					li=customerServiceImpl.viewFlatDetails(flatEntity);
					
					System.out.println("\n"+li+"\n");
				}
				catch(CustomerException ce) {
					
					System.err.println("Error:"+ce.getMessage());
				}
			 
				System.out.println(flatEntity);
			
			
			
			break;
	
		
		case 2:
			String custId =null;
			customerEntity = new CustomerEntity();
			customerServiceImpl = new CustomerServiceImpl();
			customerService = new CustomerServiceImpl();
			System.out.println("enter details of custoemr :");
			
			System.out.println("enter name : ");
			customerEntity.setCusName(scanner.next());
			
			System.out.println(" enter contact :");
			customerEntity.setCusNum(scanner.next());
			
			System.out.println("enter email : ");
			customerEntity.setCusEmail(scanner.next());
			
			System.out.println(" enter aadhaar number : ");
			customerEntity.setCusAadhar(scanner.next());
			
			System.out.println(" enter addresss : ");
			customerEntity.setCusAddress(scanner.next());
			
		//	System.out.println(" enter custoemr");
			
			if(customerServiceImpl.validatecusNum(cusNum))
			{
				custId = customerService.addCustomer(customerEntity);
				System.out.println("details successfully added");
			}
			
			break;
			//++++++++++++++++++++++++++++++++++++++
			/*while(customerEntity==null)
		{
			customerEntity=retrieveCustomerEntity();
		}
			//------------------------
			while(flatEntity==null)
			{
				flatEntity=FlatMain.Eflat();
			}
			//-------------------------
		try
		{
		    customerService = new CustomerServiceImpl();
		    
		    cusId=customerService.addCustomer(flatEntity,customerEntity);
		    
		    System.out.println("Flat is booked succesfully");
		    
		}*/
		//++++++++++++++++++++++++++++++++++++
	
		
		
		
		
		case 3:
			customerServiceImpl=new CustomerServiceImpl();
			System.out.println("Enter Customer Phone Number:");
			cusNum = scanner.next();
			customerEntity = getCustomerDetails(cusNum);

			if (customerEntity != null)
			{
				System.out.println("Name               :"+ customerEntity.getCusName());
				System.out.println("Phone Number       :"+ customerEntity.getCusNum());
				System.out.println("Email Id           :"+ customerEntity.getCusEmail());
				System.out.println("Address            :"+ customerEntity.getCusAddress());
				System.out.println("Aadhar number      :"+ customerEntity.getCusAadhar());
				break;
			} 
			else
			{
				System.err.println("There are no details associated with your phone number "+ cusNum);
								
			}

			break;
			
		
		case 4:
			flatEntity=new FlatEntity();
			customerEntity=new CustomerEntity();
			
			System.out.println("Enter your flat Id to see your booked flat ");
			 cusFlatId=scanner.next();
			 
			 System.out.println("Enter your phone no");
			 cusNum=scanner.next();
			 
			 customerServiceImpl=new CustomerServiceImpl();
			 while (true) 
				{
					if ( customerServiceImpl.validatecusFlatId(cusFlatId)&&customerServiceImpl.validatecusNum(cusNum)) 
					{
						break;
					} 
					else
					{
						System.err.println("Please enter correct Flat Id or number");
						
						cusFlatId = scanner.next();
						cusNum=scanner.next();
						
					}
				
				}
			 
			 while(cusFlatId!=null)
			 {
					System.out.println("Your flat details are:");
				
					flatEntity=getFlatDetailsId(cusFlatId,cusNum);
					//System.out.println("get");
				
				    System.out.println(flatEntity);
			 }
				
			 break;
			
		case 5: 
			System.out.print("Exit Trust Application");
			System.exit(0);
			break;
			
		default:System.out.println("enter valid option");	
		}
		}
	catch(InputMismatchException ime)
	{
		System.out.println(ime);
	}
		
 }
	
}




private static FlatEntity Eflat() {
  FlatEntity flatEntity=new FlatEntity();
	System.out.println("Enter flat id ");
	flatEntity.setCusFlatId(scanner.next());
	System.out.println("Enter flat type ");
	flatEntity.setFlatType(scanner.next());
	try
	{
		CustomerServiceImpl.flatValidation(flatEntity);
		return flatEntity;
	}
	catch(CustomerException e) {
		
		System.out.println(e);
	}
	return null;
}




private static FlatEntity getFlatDetailsId(String cusFlatId,String cusNum) throws ClassNotFoundException, FileNotFoundException, SQLException, CustomerException {

	
	FlatEntity flatEntity=null;
	customerService = new CustomerServiceImpl();
	flatEntity=customerService.viewFlatDetailsId(cusFlatId,cusNum);
	System.out.println("get1");
	return flatEntity;
}

private static CustomerEntity getCustomerDetails(String cusId) throws CustomerException, ClassNotFoundException, FileNotFoundException, SQLException {
	CustomerEntity customerEntity = null;
	customerService = new CustomerServiceImpl();

	customerEntity = customerService.viewCustomerDetails(cusId);

	customerService = null;
	return customerEntity;
}

	

private static CustomerEntity retrieveCustomerEntity() {
	
	CustomerEntity customerEntity=new CustomerEntity();
	
		
	System.out.println("enter your Name ");
//	String cusName=scanner.next();
	customerEntity.setCusName(scanner.next());
	
	System.out.println("enter your Number ");
//	String cusNum=scanner.next();
	customerEntity.setCusNum(scanner.next());
	
	System.out.println("enter your Email ");
//	String cusEmail=scanner.next();
	customerEntity.setCusEmail(scanner.next());
	
	System.out.println("enter your Aadhar number ");
//	String cusAadhar=scanner.next();
	customerEntity.setCusAadhar(scanner.next());
	
	System.out.println("enter your Address ");
//	String cusAddress=scanner.next();
	customerEntity.setCusAddress(scanner.next());
	System.out.println(customerEntity.cusAadhar);
	
	/*System.out.println("enter the Flat Id which you wnat to book");
	String cusFlatId=scanner.next();
	customerEntity.setCusFlatId(cusFlatId);*/
	
	//customerServiceImpl =new CustomerServiceImpl(); 
	
	//try
//	{
	//	System.out.println("g");
	//	if(customerServiceImpl.validateCustomer(customerEntity)==null)
	//	return customerEntity;
	//}
	
	//catch(CustomerException customerException)
	//{
	//	System.err.println(customerException.getMessage());
	//	System.exit(0);
		
	//}
	
	return customerEntity;
}
}

