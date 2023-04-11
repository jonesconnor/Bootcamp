package com.stackroute;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
    Connection  connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;

    public void databaseConnection() throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        FileReader fileReader = new FileReader("src/main/resources/demo.properties");
        Properties properties = new Properties();
        properties.load(fileReader);
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cgi_db",
                properties.getProperty("username"),properties.getProperty("password"));

//        statement = connection.createStatement();
//        resultSet = statement.executeQuery("select * from employees");
//        while (resultSet.next()){
//            System.out.println(resultSet.getInt("empid")+"   "+resultSet.getString("empname"));
//        }
//         preparedStatement = connection.prepareStatement("select * from employees");
//         resultSet = preparedStatement.executeQuery();
//         while(resultSet.next()){
//             System.out.println(resultSet.getString("empname"));
//         }
            String empname="Connor";
            String address="Cannada";
            String email = "connor@gmail.com";
          preparedStatement = connection.prepareStatement
                  ("insert into employees(empname,empaddress,email) values (?,?,?)");
           preparedStatement.setString(1,empname);
           preparedStatement.setString(2,address);
           preparedStatement.setString(3,email);
        int  result = preparedStatement.executeUpdate();

          if(result>0){
              System.out.println("value added");
          }



    }


    public static void main( String[] args ) throws SQLException, IOException, ClassNotFoundException {
        App app = new App();
        app.databaseConnection();
    }
}
