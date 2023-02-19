package entity.card;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.bike.bike;
import entity.db.EcoDB;

public class card {
    private String owner;
    private String cardCode;
    private int cvvCode;
    private String dateExpired;
    private int balance;
	public int getBalance() {
		return balance;
	}
	private void setBalance(int balance) {
		this.balance = balance;
	}
	public String getOwner() {
		return owner;
	}
	private void setOwner(String owner) {
		this.owner = owner;
	}
	public String getCardCode() {
		return cardCode;
	}
	private void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	public int getCvvCode() {
		return cvvCode;
	}
    private void setCvvCode(int cvvCode) {
		this.cvvCode = cvvCode;
	}
	
	 public String getDateExpired() {
		return dateExpired;
	}
	private void setDateExpired(String dateExpired) {
		this.dateExpired = dateExpired;
	}
	public static card getcard() throws SQLException {
	    	String sql = "select * from card";
	    	Statement stm = EcoDB.getConnetttion().createStatement();
			ResultSet res = stm.executeQuery(sql);
			card creditCard= new card();
			while(res.next()) {
				creditCard.setOwner(res.getString("owner"));
				creditCard.setCardCode(res.getString("cardCode"));
				creditCard.setCvvCode(res.getInt("cvvCode"));
				creditCard.setDateExpired(res.getString("dateExpired"));
				creditCard.setBalance(res.getInt("balance"));				
			}
			return creditCard;
	    }
	 public boolean checkCard(String owner, String cardCode, String cvvCode , String dateExpried) {
		 if(!this.owner.equals(owner) ||
		    this.getCvvCode() != Integer.parseInt(cvvCode)|| 
		    !this.cardCode.equals(cardCode)||
		    !this.dateExpired.equals(dateExpried))
			 {
			 return false;
		 }
		 return true;
	 }
	
}
