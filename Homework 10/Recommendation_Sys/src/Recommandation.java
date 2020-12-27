import java.sql.*;
import java.util.ArrayList;

public class Recommandation {
    private String UserID;
    private Connection conn;
    private Statement statement;
    private ResultSet rSet;
    private ArrayList<String> list = new ArrayList<>();

    public void initRecommandation(String userID, Connection Conn) {
        this.UserID = userID;
        this.conn = Conn;
    }

    protected ArrayList<String> mostPopular() throws SQLException {
        return sqlQuery("SELECT TOP 3 Movie.Title,COUNT(Movie.Title) AS \"Like count\" "
                + "FROM [User] INNER JOIN (Movie INNER JOIN [Like] ON Movie.MovieID = [Like].MovieID) ON [User].UserID = [Like].UserID "
                + "WHERE [Like].LikeOrDislike = 'yes' and [User].UserID != " + UserID + " "
                + "and Movie.MovieID NOT IN (SELECT Movie.MovieID FROM [User] INNER JOIN (Movie INNER JOIN [Like] ON Movie.MovieID = [Like].MovieID) ON [User].UserID = [Like].UserID WHERE [User].UserID ="
                + UserID + ")" + "GROUP BY Movie.Title " + "ORDER BY COUNT(Movie.Title) DESC; ");
    }

    protected ArrayList<String> mostPopularInCountry() throws SQLException {
        return sqlQuery("SELECT Title, COUNT(Movie.MovieID) AS LikeAmount "
                + "FROM [User] INNER JOIN ([Like] INNER JOIN Movie ON [Like].MovieID = Movie.MovieID) ON [User].UserID = [Like].UserID "
                + "WHERE Country IN ( SELECT Country FROM [User] WHERE UserID = " + UserID + " "
                + ") AND [Like].UserID != " + UserID + " AND LikeOrDislike = 'yes' "
                + "AND Movie.MovieID NOT IN (SELECT MovieID FROM [Like] WHERE UserID=" + UserID + ")"
                + "GROUP BY Movie.Title " + "ORDER BY LikeAmount DESC; ");
    }

    protected ArrayList<String> byAuthor() throws SQLException {
        return sqlQuery("SELECT Movie.Title "
                + "FROM  Movie INNER JOIN (Author INNER JOIN MovieDetail ON Author.AuthorID = MovieDetail.AuthorID) ON Movie.MovieID = MovieDetail.MovieID "
                + "WHERE Author.AuthorID IN ( SELECT Author.AuthorID FROM  [User] INNER JOIN([Like] INNER JOIN (Movie INNER JOIN (Author INNER JOIN MovieDetail ON Author.AuthorID = MovieDetail.AuthorID) ON Movie.MovieID = MovieDetail.MovieID) ON [Like].MovieID = Movie.MovieID) ON [User].UserID = [Like].UserID WHERE [User].UserID = "
                + UserID + " AND LikeOrDislike = 'yes' GROUP BY Author.AuthorID) "
                + "AND Movie.MovieID NOT IN ( SELECT Movie.MovieID "
                + "FROM [User] INNER JOIN (Movie INNER JOIN [Like] ON Movie.MovieID = [Like].MovieID) ON [User].UserID = [Like].UserID "
                + "WHERE [User].UserID = " + UserID + " AND LikeOrDislike = 'yes')");
    }

    protected ArrayList<String> bySimilarPreference() throws SQLException {
        return sqlQuery(
                "SELECT Title FROM Movie INNER JOIN [Like] ON [Like].MovieID = Movie.MovieID WHERE UserID = (SELECT TOP 1 UserID FROM [Like] WHERE MovieID IN (SELECT MovieID FROM [Like] WHERE UserID="
                        + UserID + " AND LikeOrDislike = 'yes') AND UserID != " + UserID
                        + " GROUP BY UserID HAVING COUNT(UserID) = (SELECT COUNT(*) FROM [Like] WHERE UserID=" + UserID
                        + " AND LikeOrDislike = 'yes')) AND UserID = (SELECT TOP 1 UserID FROM [Like] WHERE MovieID IN (SELECT MovieID FROM [Like] WHERE UserID="
                        + UserID + " AND LikeOrDislike = 'no') AND UserID != " + UserID
                        + " GROUP BY UserID HAVING COUNT(UserID) = (SELECT COUNT(*) FROM [Like] WHERE UserID=" + UserID
                        + " AND LikeOrDislike = 'no')) AND LikeOrDislike != 'no'  AND Movie.MovieID NOT IN (SELECT MovieID FROM [Like] WHERE UserID="
                        + UserID + ")");
    }

    protected ArrayList<String> bySimilarMostLike() throws SQLException {
        return sqlQuery(
                "SELECT Title FROM Movie INNER JOIN [Like] ON [Like].MovieID = Movie.MovieID WHERE UserID IN (SELECT UserID FROM [Like] WHERE MovieID IN (SELECT TOP 1 MovieID FROM [Like] WHERE MovieID IN (SELECT MovieID FROM [Like] WHERE UserID="
                        + UserID
                        + " AND LikeOrDislike = 'yes') AND LikeOrDislike = 'yes' GROUP BY MovieID)) AND LikeOrDislike = 'yes' AND Movie.MovieID NOT IN (SELECT MovieID FROM [Like] WHERE UserID="
                        + UserID + " AND LikeOrDislike = 'yes') GROUP BY Title");
    }

    private ArrayList<String> sqlQuery(String state) throws SQLException {
        list.clear();
        statement = conn.createStatement();
        rSet = statement.executeQuery(state);
        while (rSet.next()) {
            list.add(rSet.getString(1));
        }
        return list;
    }
}
