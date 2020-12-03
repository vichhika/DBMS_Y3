import java.sql.*;
import java.util.Scanner;
public class App {

    private static String dbURL;
    private static Connection conn;
    private static Scanner input = new Scanner(System.in);
    private static String username;
    private static String password;
    private static Login user = new Login();
    private static Recommandation movie = new Recommandation();

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
            if(user.verify(username, password, conn)){
                //Assign database and UserID into Recommandation object
                movie.initRecommandation(user.userID,conn);
                /**
                 * Display most popular movies to User
                 */
                System.out.println("---------------[ Most Popular Movies ]---------------");
                Display.moiveTitle(movie.mostPopular());
                /**
                 * Display most popular movies in Country
                 */
                System.out.println("------[ Most Popular Movies in Your Country ]--------");
                Display.moiveTitle(movie.mostPopularInCountry());
                /**
                 * Display Movies by Author
                 */
                System.out.println("---------------[ Movies by Author ]------------------");
                Display.moiveTitle(movie.byAuthor());
                /**
                 * Display Movies by the user with strongly similar preferences
                 */
                System.out.println("------------------[ Hot Movies ]---------------------");
                Display.moiveTitle(movie.bySimilarPreference());
                /**
                 * Display Movies by most users who liked the movie that you like
                 */
                //System.out.println("------------------[ Movies Most Likes ]---------------------");
                //Display.moiveTitle(movie.bySimilarMostLike());
                System.out.println("1. Rating movies"); //List all movies to provide user to like or dislike
                System.out.println("0. Logout");
                // Menu Input //
            }
            else System.out.println("Invalid Credential!");
        }
    }

    public static void main(String[] args) throws Exception {
        menu();
        /**
         * testing statement
         */
        // statement = conn.createStatement();
        // resultStatement = statement.executeQuery("SELECT TOP 3 Movie.Title,COUNT(Movie.Title) AS \"Like count\" "+
        // "FROM [User] INNER JOIN (Movie INNER JOIN [Like] ON Movie.MovieID = [Like].MovieID) ON [User].UserID = [Like].UserID "+
        // "WHERE [Like].LikeOrDislike = 'yes' and [User].UserID != 5 "+
        // "GROUP BY Movie.Title "+
        // "ORDER BY COUNT(Movie.Title) DESC; ");
        // while(resultStatement.next()){
        //     String result = resultStatement.getString(1);
        //     System.out.println(result);
        // }
    }
}
