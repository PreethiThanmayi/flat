package com.cg.flat.service;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.flat.dao.CustomerDaoImpl;
import com.cg.flat.dao.ICustomerDao;
import com.cg.flat.entity.CustomerEntity;
import com.cg.flat.entity.FlatEntity;
import com.cg.flat.exception.CustomerException;

public class CustomerServiceImpl implements CustomerService{

	ICustomerDao customerDao=null;
	
	public String addCustomer(CustomerEntity customerEntity) throws ClassNotFoundException, FileNotFoundException, CustomerException {
		customerDao=new CustomerDaoImpl();	
			String cust=null;
			System.out.println("f");
			cust= customerDao.addCustomer(customerEntity);
			System.out.println("e");
			return cust; 
		}
		
	

	public CustomerEntity viewCustomerDetails(String cusId) throws ClassNotFoundException, FileNotFoundException, CustomerException, SQLException {

		customerDao=new CustomerDaoImpl();	
		CustomerEntity customerEntity=null;
		customerEntity=customerDao.viewCustomerDetails(cusId);
		return customerEntity;
	}
	
	
	public List viewFlatDetails(FlatEntity flatEntity) throws ClassNotFoundException, FileNotFoundException, SQLException, CustomerException {
		List<FlatEntity> li=new ArrayList<FlatEntity>();
		customerDao=new CustomerDaoImpl();	
		li=customerDao.viewFlatDetails(flatEntity);
		return li;
	}
	 
	
	
	public FlatEntity viewFlatDetailsId(String cusFlatId,String cusNum) throws ClassNotFoundException, FileNotFoundException, SQLException ,CustomerException
	{
			customerDao=new CustomerDaoImpl();	
			FlatEntity flatEntity=null;
			flatEntity=customerDao.viewFlatDetailsId(cusFlatId,cusNum);
			System.out.println("get2");
			return flatEntity;
		
    }

	


	public void validateCustomer(CustomerEntity customerEntity) throws CustomerException {
		
		if(!(isValidName(customerEntity.getCusName()))) 
		{
			System.out.println("\n  Name Should Be In Alphabets and minimum 3 characters long ! \n");
		}
		
		if(!(validatecusNum(customerEntity.getCusNum())))
		{
			System.out.println("\n Phone Number Should be in 10 digit \n");
		}
		
		if(!(isValidAddress(customerEntity.getCusAddress())))
		{
			System.out.println("\n Address Should Be Greater Than 6 Characters \n");
		}

		if(!(isValidEmail(customerEntity.getCusEmail())))
		{
			System.out.println("\n Email Id must not contain spaces and should be valid format \n" );
		}
		
		if(!(isValidAadhar(customerEntity.getCusAadhar())))
		{
			System.out.println("\n Aadhar number must be 12 digit number \n" );
		}
		
	}


	private boolean isValidName(String cusName) {

		Pattern namePattern=Pattern.compile("^[A-Za-z]{3,}$");
		Matcher nameMatcher=namePattern.matcher(cusName);
		return nameMatcher.matches();
		
		
	}
	public boolean validatecusNum(String cusNum) {
		Pattern phonePattern=Pattern.compile("^[1-9]{1}[0-9]{9}$");
		Matcher phoneMatcher=phonePattern.matcher(cusNum);
		return phoneMatcher.matches();
		
	}


	private boolean isValidAadhar(String cusAadhar) {

		Pattern phonePattern=Pattern.compile("^[0-9]{12}");
		Matcher aadharMatcher=phonePattern.matcher(cusAadhar);
		return aadharMatcher.matches();
		
		
	}
	
	private boolean isValidEmail(String cusEmail) {
		Pattern p3=Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$");
		Matcher emailMatcher=p3.matcher(cusEmail);
		return emailMatcher.matches();
		
	}

	private boolean isValidAddress(String cusAddress) {
		
		return (cusAddress.length() > 6);
		
	}


	public boolean validateflatType(String type) {
		Pattern namePattern=Pattern.compile("[1-3]+BHK");
		Matcher flatNameMatcher=namePattern.matcher(type);
		return flatNameMatcher.matches();
		
		
	}



	public boolean validatecusFlatId(String cusFlatId) {
		
		Pattern phonePattern=Pattern.compile("[0-9]{1,}");
		Matcher idMatcher=phonePattern.matcher(cusFlatId);
		return idMatcher.matches();
		
		
	}
	//---------------------------------------------------
	public static void flatValidation(FlatEntity flatEntity) throws CustomerException {
		
		List validationerror=new ArrayList();
		if(!isVaidFlat(flatEntity.getFlatType())) {
			
			validationerror.add("laptop not avialable");
		}
		if(validationerror.isEmpty()) {
			
			throw new CustomerException(validationerror+"\n");
		}
	
	
	}
   private static boolean isVaidFlat(String FlatType) {
		
		String type=FlatType;
	
		if(type.equals("1BHK")||type.equals("2BHK")||type.equals("3BHK"))
		{
			return true;
		}
		else 
		{
			return false;
		}
		
	}
   //-------------------------------------------------------------------
}


