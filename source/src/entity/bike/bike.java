package entity.bike;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.db.EcoDB;
import entity.parking.parking;

public class bike {
    private int id;
    private int idParking;
    private int price;
    private int status;
    private String img;
	public bike(int id, int idParking, int price, int status, String img) {
		super();
		this.id = id;
		this.idParking = idParking;
		this.price = price;
		this.status = status;
		this.img = img;
	}
	
	public bike(int id, int price, String img) {
		super();
		this.id = id;
		this.price = price;
		this.img = img;
	}

	public bike() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdParking() {
		return idParking;
	}
	public void setIdParking(int idParking) {
		this.idParking = idParking;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
    public static ArrayList<bike> getBikeInParking(int id) throws SQLException{
    	    ArrayList<bike> bikeList = new ArrayList<>();
			String sql = "select bike.id,price,bike.img from bike "
					+ "inner join parking on bike.idParking = parking.id where parking.id =" 
					+ id +";";
			Statement stm = EcoDB.getConnetttion().createStatement();
			ResultSet res = stm.executeQuery(sql);
			while(res.next()) {
		    	 bikeList.add(new bike( res.getInt("id"),
		    			               res.getInt("price"),
		    			               res.getString("img")
		    			        )
		    			);	
            }
			return bikeList;
  }
}