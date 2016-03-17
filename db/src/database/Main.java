package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main{
	public static Connection Connect(){
		try{
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no/andrris_trening","andrris_db1", "cdji2005");
			System.out.println("connection established");
			return con;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public static void doSomething(String doThis, Connection con)
	{
		try
		{
			Statement s = con.createStatement();
			s.executeUpdate(doThis);
			System.out.println("table created");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		Connection con = Connect();
		// doSomething(state, con);

	}

}
