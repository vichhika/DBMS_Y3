import java.sql.SQLException;
import java.util.Scanner;
public class App {

    private static void menu(Database query) throws SQLException{
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.print(
                "-----Student Management-----\n"+
                "1. Search for a student by ID\n"+
                "2. Add a new student\n"+
                "3. Delete a student by ID\n"+
                "4. Update a student's information\n"+
                "5. Show all students\n"+
                "6. Show all courses attended by a student\n"+
                "7. Add a new course\n"+
                "8. Show all courses\n"+
                "9. Enroll a student in a course\n"+
                "0. Exit the program\n"+
                "Choose: "
            );
            switch (input.nextInt()) {
                case 1:
                    query.searchStudent();
                    break;
                case 2:
                    query.addStudent();
                    break;
                case 3:
                    query.delStudentID();
                    break;
                case 4:
                    query.studentUpdate();
                    input.nextLine();
                    break;
                case 5:
                    query.showAllStudent();
                    break;
                case 6:
                    query.showCourseAttended();
                    break;
                case 7:
                    query.addCourse();
                    break;
                case 8:
                    query.showAllCourse();
                    break;
                case 9:
                    query.stuEnrollCourse();
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
    public static void main(String[] args) throws Exception {
        Database conn = new Database();
        if(conn.dbStatus) menu(conn);
    }
}
