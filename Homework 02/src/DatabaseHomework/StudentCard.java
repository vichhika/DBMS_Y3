package DatabaseHomework;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class StudentCard {
    private int StudentID;
    private Date IssuedDate;
    private Date ExpireDate;
    private String UniversityName;

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

    public boolean cardIdExisting(int StuID){
        try {
            rs = database.getConn().createStatement().executeQuery("SELECT StudentID FROM StudentCard WHERE StudentID = " + StuID);
            return true;
        } catch (SQLException e) {
            if(e.getErrorCode() == 0) System.out.println("This student not found !");
            return false;
        }
    }
    public void UpdateCard(int stuID) {
        if(student.searchStudent(stuID) != -1){
            setIssuedDate();setExpireDate();
            System.out.print("University Name : "); database.getInput().nextLine();
            UniversityName = database.getInput().nextLine();
            try{
                pst = database.getConn().prepareStatement("INSERT INTO StudentCard(StudentID,IsuedDate,ExpiredDate,UniversityName) VALUES(?,?,?,?)");
                pst.setInt(1,stuID);
                pst.setDate(2,IssuedDate);
                pst.setDate(3,ExpireDate);
                pst.setString(4,UniversityName);
                pst.executeUpdate();
                database.getConn().commit();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }else {
            System.out.println("No Student ID : " + stuID);
        }
    }
    private void setIssuedDate(){
        int date,month,year;
        Calendar c = Calendar.getInstance();
        System.out.println("IssueDate : ");
        System.out.print("Date : ");
        date = database.getInput().nextInt();
        System.out.print("Month (Int): ");
        month = database.getInput().nextInt();
        System.out.print("Year : ");
        year = database.getInput().nextInt();
        IssuedDate = java.sql.Date.valueOf(year+"-"+month+"-"+date);
        System.out.println(IssuedDate);
    }
    private void setExpireDate(){
        int date,month,year;
        System.out.println("ExpiredDate : ");
        System.out.print("Date : ");
        date = database.getInput().nextInt();
        System.out.print("Month (Int): ");
        month = database.getInput().nextInt();
        System.out.print("Year : ");
        year = database.getInput().nextInt();
        ExpireDate = java.sql.Date.valueOf(year+"-"+month+"-"+date);
        System.out.println(ExpireDate);
    }

}
