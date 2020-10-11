package DatabaseHomework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.PreparedStatement;

public class Database {
    private String url = "jdbc:sqlserver://DESKTOP-42TKEOP:1433;databaseName=StudentManagement";
    private String user = "Sambath";
    private String password = "012886342";

    private Scanner input = new Scanner(System.in);
    private String dbURL = new String();
    private Connection conn;
    private PreparedStatement pst;
    private ResultSet rs;
    protected boolean dbStatus;

    private String StudentName,Gender,PhoneNo,StudentID,CourseID,CourseName,NumberOfCredits,NumberOfHours,FinalScore;

    public Database() {
        try {
            conn = DriverManager.getConnection(url,user,password);
            dbStatus = true;
        } catch (Exception e) {
            System.out.println("Database failed connection !");
            dbStatus = false;
        }
    }
    public Connection getConn(){
        return conn;
    }

    public ResultSet getRs(){
        return rs;
    }

    public Scanner getInput(){
        return input;
    }

    public void addCourse() throws SQLException{
        System.out.println("-------------------------");
        System.out.print("CourseName: "); CourseName = input.nextLine();
        System.out.print("NumberOfCredits: "); NumberOfCredits = input.nextLine();
        System.out.print("NumberOfHours: "); NumberOfHours = input.nextLine();
        pst = conn.prepareStatement("INSERT INTO Course(CourseName,NumberOfCredits,NumberOfHours) VALUES(?,?,?)");
        pst.setString(1, CourseName);
        pst.setString(2, NumberOfCredits);
        pst.setString(3, NumberOfHours);
        pst.executeUpdate();
        conn.commit();
        rs = conn.createStatement().executeQuery("SELECT TOP 1 * FROM Course ORDER BY CourseID DESC");
        System.out.println(
            "CourseID\t|CourseName              \t|NumberOfCredits\t|NumberOfHours     \t|\n"+
            "----------------|-------------------------------|-----------------------|-----------------------|"
            );
        rs.next();
        System.out.format(
            "%s \t\t|%s\t|%s\t\t\t|%s\t\t\t|\n",
            rs.getString("CourseID"),
            rs.getString("CourseName"),
            rs.getString("NumberOfCredits"),
            rs.getString("NumberOfHours")
            );
        rs.close();
    }

    public void showAllCourse() throws SQLException{
        rs = conn.createStatement().executeQuery("SELECT * FROM Course");
        System.out.println(
            "CourseID\t|CourseName              \t|NumberOfCredits\t|NumberOfHours     \t|\n"+
            "----------------|-------------------------------|-----------------------|-----------------------|"
            );
        while(rs.next()){
            System.out.format(
            "%s \t\t|%s\t|%s\t\t\t|%s\t\t\t|\n",
            rs.getString("CourseID"),
            rs.getString("CourseName"),
            rs.getString("NumberOfCredits"),
            rs.getString("NumberOfHours")
            );
        }
        rs.close();
    }

    public void stuEnrollCourse(){
        int numCourse = 0;
        System.out.println("-------------------------");
        while(true){
            try {
                System.out.print("Student ID:"); StudentID = input.nextLine();
                rs = conn.createStatement().executeQuery("SELECT StudentID FROM Student WHERE StudentID = " + StudentID);
                rs.next();
                StudentID = rs.getString("StudentID");
                rs.close();
                break;
            } catch (SQLException e) {
                if(e.getErrorCode() == 0) System.out.println("This student not found !");
            }
        }
        System.out.print("How many courses to enroll:"); numCourse = input.nextInt();input.nextLine();
        System.out.println("--List course ID to enroll--");
        while(numCourse-- != 0){
            System.out.print("Course ID:"); CourseID = input.nextLine();
            try {
                pst = conn.prepareStatement("INSERT INTO StudentDetail(StudentID,CourseID) VALUES(?,?)");
                pst.setString(1, StudentID);
                pst.setString(2, CourseID);
                pst.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                if(e.getErrorCode() == 2601){
                    System.out.println("This student enrolled already !");
                }else if(e.getErrorCode() == 547){
                    System.out.println("This course not found !");
                }
                numCourse++;
            }

        }
        System.out.println("Enroll Successfully !");
    }

}

