import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login {
    private String userID;
    private Statement statement;
    private ResultSet rSet;

    public boolean verify(String username,String password,Connection conn) throws SQLException{
        statement = conn.createStatement();
        rSet = statement.executeQuery("SELECT UserID FROM \"User\" WHERE Username='"+username+"' AND Password='"+password+"'");
        if(rSet.next()){
            userID = rSet.getString("UserID");
            return true;
        }else return false;
    } 
    public String getUserID(){
        return this.userID;
    }
}
