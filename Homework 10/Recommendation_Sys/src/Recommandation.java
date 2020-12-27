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
        return sqlQuery("MATCH (m:Movie)-[r:act{like:\"yes\"}]-(u:User) WHERE NOT exists {MATCH (m)-[r:act]-(:User{id:'"+this.UserID+"'})} return m.Title AS `Title`,count(r) AS `Like`  ORDER BY  Like DESC LIMIT 3");
    }

    protected ArrayList<String> mostPopularInCountry() throws SQLException {
        return sqlQuery("call {MATCH (u:User{id:\""+this.UserID+"\"}) return u.Country AS `Country`} MATCH (m:Movie)-[r:act{like:\"yes\"}]-(u:User) WHERE NOT exists {MATCH (m)-[r:act]-(:User{id:\""+this.UserID+"\"})} AND u.Country = Country return m.Title AS `Title`,count(r) AS `Like` ORDER BY  Like DESC LIMIT 3");
    }

    protected ArrayList<String> byAuthor() throws SQLException {
        return sqlQuery("MATCH (m:Movie)<-[:write]-(:Author)-[:write]->(:Movie)<-[:act{like:\"yes\"}]-(:User{id:\""+this.UserID+"\"}) WHERE NOT exists {(m)-[:act]-(:User{id:\""+this.UserID+"\"})} return m.Title AS Title LIMIT 5");
    }

    protected ArrayList<String> bySimilarPreference() throws SQLException {
        return sqlQuery("call{MATCH(a:User{id:\""+this.UserID+"\"})-[:act{like:\"yes\"}]->(m:Movie)<-[:act{like:\"yes\"}]-(b:User) return b,count(m) as vote order by vote DESC  limit 1} match (b)-[:act{like:\"yes\"}]->(m:Movie) where NOT exists {(m)<-[:act]-(:User{id:\""+this.UserID+"\"})} return m.Title AS Title LIMIT 5");
    }

    protected ArrayList<String> bySimilarMostLike() throws SQLException {
        return sqlQuery("CALL {MATCH (u2:User)-[a1:act{like:\"yes\"}]->(m1:Movie)<-[:act{like:\"yes\"}]-(u1:User{id:\""+this.UserID+"\"}) return m1.Title AS Title,count(a1) ORDER BY count(a1) DESC LIMIT 3}  MATCH (m:Movie)<-[:act{like:\"yes\"}]-(u:User)-[:act{like:\"yes\"}]->(m3:Movie)<-[a2:act{like:\"yes\"}]-(:User) WHERE m.Title in Title AND NOT Exists {(m3)<-[:act]-(:User{id:\""+this.UserID+"\"})} return m3.Title AS Title,count(a2) AS Like ORDER BY Like DESC LIMIT 3");
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
