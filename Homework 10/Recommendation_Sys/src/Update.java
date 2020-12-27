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
        rSet = statement.executeQuery("MATCH (m:Movie) return m.id AS ID,m.Title AS Title;");
        while (rSet.next()) {
            list.add(rSet.getString(1));
            list.add(rSet.getString(2));
        }
        return list;
    }

    protected boolean movieCheck(String movieID){
        boolean status;
        try {
            preState = conn.prepareStatement("MATCH (m:Movie{id:?}) return m.Title as Title");
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
        preState = conn.prepareStatement("MATCH (:User{id:?})-[:act]-(m:Movie{id:?}) return m.id AS ID,m.Title AS Title");
        preState.setString(1, UserID);
        preState.setString(2, movieID);
        rSet = preState.executeQuery();
        if(rSet.next()){
            preState = conn.prepareStatement("MATCH (:User{id:?})-[a:act]-(m:Movie{id:?}) SET a.like = ?");
            preState.setString(3, like);
            preState.setString(1, UserID);
            preState.setString(2, movieID);
            preState.executeUpdate();
        }
        else{
            preState = conn.prepareStatement("MATCH (u:User{id:?}),(m:Movie{id:?}) CREATE (u)-[:act{like:?}]->(m)");
            preState.setString(1, UserID);
            preState.setString(2, movieID);
            preState.setString(3, like);
            preState.execute();
        }
        System.out.println("Successfully!");
    }
}
