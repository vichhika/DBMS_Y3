import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    private String userID;
    private PreparedStatement preStatement;
    private ResultSet rSet;

    public boolean verify(String username,String password,Connection conn) throws SQLException{
        preStatement =  conn.prepareStatement("MATCH (u:User{Username:?,Password:?}) return u.id AS `UserID`");
        preStatement.setString(1, username);
        preStatement.setString(2, password);
        rSet = preStatement.executeQuery();
        if(rSet.next()){
            userID = rSet.getString("UserID");
            return true;
        }else return false;
    } 
    public String getUserID(){
        return this.userID;
    }
}
