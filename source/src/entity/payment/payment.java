package entity.payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.db.EcoDB;

public class payment {
     int bikeid;
     int time;
     int postage;
     int refund;
     String img;
	public payment(int bikeid, int time, int postage, int refund ,String img) {
		super();
		this.bikeid = bikeid;
		this.time = time;
		this.postage = postage;
		this.refund = refund;
		this.img = img;
	}
	
	

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	public int getBikeid() {
		return bikeid;
	}

	public void setBikeid(int bikeid) {
		this.bikeid = bikeid;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getPostage() {
		return postage;
	}

	public void setPostage(int postage) {
		this.postage = postage;
	}

	public int getRefund() {
		return refund;
	}

	public void setRefund(int refund) {
		this.refund = refund;
	}
    
    public static payment getPayment() throws SQLException {
    	payment paymentBike = new payment();
		String sql = "select rent_bike.bikeId , startAt , endAt , price, img "
				    + "from rent_bike, bike "
				     + "where bike.id= rent_bike.bikeId and rent_bike.status = 1 ;";
		Statement stm = EcoDB.getConnetttion().createStatement();
		ResultSet res = stm.executeQuery(sql);
		while(res.next()) {
			  paymentBike.setBikeid(res.getInt("bikeId"));
			  int amount = (int) (res.getInt("price") * 0.4);
			  paymentBike.setRefund(amount);
			  paymentBike.setTime(0);
			  int postage = paymentBike.getTime() * 30000;
			  paymentBike.setPostage(postage);
			  paymentBike.setImg(res.getString("img"));
			}
		return paymentBike;
	}



	public payment() {
		super();
	}
     
}
