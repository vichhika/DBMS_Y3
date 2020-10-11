package DatabaseHomework;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class Course {
    private int CourseID;
    private String CourseName;
    private int numberOfCredits;
    private String numberOfHours;
    private int FinalScore;
    private Homework[] homework;

    private ResultSet rs;
    private PreparedStatement pst;
    private Database database;
    private Student student;

    public void setDatabase(Database database){
        this.database = database;
    }

    public void setStudent(Student student){
        this.student = student;
    }

    public void addCourse() throws SQLException {
        System.out.println("-------------------------");
        System.out.print("CourseName: "); CourseName = database.getInput().nextLine();
        System.out.print("NumberOfCredits: "); numberOfCredits = Integer.parseInt(database.getInput().nextLine());
        System.out.print("NumberOfHours: "); numberOfHours = database.getInput().nextLine();
        pst = database.getConn().prepareStatement("INSERT INTO Course(CourseName,NumberOfCredits,NumberOfHours) VALUES(?,?,?)");
        pst.setString(1, CourseName);
        pst.setInt(2, numberOfCredits);
        pst.setString(3, numberOfHours);
        pst.executeUpdate();
        database.getConn().commit();
        rs = database.getConn().createStatement().executeQuery("SELECT TOP 1 * FROM Course ORDER BY CourseID DESC");
        System.out.println(
                "CourseID\t|CourseName              \t|NumberOfCredits\t|NumberOfHours     \t|\n"+
                        "------------|---------------------------|-------------------|-------------------|"
        );
        rs.next();
        System.out.format(
                "%s \t\t\t|%s\t|%s\t\t\t\t\t|%s\t\t\t\t\t|\n",
                rs.getString("CourseID"),
                rs.getString("CourseName"),
                rs.getString("NumberOfCredits"),
                rs.getString("NumberOfHours")
        );
        rs.close();
    }

    public void showAllCourse() throws SQLException {
        rs = database.getConn().createStatement().executeQuery("SELECT * FROM Course");
        System.out.println(
                "CourseID\t|CourseName              \t|NumberOfCredits\t|NumberOfHours     \t|\n"+
                        "------------|---------------------------|-------------------|-------------------|"
        );
        while(rs.next()){
            System.out.format(
                    "%s \t\t\t|%s\t|%s\t\t\t\t\t|%s\t\t\t\t\t|\n",
                    rs.getString("CourseID"),
                    rs.getString("CourseName"),
                    rs.getString("NumberOfCredits"),
                    rs.getString("NumberOfHours")
            );
        }
        rs.close();
    }

    public void stuEnrollCourse() throws SQLException {
        int numCourse = 0;
        int StudentID;
        System.out.println("-------------------------");
        while(true){
                System.out.print("Student ID:");
                StudentID = student.searchStudent(database.getInput().nextInt());
                if (StudentID != -1)
                    break;
        }
        System.out.print("How many courses to enroll:"); numCourse = database.getInput().nextInt();database.getInput().nextLine();
        System.out.println("--List course ID to enroll--");
        showAllCourse();
        while(numCourse-- != 0){
            System.out.print("Course ID:"); CourseID = Integer.parseInt(database.getInput().nextLine());
            try {
                pst = database.getConn().prepareStatement("INSERT INTO StudentDetail(StudentID,CourseID) VALUES(?,?)");
                pst.setInt(1, StudentID);
                pst.setInt(2, CourseID);
                pst.executeUpdate();
                database.getConn().commit();
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
        System.out.print("StudentID:");
        rs = database.getConn().createStatement().executeQuery(
                "SELECT Student.StudentName,Course.CourseName FROM Student INNER JOIN " +
                        "(StudentDetail INNER JOIN Course ON StudentDetail.CourseID = Course.CourseID )" +
                        " ON Student.StudentID = StudentDetail.StudentID WHERE Student.StudentID = "
                        + database.getInput().nextLine());
        System.out.println(
                "StudentName              \t|CourseName              \t|\n"+
                        "----------------------------|---------------------------|"
        );
        while(rs.next()){
            System.out.format("%s\t|%s\t|\n",
                    rs.getString("StudentName"),
                    rs.getString("CourseName")
            );
        }
        rs.close();
    }

    public void setFinalScore(int StudentID,int courseID,int score) throws SQLException {
        pst = database.getConn().prepareStatement("UPDATE StudentDetail SET FinalScore = ? WHERE StudentID = ? AND CourseID = ?");
        pst.setInt(1, FinalScore);
        pst.setInt(2, StudentID);
        pst.setInt(3, courseID);
        pst.executeUpdate();
        System.out.println("Update Successfully !");
    }

    public int searchCourseByID(int courseID){
        try {
            rs = database.getConn().createStatement().executeQuery("SELECT * FROM Course WHERE CourseID = " + courseID);
            if (rs.next())
                return courseID;
        } catch (SQLException e) {
            if(e.getErrorCode() == 0) System.out.println("This course not found !");
        }
        return -1;
    }

}
