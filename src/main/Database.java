import java.sql.*;

/**
 * Denne klassen oppretter databasen og tar seg av kommunikasjon mellom app-en og databasen.
 */

public class Database
{
    /**
     * Constructor for objects of class database
     */
    public void init()
    {
        
    }

    /**
     * @return String Connected!
     */
    public String connection(){
        return "Connected!";
    }
    public Connection getConnection() throws SQLException {
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", this.userName);
        connectionProps.put("password", this.password);
    
        if (this.dbms.equals("mysql")) {
            conn = DriverManager.getConnection(
                       "jdbc:" + this.dbms + "://" +
                       this.serverName +
                       ":" + this.portNumber + "/",
                       connectionProps);
                    } else if (this.dbms.equals("derby")) {
            conn = DriverManager.getConnection(
                       "jdbc:" + this.dbms + ":" +
                       this.dbName +
                       ";create=true",
                       connectionProps);
        }
        System.out.println("Connected to database");
        return conn;
    }   
}
