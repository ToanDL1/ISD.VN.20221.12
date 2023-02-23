package entity.Invoice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import entity.bike.bike;
import entity.db.EcoDB;


public class Invoice {
       private bike bikeInvoice;
       private int deposit;
       private long totalTime;
       private long  moneyRent;
       
       
   	 public bike getBikeInvoice() {
		return bikeInvoice;
	}

	public void setBikeInvoice(bike bikeInvoice) {
		this.bikeInvoice = bikeInvoice;
	}

	public int getDeposit() {
		return deposit;
	}

	public void setDeposit() {
		this.deposit = (int) (this.bikeInvoice.getPrice() * 0.4);
	}

	public long getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(long totalTime) {
		this.totalTime = totalTime;
	}

	public long getMoneyRent() {
		return moneyRent;
	}

	public Invoice(bike bikeInvoice) {
		super();
		this.bikeInvoice = bikeInvoice;
	}

	public void setMoneyRent(long l) {
		this.moneyRent = l;
	}
       
	public Invoice(){

       }



      
	public void saveInvoice(){
           
       }
       
	public static Invoice getInvoice() throws SQLException, ParseException {
    	String sql = "select * from rent_bike where status = 1 ;";
       	Statement stm = EcoDB.getConnetttion().createStatement();
   		ResultSet res = stm.executeQuery(sql);
   		Invoice invoice = new Invoice();
   	   while(res.next()) {
   		   invoice.setBikeInvoice(bike.getBikeById(res.getInt("bikeId")));
   		   invoice.setDeposit();
   		   
   		   String startRent = res.getString("startAt");
   		   String endRent     = res.getString("endAt");
   		   
   		   DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   		   Date date1= simpleDateFormat.parse(startRent);
   		   Date date2 = simpleDateFormat.parse(endRent);
   		   
   		   long getDiff = date2.getTime() - date1.getTime();
   		   long getDiffHour = TimeUnit.MILLISECONDS.toHours(getDiff);
   		
   		   
   		   invoice.setTotalTime(getDiffHour);
   		   invoice.setMoneyRent(getDiffHour * 30000);
   		   
   		   
   	   }
        return invoice;
   	}
   		
   		
}

	
