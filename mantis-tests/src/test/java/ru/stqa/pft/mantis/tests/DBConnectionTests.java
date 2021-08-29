package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.UsersData;
import ru.stqa.pft.mantis.model.Users;

import java.sql.*;

public class DBConnectionTests {

    @Test
    public void testDBConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?user=root&password=");
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select id, username from mantis_user_table");
            Users users = new Users();
            while (resultSet.next()){
                users.add(new UsersData()
                        .withId(resultSet.getInt("id"))
                        .withUsername(resultSet.getString("username")));
            }
            resultSet.close();
            statement.close();
            conn.close();
            System.out.println(users);


        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}