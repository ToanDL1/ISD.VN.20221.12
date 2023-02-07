package entity.parking;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.db.EcoDB;

public class parking {
    private int id;
    private String address;
    private int maxSlot;
    private String description;
    private String img;
    
	public parking(int id, String address, int maxSlot, String description, String img) {
		super();
		this.id = id;
		this.address = address;
		this.maxSlot = maxSlot;
		this.description = description;
		this.img = img;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getMaxSlot() {
		return maxSlot;
	}
	public void setMaxSlot(int maxSlot) {
		this.maxSlot = maxSlot;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	} 
	public static ArrayList<parking> getAllParking() throws SQLException{
		ArrayList<parking> parkingList = new ArrayList<>();
		String sql = "select * from parking";
		Statement stm = EcoDB.getConnetttion().createStatement();
		ResultSet res = stm.executeQuery(sql);
	    while(res.next()) {
	    	parkingList.add(new parking(res.getInt("id"),
	    			        res.getString("address"),
	    			        res.getInt("maxSlot"),
	    			        res.getString("description"),
	    			        res.getString("img")
	    			        )
	    			);
	    }
		return parkingList;
		
	}
    
}
