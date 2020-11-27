import java.sql.*;
import java.util.Scanner;

public class App {

    private static String dbURL;
    private static Connection conn;
    private static PreparedStatement preStatement;
    private static ResultSet resultStatement;
    private static Scanner input = new Scanner(System.in);
    private static String username;
    private static String password;
    private static Login user = new Login();

    private static boolean dbConnect(){
        try {
            /**
             * edit databaseName, user ,and password
             */
            dbURL="jdbc:sqlserver://localhost:1433;databaseName=RecommendationSys;user=sa;password=vichhika@2020";
            conn = DriverManager.getConnection(dbURL);
            return true;
        } catch (Exception e) {
            System.out.println("Database connection error !");
            return false;
        }
    }

    private static void menu() throws Exception{
        if(dbConnect()){
            System.out.println("____Login____");
            System.out.print("Username:");
            username = input.nextLine();
            System.out.print("Username:");
            password = input.nextLine();
            if(user.verify(username, password, conn.createStatement(), resultStatement)){
                
            }
        }
    }

    public static void main(String[] args) throws Exception {
        menu();
    }
}
