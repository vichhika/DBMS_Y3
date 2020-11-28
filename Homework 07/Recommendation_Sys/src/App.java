import java.sql.*;
import java.util.Scanner;

public class App {

    private static String dbURL;
    private static Connection conn;
    private static PreparedStatement preStatement;
    private static ResultSet resultStatement;
    private static Statement statement;
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
            System.out.print("Password:");
            password = input.nextLine();
            if(user.verify(username, password, conn.createStatement(), resultStatement)){
                System.out.println("_____Movie_Menu_____");
                /**
                 * Automatically listing movies following by requirement of menu 1-5
                 */
                System.out.println("1. Most popular movies");
                System.out.println("2. Most popular movies in your country");
                System.out.println("3. Movies by author");
                System.out.println("4. Hot movies");
                System.out.println("5. Movies that most users like");
                System.out.println("6. Rating movies"); //List all movies to provide user to like or dislike
                System.out.println("0. Logout");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        menu();

        /**
         * testing statement
         */
        // statement = conn.createStatement();
        // resultStatement = statement.executeQuery("Statement");
        // while(resultStatement.next()){
        //     String result = resultStatement.getString("parameter");
        //     System.out.println(result);
        //}
    }
}
