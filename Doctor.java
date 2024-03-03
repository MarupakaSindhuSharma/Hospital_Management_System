package hospital_management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor 
{
private Connection con;
public Doctor(Connection con)
{
	super();
	this.con = con;
	
}
public void viewDoctors() throws SQLException
{
String query ="select * from doctor";
//create the statement 
PreparedStatement ps = con.prepareStatement(query);
//execute the query 
ResultSet rs = ps.executeQuery();
while(rs.next())
{
	System.out.println(rs.getInt(1));
	System.out.println(rs.getString(2));
	System.out.println(rs.getString(3));
}
}
public boolean getDoctorById(int id ) throws SQLException
{
	String query ="select * from doctor where id=?";
	//create the statement 
	PreparedStatement ps = con.prepareStatement(query);
	ps.setInt(1, id);
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
