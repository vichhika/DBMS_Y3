package DatabaseHomework;
import java.sql.*;

public class Student {
    private ResultSet rs;
    private PreparedStatement pst;
    private Database database;
    private int StudentID;
    private String StudentName = null;
    private char Gender;
    private String PhoneNo = null;
    private Course course;

    public void setStudentName(String StudentName){
        this.StudentName = StudentName;
    }

    public void setGender(char Gender){
        this.Gender = Gender;
    }

    public void setPhoneNo(String PhoneNo){
        this.PhoneNo = PhoneNo;
    }

    public void setDatabase(Database database){
        this.database = database;
    }

    public void setCourse(Course course){
        this.course = course;
    }

    public void addStudent() throws SQLException {
        System.out.println("-------------------------");
        pst = database.getConn().prepareStatement("INSERT INTO Student(StudentName,Gender,PhoneNo) VALUES(?,?,?)");
        pst.setString(1, StudentName);
        pst.setString(2, Character.toString(Gender));
        pst.setString(3, PhoneNo);
        pst.executeUpdate();
        database.getConn().commit();
        rs = database.getConn().createStatement().executeQuery("SELECT StudentID FROM Student ORDER BY StudentID DESC");
        rs.next();
        System.out.println("Your ID is : "+ rs.getString("StudentID"));
        rs.close();
    }

    public int searchStudent(int stuID){
        try {
            rs = database.getConn().createStatement().executeQuery("SELECT * FROM Student WHERE StudentID = " + stuID);
            if (rs.next())
                return stuID;
        } catch (SQLException e) {
            if(e.getErrorCode() == 0) System.out.println("This student not found !");
        }
        return -1;
    }

    public void showAllStudent(){
        try {
            rs = database.getConn().createStatement().executeQuery("SELECT * FROM Student");
            System.out.println(
                    "StudentID\t|StudentName              \t|Gender\t|PhoneNo     \t|\n"+
                            "------------|---------------------------|-------|---------------|"
            );
            while(rs.next()){
                System.out.format(
                        "%s \t\t\t|%s\t|%s\t\t|%s\t|\n",
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
        System.out.print("StudentID:"); StudentID = database.getInput().nextInt();
        database.getConn().createStatement().executeUpdate("DELETE FROM Student WHERE StudentID = "+StudentID);
        if (searchStudent(StudentID) != -1)
            System.out.println("Delete Successful");
        else
            System.out.println("No ID : "+StudentID);
    }

    public void studentUpdate() throws SQLException{
        System.out.println("-------------------------");
        while(true){
            try {
                System.out.print("Student ID:"); StudentID = database.getInput().nextInt();
                rs = database.getConn().createStatement().executeQuery("SELECT StudentID FROM Student WHERE StudentID = " + StudentID);
                rs.next();
                StudentID = Integer.parseInt(rs.getString("StudentID"));
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
            switch (database.getInput().nextInt()){
                case 1:
                    database.getInput().nextLine();
                    System.out.println("-------------------------");
                    System.out.print("Input new Full Name:"); StudentName = database.getInput().nextLine();
                    updateStudentStatement("StudentName", StudentName, StudentID);
                    break;
                case 2:
                    database.getInput().nextLine();
                    System.out.println("-------------------------");
                    System.out.print("Input new Gender F/M/O:"); Gender = database.getInput().nextLine().charAt(0);
                    updateStudentStatement("Gender", Character.toString(Gender), StudentID);
                    break;
                case 3:
                    database.getInput().nextLine();
                    System.out.println("-------------------------");
                    System.out.print("Input new Phone Number:"); PhoneNo = database.getInput().nextLine();
                    updateStudentStatement("PhoneNo", PhoneNo, StudentID);
                    break;
                case 4:
                    try {
                        rs = database.getConn().createStatement().executeQuery("SELECT StudentID FROM StudentDetail WHERE StudentID = " + StudentID);
                        rs.next();
                        StudentID = Integer.parseInt(rs.getString("StudentID"));
                        rs.close();
                    } catch (SQLException e) {
                        if(e.getErrorCode() == 0){
                            System.out.println("Can't Update FinalScore because this student did not enroll any course !");
                            break;
                        }
                    }
                    System.out.println("-------------------------");
                    int CourseID;
                    while(true){
                        try {
                            System.out.print("Course ID:"); CourseID = database.getInput().nextInt();
                            rs = database.getConn().createStatement().executeQuery("SELECT CourseID FROM StudentDetail WHERE StudentID = " + StudentID + "AND CourseID = " + CourseID);
                            rs.next();
                            CourseID = Integer.parseInt(rs.getString("CourseID"));
                            rs.close();
                            break;
                        } catch (SQLException e) {
                            if(e.getErrorCode() == 0) System.out.println("This student did not enroll this course !");
                        }
                    }
                    System.out.println("-------------------------");
                    System.out.print("Input new FinalScore : ");
                    course.setFinalScore(StudentID,CourseID,database.getInput().nextInt());
                    break;
                case 0:
                    database.getInput().nextLine();
                    return;
                default:
                    System.out.println("Wrong input!");
                    break;
            }
        }
    }
    private void updateStudentStatement(String arg0,String arg1,int arg2) throws SQLException{
        pst = database.getConn().prepareStatement("UPDATE Student SET " + arg0 + " = ? WHERE StudentID =  ?");
        pst.setString(1, arg1);
        pst.setInt(2, arg2);
        pst.executeUpdate();
        System.out.println("Update Successfully !");
    }
    public void DisplayStudentInfo(int stuID){
        try {
            rs = database.getConn().createStatement().executeQuery("SELECT * FROM Student WHERE StudentID = " + stuID);
            while(rs.next()){
                System.out.println(
                        "StudentID\t|StudentName              \t|Gender\t|PhoneNo     \t|\n"+
                                "------------|---------------------------|-------|---------------|"
                );
                System.out.format(
                        "%s \t\t\t|%s\t|%s\t\t|%s\t|\n",
                        rs.getString("StudentID"),
                        rs.getString("StudentName"),
                        rs.getString("Gender"),
                        rs.getString("PhoneNo")
                );
            }
            rs.close();
        } catch (SQLException e) {
            if(e.getErrorCode() == 0) System.out.println("This student not found !");
        }
    }
}
