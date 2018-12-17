package com.cg.flat.service;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import com.cg.flat.entity.CustomerEntity;
import com.cg.flat.entity.FlatEntity;
import com.cg.flat.exception.CustomerException;

public interface CustomerService {

	CustomerEntity viewCustomerDetails(String cusId) throws ClassNotFoundException, FileNotFoundException, CustomerException, SQLException;

	String addCustomer(CustomerEntity customerEntity) throws ClassNotFoundException, FileNotFoundException, CustomerException;

	List viewFlatDetails(FlatEntity flatEntity) throws ClassNotFoundException, FileNotFoundException, SQLException, CustomerException;

	FlatEntity viewFlatDetailsId(String cusFlatId,String cusNum) throws ClassNotFoundException, FileNotFoundException, SQLException, CustomerException;

}
