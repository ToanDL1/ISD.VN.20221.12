package entity.db;

import java.sql.Connection;
import java.sql.DriverManager;

import utils.Configs;

public class EcoDB {
private static Connection conn;
	
	public static Connection getConnetttion() {
		if(conn != null) {
			return conn;
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(Configs.DB_URL, Configs.DB_USER, Configs.DB_PASS);
			System.out.println("succesfully");		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void main(String[] args) {
//		DB.getConnetttion();
	}
}
