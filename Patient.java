package hospital_management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient 
{
	private Connection con;
	private Scanner sc;
	public Patient(Connection con , Scanner sc)
	{
		this.con=con;
		this.sc=sc;
	}
	public void addPatient()
	{
		System.out.println("enter the id of the patient :");
		int id =sc.nextInt();
		System.out.println("enter the name of the patient");
		String name = sc.next();
		System.out.println("enter the age of the patient :");
		int age = sc.nextInt();
		System.out.println("enter the gender of the patient :");
		String gender=sc.next();
		//create the statement 
		try
		{
			String query = "insert into patients values(?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setInt(3, age);
			ps.setString(4, gender);
			//execute the query 
			int value = ps.executeUpdate();
			if(value>0)
			{
				System.out.println("patient data added successfully");
			}
			else
			{
				System.out.println("failed to add the data");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void viewPatients() throws SQLException
	{
		String query ="select * from patients";
		//create the statement
		PreparedStatement ps = con.prepareStatement(query);
		   //execute the query
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			System.out.println(rs.getInt(1));
			System.out.println(rs.getString(2));
			System.out.println(rs.getInt(3));
			System.out.println(rs.getString(4));
		}
	}
	
	public boolean getPatientById(int id) throws SQLException
	{
		String query ="select * from patients where id=?";
		//create the statement 
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1,id);
		//execute the query 
		ResultSet rs = ps.executeQuery();
	  if(rs.next())
		{
			return true;
		}
	  else
	  {
		  return false;
	  }
	}
	
}

