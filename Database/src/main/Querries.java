package main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Querries {
	
	public static ResultSet getOving(String type, Connection con){
		ResultSet ovelse;
		String sqlEnum = "";
		boolean first = true;
		
		try{
			Statement s = con.createStatement();
			ResultSet result = s.executeQuery("SELECT ovelse_id FROM " + "ovelsestype WHERE type='"+type+"'");
			if(result.next()){
				sqlEnum = "id=" + result.getString("ovelse_id");
			}
			while(result.next()){
				sqlEnum += " OR id= "+result.getString("ovelse_id");
			}
			ovelse = s.executeQuery("SELECT * FROM Ovelse WHERE "+ sqlEnum);
		}catch(SQLException e){
//			System.err.println(e);
			e.printStackTrace();
			return null;
		}
		
		return ovelse;
	}

}
