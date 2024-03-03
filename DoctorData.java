package hospital_management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DoctorData
{
public static void main(String[] args) throws ClassNotFoundException, SQLException 
{
	//1.load and register the Driver
	Class.forName("com.mysql.cj.jdbc.Driver");
	//2.establish the connection 
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management","root","root");
	//3.create the statement 
	 PreparedStatement ps = con.prepareStatement("insert into doctor values(?,?,?)");
	 Scanner sc = new Scanner(System.in);
	 System.out.println("enter the num of entries :");
	 int entries = sc.nextInt();
	 int i=1;
	 while(entries>=i)
	 {
	 System.out.println("enter the id :");
	 int id=sc.nextInt();
	 System.err.println("enter the name :");
	 String name = sc.next();
	 System.out.println("enter the specialization :");
	 String special = sc.next();
	 
	 ps.setInt(1,id);
	 ps.setString(2, name);
	 ps.setString(3, special);
	 
	 ps.addBatch();
	 i++;
}
	 //4.execute the query 
	 ps.executeBatch();
	 //5.close the connection 
	 con.close();
	 sc.close();
	 
	 System.out.println("data inserted successfully");
}
}