package utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.db.EcoDB;

public class DbCheck {
      public static boolean checkRentbike() throws SQLException {
    	  String sql = "SELECT COUNT(status) as check_rent "
    	  		+ "FROM rent_bike "
    	  		+ "WHERE status = 1 " +";";
			Statement stm = EcoDB.getConnetttion().createStatement();
			ResultSet res = stm.executeQuery(sql);
			int count = -1;
			while(res.next()){
				 count = res.getInt("check_rent");
			}
			
			System.out.println(count);
			if(count == 1) return true;
			
			return false;
			
      }
}
