package DatabaseHomework;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        App app = new App();
        try{
            app.menu();
        }catch (SQLException e){
            System.out.println("Error!");
        }
    }
}
