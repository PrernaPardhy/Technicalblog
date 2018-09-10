package blog.common;

import java.sql.*;

public class JDBCConnector {
    private static final String DB_Driver="org.postgresql.Driver";
    private static final String DB_URL="jdbc:postgresql://localhost:5432/technicalblog";
    private static final String USER="postgres";
    private static final String PASSWORD="postgres";
    Connection connection=null;
    Statement statement=null;

    private JDBCConnector(){
        try {
            Class.forName(DB_Driver);
            connection= DriverManager.getConnection(DB_URL,USER,PASSWORD);
            statement=connection.createStatement();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static JDBCConnector jdbcConnector =new JDBCConnector();

    public static JDBCConnector getInstance(){
        return jdbcConnector;

    }

    public ResultSet executeQuery(final String query){
        try {
            statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    return null;
    }

    public void execute(String query){
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args){
        JDBCConnector jdbcConnector=JDBCConnector.getInstance();
       // jdbcConnector.execute("create database technicalblog");
     //   jdbcConnector.execute("create table Posts (id SERIAL PRIMARY KEY ,title varchar, body varchar, data timestamp)");


    }
}
