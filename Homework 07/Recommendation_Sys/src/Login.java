import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login {
    protected String userID;

    public boolean verify(String username,String password,Statement Statement,ResultSet resultStatement) throws SQLException{
        resultStatement = Statement.executeQuery("SELECT UserID FROM \"User\" WHERE Username='"+username+"' AND Password='"+password+"'");
        if(resultStatement.next()){
            userID = resultStatement.getString("UserID");
            return true;
        }else return false;
    } 
}
