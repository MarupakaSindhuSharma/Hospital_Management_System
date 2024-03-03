package hospital_management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Appointment 
{
private Connection con;
private Scanner sc;

public Appointment(Connection con ,Scanner sc)
{
	this.con=con;
	this.sc=sc;
}
public void addData() throws SQLException
{
	System.out.println("enter the id :");
	int id =sc.nextInt();
	System.out.println("enter the patient id:");
	int patientId=sc.nextInt();
	System.out.println("enter the doctor id:");
	int doctorId=sc.nextInt();
	System.out.println("enter the appointment date");
	String date=sc.next();
	//create the statement 
	PreparedStatement ps = con.prepareStatement("insert into appointment values(?,?,?,?)");
	  ps.setInt(1, id);
	  ps.setInt(2, patientId);
	  ps.setInt(3, doctorId);
	  ps.setString(4, date);
	  //execute the query 
	  ps.execute();
	  System.out.println("data got inserted successfully");
}

public static void bookAppointment(Patient patient, Doctor doctor, Connection connection, Scanner scanner) throws SQLException{
    System.out.print("Enter Patient Id: ");
    int patientId = scanner.nextInt();
    System.out.print("Enter Doctor Id: ");
    int doctorId = scanner.nextInt();
    System.out.print("Enter appointment date (YYYY-MM-DD): ");
    String appointmentDate = scanner.next();
    if(patient.getPatientById(patientId) && doctor.getDoctorById(doctorId)){
        if(checkDoctorAvailability(doctorId, appointmentDate, connection)){
            String appointmentQuery = "INSERT INTO appointments(patient_id, doctor_id, appointment_date) VALUES(?, ?, ?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery);
                preparedStatement.setInt(1, patientId);
                preparedStatement.setInt(2, doctorId);
                preparedStatement.setString(3, appointmentDate);
                int rowsAffected = preparedStatement.executeUpdate();
                if(rowsAffected>0){
                    System.out.println("Appointment Booked!");
                }else{
                    System.out.println("Failed to Book Appointment!");
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }else{
            System.out.println("Doctor not available on this date!!");
        }
    }else{
        System.out.println("Either doctor or patient doesn't exist!!!");
    }
}

public static boolean checkDoctorAvailability(int doctorId, String appointmentDate, Connection connection){
    String query = "SELECT COUNT(*) FROM appointments WHERE doctor_id = ? AND appointment_date = ?";
    try{
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, doctorId);
        preparedStatement.setString(2, appointmentDate);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            int count = resultSet.getInt(1);
            if(count==0){
                return true;
            }else{
                return false;
            }
        }
    } catch (SQLException e){
        e.printStackTrace();
    }
    return false;
}
}