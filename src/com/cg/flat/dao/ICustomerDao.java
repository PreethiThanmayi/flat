package com.cg.flat.dao;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import com.cg.flat.entity.CustomerEntity;
import com.cg.flat.entity.FlatEntity;
import com.cg.flat.exception.CustomerException;

public interface ICustomerDao {

	String addCustomer(CustomerEntity customerEntity) throws CustomerException, ClassNotFoundException, FileNotFoundException;
	
	CustomerEntity viewCustomerDetails(String cusId) throws CustomerException, ClassNotFoundException, FileNotFoundException, SQLException;

	List viewFlatDetails(FlatEntity flatEntity) throws ClassNotFoundException, FileNotFoundException, SQLException, CustomerException;

	FlatEntity viewFlatDetailsId(String cusFlatId,String cusNum) throws ClassNotFoundException, FileNotFoundException, SQLException, CustomerException;

}
