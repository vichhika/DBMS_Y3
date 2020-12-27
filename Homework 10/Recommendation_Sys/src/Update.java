import java.util.ArrayList;
import java.sql.*;

public class Update {
    private String UserID;
    private Connection conn;
    private Statement statement;
    private PreparedStatement preState;
    private ResultSet rSet;
    private ArrayList<String> list = new ArrayList<>();

    public void initUpdate(String userID, Connection Conn){
        this.UserID = userID;
        this.conn = Conn;
    }

    protected  ArrayList<String> movieList() throws SQLException{
        list.clear();
        statement = conn.createStatement();
        rSet = statement.executeQuery("SELECT MovieID , Title FROM Movie");
        while (rSet.next()) {
            list.add(rSet.getString(1));
            list.add(rSet.getString(2));
        }
        return list;
    }

    protected boolean movieCheck(String movieID){
        boolean status;
        try {
            preState = conn.prepareStatement("SELECT MovieID FROM Movie WHERE MovieID = ?");
            preState.setString(1, movieID);
            rSet = preState.executeQuery();
            if(rSet.next()) status = true;
            else status = false;
        } catch (SQLException e) {
            System.out.println("Invalid Input!");
            status = false;
        }
        return status;
    }

    protected void updateUser(String movieID, String like) throws SQLException{
        preState = conn.prepareStatement("SELECT Movie.MovieID , Title FROM Movie INNER JOIN [Like] ON [Like].MovieID = Movie.MovieID WHERE UserID=? AND Movie.MovieID = ?");
        preState.setString(1, UserID);
        preState.setString(2, movieID);
        rSet = preState.executeQuery();
        if(rSet.next()){
            preState = conn.prepareStatement("UPDATE [Like] SET LikeOrDislike = ? WHERE UserID = ? AND MovieID = ? ");
            preState.setString(1, like);
            preState.setString(2, UserID);
            preState.setString(3, movieID);
            preState.executeUpdate();
        }
        else{
            preState = conn.prepareStatement("INSERT INTO [Like] VALUES (?,?,?)");
            preState.setString(1, UserID);
            preState.setString(2, movieID);
            preState.setString(3, like);
            preState.execute();
        }
        System.out.println("Successfully!");
    }
}
