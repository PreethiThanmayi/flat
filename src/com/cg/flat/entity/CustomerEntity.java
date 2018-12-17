package com.cg.flat.entity;

import java.sql.Date;

public class CustomerEntity {
	
	public String cusId;
	public String cusName;
	public String cusNum;
    public String cusEmail;
    public String cusAadhar;
    public String cusAddress;
    public Date cusBookedDate;
    public String cusFlatId;

    public String getCusFlatId() {
		return cusFlatId;
	}
	public void setCusFlatId(String cusFlatId) {
		this.cusFlatId = cusFlatId;
	}
	public String getCusId()
    {
		return cusId;
	}
	public void setCusId(String cusId)
	{
		this.cusId = cusId;
	}

	
	public String getCusName() 
	{
	return cusName;
}
public void setCusName(String cusName)
{
	this.cusName = cusName;
}


public Date getCusBookedDate()
{
	return cusBookedDate;
}
public void setCusBookedDate(Date date) 
{
	this.cusBookedDate = date;
}


public String getCusNum() 
{
	return cusNum;
}
public void setCusNum(String cusNum)
{
	this.cusNum = cusNum;
}


public String getCusEmail()
{
	return cusEmail;
}
public void setCusEmail(String cusEmail)
{
	this.cusEmail = cusEmail;
}


public String getCusAadhar()
{
	return cusAadhar;
}
public void setCusAadhar(String cusAadhar)
{
	this.cusAadhar = cusAadhar;
}



public String getCusAddress()
{
	return cusAddress;
}
public void setCusAddress(String cusAddress)
{
	this.cusAddress = cusAddress;
}
/*@Override
public String toString() {
	return "CustomerEntity [cusId=" + cusId + ", cusNum=" + cusNum + ", cusName=" + cusName + ", cusEmail=" + cusEmail
			+ ", cusAadhar=" + cusAadhar + ", cusAddress=" + cusAddress + ", cusBookedDate=" + cusBookedDate + "]";
}*/




}