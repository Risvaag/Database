package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main 
{
	
	public static Connection Connect()
	{
		try
		{	
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
		String state = "CREATE TABLE resultat (trening_id INTEGER NOT NULL,ovelse_id INTEGER NOT NULL,belastning INTEGER, antall_set INTEGER, antall_rep INTEGER, logg_id INTEGER,FOREIGN KEY (trening_id) REFERENCES Trening (id),FOREIGN KEY (ovelse_id) REFERENCES Ovelse (id),FOREIGN KEY (logg_id) REFERENCES Resultatlogg(id),PRIMARY KEY (ovelse_id, trening_id))";
		doSomething(state, con);
		
	}

}
