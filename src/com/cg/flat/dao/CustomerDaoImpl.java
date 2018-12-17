package com.cg.flat.dao;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cg.flat.entity.CustomerEntity;
import com.cg.flat.entity.FlatEntity;
import com.cg.flat.exception.CustomerException;
import com.cg.flat.util.DBConnection;

public class CustomerDaoImpl implements ICustomerDao {
	static PreparedStatement preparedStatement;
	static Connection connection;
	
	public String addCustomer(CustomerEntity customerEntity)throws CustomerException, ClassNotFoundException{
	//	String cusId=null;
		//String cusNum=null;
		String fType=null;
		try {
			Connection connection=DBConnection.getConnection();
			PreparedStatement preparedStatement=null;
			PreparedStatement pst1=null;
			ResultSet rs = null;
			Statement statement=connection.createStatement();
			String flatId=null;
			preparedStatement=connection.prepareStatement("insert into customer values(cust_seq.nextval,?,?,?,?,?,sysdate,?,?)");

			preparedStatement.setString(1,customerEntity.getCusName());			
			preparedStatement.setString(2,customerEntity.getCusNum());
			preparedStatement.setString(3,customerEntity.getCusEmail());
			preparedStatement.setString(4,customerEntity.getCusAadhar());
			preparedStatement.setString(5,customerEntity.getCusAddress());
			preparedStatement.setString(6,customerEntity.getCusFlatId());
			preparedStatement.setString(7, flatId);
			
									
			FlatEntity fe=new FlatEntity();
		    CustomerEntity ce=new CustomerEntity();
		   
			preparedStatement.executeUpdate();
		     
		     String str=fe.getFlatType();
			
//		statement.executeUpdate("update flat set flatavty=flatavty-1 where flattype='"+str+"'");
		    rs = statement.executeQuery("select flattype from flat where cusflatid='"+customerEntity.getCusFlatId()+"'");
		    while(rs.next())
		    {
		    	fType = rs.getString(1);
		    }
		    pst1 = connection.prepareStatement("update customer set flattype = ?");
		    pst1.setString(1, fType);
		    pst1.executeUpdate();
		    
		    System.out.println("x");
			return customerEntity.cusId;
		}
			catch(Exception e) 
			{
				System.out.println("y");
				System.out.println(e);
				e.printStackTrace();
			}
	
		return null;
			
	}
		
	public List viewFlatDetails(FlatEntity flatEntity) throws ClassNotFoundException, FileNotFoundException, SQLException, CustomerException 
	{

		Connection connection=DBConnection.getConnection();
		Statement st=connection.createStatement();
		ResultSet resultset = null;
		List<FlatEntity> li=new ArrayList<FlatEntity>();
		try
		{
			
	          resultset=st.executeQuery("select * from flat ");
		
			
			while(resultset.next())
			{
				FlatEntity flatEntity1=new FlatEntity();
				flatEntity1.setCusFlatId(resultset.getString(1));
				flatEntity1.setFlatArea(resultset.getString(2));
				flatEntity1.setFlatType(resultset.getString(3));
				flatEntity1.setFlatSqft(resultset.getString(4));
				flatEntity1.setFlatPrice(resultset.getString(5));
				flatEntity1.setFlatAvty(resultset.getInt(6));
				li.add(flatEntity1);
				
				//System.out.println(resultset.getString(1) +  resultset.getString(2)+ resultset.getString(3)+  resultset.getString(4)+  resultset.getString(5));
			}
			
		}
		catch(Exception e)
		{
			
			throw new CustomerException(e.getMessage());
		}
		
		return li;
	}
	
				
	public CustomerEntity viewCustomerDetails(String cusNum) throws CustomerException, ClassNotFoundException, FileNotFoundException, SQLException
	{
		Connection connection=DBConnection.getConnection();
		
		PreparedStatement preparedStatement=null;
		ResultSet resultset = null;
		CustomerEntity customerEntity=null;
		
		try
		{
			preparedStatement=connection.prepareStatement("select * from customer where cusNum=?");
			preparedStatement.setString(1,cusNum);
			resultset=preparedStatement.executeQuery();
			
			if(resultset.next())
			{
				customerEntity = new CustomerEntity();
				
				customerEntity.setCusName(resultset.getString(2));
				customerEntity.setCusNum(resultset.getString(3));
				customerEntity.setCusEmail(resultset.getString(4));
				customerEntity.setCusAddress(resultset.getString(5));
				customerEntity.setCusAadhar(resultset.getString(6));
				
				//customerEntity.setCusBookedDate(resultset.getCusBookedDate(6));
				//System.out.println(resultset.getString(1) +  resultset.getString(2)+ resultset.getString(3)+  resultset.getString(4)+  resultset.getString(5)+  resultset.getString(6));
			}
			
		}
		catch(Exception e)
		{
			
			throw new CustomerException(e.getMessage());
		}
		finally
		{
			try 
			{
				resultset.close();
				preparedStatement.close();
				connection.close();
			} 
			catch (SQLException e) 
			{
				
				throw new CustomerException("Error in closing db connection");

			}
		}
		return customerEntity;
		
	}

	
	public FlatEntity viewFlatDetailsId(String cusFlatId,String cusNum) throws ClassNotFoundException, FileNotFoundException, SQLException  {
		Connection connection=DBConnection.getConnection();
		
		PreparedStatement preparedStatement=null;
		ResultSet resultset = null;
		FlatEntity flatEntity=null;
		CustomerEntity customerEntity=null;
		
		try
		{
			preparedStatement=connection.prepareStatement("select f.flatarea ,f.flattype , f.flatsqft ,f.flatprice ,f.flatavty,c.cusname,c.cusnum,c.cusbookeddate from flat f,customer c where f.cusflatid=? and c.cusnum=? and f.cusflatid=c.cusflatid");
			preparedStatement.setString(1,cusFlatId);
			preparedStatement.setString(2,cusNum);
			resultset=preparedStatement.executeQuery();
			while(resultset.next())
			{
			    flatEntity=new FlatEntity();
			    customerEntity = new CustomerEntity();

			    flatEntity.setFlatArea(resultset.getString(1));
				flatEntity.setFlatType(resultset.getString(2));
				flatEntity.setFlatSqft(resultset.getString(3));
				flatEntity.setFlatPrice(resultset.getString(4));
				flatEntity.setCusFlatId(resultset.getString(5));
				customerEntity.setCusName(resultset.getString(1));
				customerEntity.setCusNum(resultset.getString(2));
				customerEntity.setCusBookedDate(resultset.getDate(3));
				//System.out.println(resultset.getString(1) +  resultset.getString(2)+ resultset.getString(3)+  resultset.getString(4)+  resultset.getString(5)+  resultset.getString(6)+  resultset.getString(7)+  resultset.getString(8));
			}
			
		}
		catch(Exception e)
		{
			
			try {
				throw new CustomerException(e.getMessage());
			} catch (CustomerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return flatEntity;
	}



	
	
	
	
}
		
