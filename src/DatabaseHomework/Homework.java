package DatabaseHomework;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Calendar;

public class Homework {
    private int homeworkID;
    private String topicName;
    private String content;
    private Date Deadline;

    private ResultSet rs;
    private PreparedStatement pst;
    private Database database;
    private Course course;

    public void setDatabase(Database database){
        this.database = database;
    }
    public void setCourse(Course course){
        this.course = course;
    }

    public void addHomework(int courseID){
        if(course.searchCourseByID(courseID) != -1){
            try{
                pst = database.getConn().prepareStatement("INSERT INTO Homework(CourseID,TopicName,Content,Deadline) VALUES(?,?,?,?)");
                pst.setInt(1,courseID);
                System.out.print("Topic Name : ");
                pst.setString(2,database.getInput().nextLine());
                System.out.print("Content : ");
                pst.setString(3,database.getInput().nextLine());
                setDeadline();pst.setDate(4,Deadline);
                pst.executeUpdate();
                database.getConn().commit();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }else {
            System.out.println("No Course was found!");
        }
    }

    private void setDeadline(){
        int date,month,year;
        Calendar c = Calendar.getInstance();
        System.out.println("Deadline : ");
        System.out.print("Date : ");
        date = database.getInput().nextInt();
        System.out.print("Month (Int): ");
        month = database.getInput().nextInt();
        System.out.print("Year : ");
        year = database.getInput().nextInt();
        Deadline = java.sql.Date.valueOf(year+"-"+month+"-"+date);
    }
}
