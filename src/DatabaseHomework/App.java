package DatabaseHomework;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class App {
    private Student student;
    private Course course;
    private Database database;
    private StudentCard studentCard;
    private Homework homework;
    public void menu() throws SQLException {
        Scanner input = new Scanner(System.in);
        init();
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
                            "10. Update student care\n"+
                            "11. Add Homework\n"+
                            "0. Exit the program\n"+
                            "Choose: "
            );
            switch (input.nextInt()) {
                case 1:
                    System.out.print("ID : ");
                    student.DisplayStudentInfo(student.searchStudent(input.nextInt()));
                    break;
                case 2:
                    System.out.print("Student Name : ");input.next();
                        student.setStudentName(input.nextLine());
                    System.out.print("Gender : ");
                        student.setGender(input.nextLine().charAt(0));
                    System.out.print("PhoneNo : ");
                        student.setPhoneNo(input.nextLine());
                    student.addStudent();
                    break;
                case 3:
                    student.delStudentID();
                    break;
                case 4:
                    student.studentUpdate();
                    input.nextLine();
                    break;
                case 5:
                    student.showAllStudent();
                    break;
                case 6:
                    course.showCourseAttended();
                    break;
                case 7:
                    course.addCourse();
                    break;
                case 8:
                    course.showAllCourse();
                    break;
                case 9:
                    course.stuEnrollCourse();
                    break;
                case 10 :
                    System.out.print("ID : ");
                    studentCard.UpdateCard(input.nextInt());
                    break;
                case 11 :
                    System.out.print("Course ID : ");
                    homework.addHomework(input.nextInt());
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Wrong input!");
                    break;
            }
       }
    }
    private void init (){
        database = new Database();
        student = new Student();
        course = new Course();
        studentCard = new StudentCard();
        homework = new Homework();

        student.setDatabase(database);
        student.setCourse(course);

        course.setDatabase(database);
        course.setStudent(student);

        studentCard.setDatabase(database);
        studentCard.setStudent(student);

        homework.setDatabase(database);
        homework.setCourse(course);
    }
}
