import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.PreparedStatement;

public class Database {
    
    private Scanner input = new Scanner(System.in);
    private String dbURL = new String();
    private Connection conn;
    private PreparedStatement pst;
    private ResultSet rs;
    protected boolean dbStatus;

    private String StudentName,Gender,PhoneNo,StudentID,CourseID,CourseName,NumberOfCredits,NumberOfHours,FinalScore;

    public Database() {
        try {
            dbURL = "jdbc:sqlserver://localhost:1433;databaseName=StudentManagement;user=SA;password=Vichhika@2020";
            conn = DriverManager.getConnection(dbURL);
            dbStatus = true;
        } catch (Exception e) {
            System.out.println("Database failed connection !");
            dbStatus = false;
        }
    }
    
    public void addStudent() throws SQLException{
        System.out.println("-------------------------");
        System.out.print("StudentName:"); StudentName=input.nextLine();
        System.out.print("Gender F/M/O:"); Gender=input.nextLine();
        System.out.print("Phone Number:"); PhoneNo=input.nextLine();
        pst = conn.prepareStatement("INSERT INTO Student(StudentName,Gender,PhoneNo) VALUES(?,?,?)");
        pst.setString(1, StudentName);
        pst.setString(2, Gender);
        pst.setString(3, PhoneNo);
        pst.executeUpdate();
        conn.commit();
        rs = conn.createStatement().executeQuery("SELECT TOP 1 * FROM Student ORDER BY StudentID DESC");
        System.out.println(
            "StudentID\t|StudentName              \t|Gender\t|PhoneNo     \t|\n"+
            "----------------|-------------------------------|-------|---------------|"
            );
        rs.next();
        System.out.format(
            "%s \t\t|%s\t|%s\t|%s\t|\n",
            rs.getString("StudentID"),
            rs.getString("StudentName"),
            rs.getString("Gender"),
            rs.getString("PhoneNo")
            );
        rs.close();
    }

    public void searchStudent(){
        System.out.println("-------------------------");
        System.out.print("StudentID:"); StudentID=input.nextLine();
        try {
            rs = conn.createStatement().executeQuery("SELECT * FROM Student WHERE StudentID = " + StudentID);
            System.out.println(
                "StudentID\t|StudentName              \t|Gender\t|PhoneNo     \t|\n"+
                "----------------|-------------------------------|-------|---------------|"
                );
            while(rs.next()){
                System.out.format(
                    "%s \t\t|%s\t|%s\t|%s\t|\n",
                    rs.getString("StudentID"),
                    rs.getString("StudentName"),
                    rs.getString("Gender"),
                    rs.getString("PhoneNo")
                    );
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Not found !");
        }
    }

    public void showAllStudent(){
        try {
            rs = conn.createStatement().executeQuery("SELECT * FROM Student");
            System.out.println(
                "StudentID\t|StudentName              \t|Gender\t|PhoneNo     \t|\n"+
                "----------------|-------------------------------|-------|---------------|"
                );
            while(rs.next()){
                System.out.format(
                    "%s \t\t|%s\t|%s\t|%s\t|\n",
                    rs.getString("StudentID"),
                    rs.getString("StudentName"),
                    rs.getString("Gender"),
                    rs.getString("PhoneNo")
                    );
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Not found !");
        }
    }

    public void delStudentID() throws SQLException{
        System.out.println("-------------------------");
        System.out.print("StudentID:"); StudentID=input.nextLine();
        conn.createStatement().executeUpdate("DELETE FROM Student WHERE StudentID = "+StudentID);
        showAllStudent();
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

    public void showCourseAttended() throws SQLException{
        System.out.println("-------------------------");
        System.out.print("StudentID:"); StudentID=input.nextLine();
        rs = conn.createStatement().executeQuery("SELECT Student.StudentName,Course.CourseName FROM Student INNER JOIN (StudentDetail INNER JOIN Course ON StudentDetail.CourseID = Course.CourseID ) ON Student.StudentID = StudentDetail.StudentID WHERE Student.StudentID = " + StudentID);
        System.out.println(
            "StudentName              \t|CourseName              \t|\n"+
            "--------------------------------|-------------------------------|"
            );
        while(rs.next()){
            System.out.format("%s\t|%s\t|\n",
            rs.getString("StudentName"),
            rs.getString("CourseName")
            );
        }
        rs.close();
    }

    public void studentUpdate() throws SQLException{
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
        while(true){
            System.out.print(
                "-----Update a student's information-----\n"+
                "1. Update Name\n"+
                "2. Update Gender\n"+
                "3. Update Phone Number\n"+
                "4. Update FinalScore\n"+
                "0. Back to menu\n"+
                "Choose: "
            );
            switch (input.nextInt()){
                case 1:
                    input.nextLine();
                    System.out.println("-------------------------");
                    System.out.print("Input new Full Name:"); StudentName = input.nextLine();
                    updateStudentStatement("StudentName", StudentName, StudentID);
                    break;
                case 2:
                    input.nextLine();
                    System.out.println("-------------------------");
                    System.out.print("Input new Gender F/M/O:"); Gender = input.nextLine();
                    updateStudentStatement("Gender", Gender, StudentID);
                    break;
                case 3:
                    input.nextLine();
                    System.out.println("-------------------------");
                    System.out.print("Input new Phone Number:"); PhoneNo = input.nextLine();
                    updateStudentStatement("PhoneNo", PhoneNo, StudentID);
                    break;
                case 4:
                    input.nextLine();
                    try {
                        rs = conn.createStatement().executeQuery("SELECT StudentID FROM StudentDetail WHERE StudentID = " + StudentID);
                        rs.next();
                        StudentID = rs.getString("StudentID");
                        rs.close();
                    } catch (SQLException e) {
                        if(e.getErrorCode() == 0){
                            System.out.println("Can't Update FinalScore because this student did not enroll any course !");
                            break;
                        }
                    }
                    System.out.println("-------------------------");
                    while(true){
                        try {
                            System.out.print("Course ID:"); CourseID = input.nextLine();
                            rs = conn.createStatement().executeQuery("SELECT CourseID FROM StudentDetail WHERE StudentID = " + StudentID + "AND CourseID = " + CourseID);
                            rs.next();
                            CourseID = rs.getString("CourseID");
                            rs.close();
                            break;
                        } catch (SQLException e) {
                            if(e.getErrorCode() == 0) System.out.println("This student did not enroll this course !");
                        }
                    }
                    System.out.println("-------------------------");
                    System.out.print("Input new FinalScore:"); FinalScore = input.nextLine();
                    pst = conn.prepareStatement("UPDATE StudentDetail SET FinalScore = ? WHERE StudentID = ? AND CourseID = ?");
                    pst.setString(1, FinalScore);
                    pst.setString(2, StudentID);
                    pst.setString(3, CourseID);
                    pst.executeUpdate();
                    System.out.println("Update Successfully !");
                    break;
                case 0:
                    input.nextLine();
                    return;
                default:
                    System.out.println("Wrong input!");
                    break;
            }
        }
    }

    private void updateStudentStatement(String arg0,String arg1,String arg2) throws SQLException{
        pst = conn.prepareStatement("UPDATE Student SET " + arg0 + " = ? WHERE StudentID =  ?");
        pst.setString(1, arg1);
        pst.setString(2, arg2);
        pst.executeUpdate();
        System.out.println("Update Successfully !");
    }
}

