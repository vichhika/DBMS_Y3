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
    private static Update update = new Update();

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
        short attempt = 3;
        String movieID;
        if(dbConnect()){
            while (attempt != 0) {
                System.out.println("____Login____");
                System.out.print("Username:");
                username = input.nextLine();
                System.out.print("Password:");
                password = input.nextLine();
                if(user.verify(username, password, conn)){
                    //Assign database and UserID into Recommandation object
                    movie.initRecommandation(user.getUserID(),conn);
                    update.initUpdate(user.getUserID(), conn);
                    while (attempt != 0) {
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
                        System.out.println("------------------[ Movies Most Likes ]---------------------");
                        Display.moiveTitle(movie.bySimilarMostLike());
                        // Menu Input //
                        System.out.println("1. Rating movies"); //List all movies to provide user to like or dislike
                        System.out.println("0. Logout");
                        System.out.print("Choose:");
                        switch (input.nextLine()) {
                        case "1":
                            Display.allMoive(update.movieList());
                            System.out.print("Input MovieID:");
                            movieID = input.nextLine();
                            if(update.movieCheck(movieID)){
                                System.out.print("Do you like it? [y/n]:");
                                switch (input.nextLine()) {
                                    case "y":
                                        update.updateUser(movieID, "yes");
                                        break;
                                    case "n":
                                        update.updateUser(movieID, "no");
                                        break;
                                    default:
                                        System.out.println("Input incorrectly!");
                                        break;
                                }
                            }else System.out.println("Movie Not Found!");
                            break;
                        case "0":
                            attempt = 0;
                            break;
                        default:
                            System.out.println("Input incorrectly!");
                            break;
                        }
                    }
                }
                else {
                    System.out.println("Invalid Credential!");
                    attempt--;
                }
            }
        }
        conn.close();   
    }   

    public static void main(String[] args) throws Exception {
        menu();
    }
}
