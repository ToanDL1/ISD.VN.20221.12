package controller;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;

import common.exception.InvalidCardException;
import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import entity.bike.bike;
import entity.db.EcoDB;
import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;
import subsystem.InterbankInterface;
import subsystem.InterbankSubsystem;


/**
 * This {@code PaymentController} class control the flow of the payment process
 * in our AIMS Software.
 * 
 * @author hieud
 *
 */
public class PaymentController extends BaseController {

	/**
	 * Represent the card used for payment
	 */
	private CreditCard card;

	/**
	 * Represent the Interbank subsystem
	 */
	private InterbankInterface interbank;

	/**
	 * Validate the input date which should be in the format "mm/yy", and then
	 * return a {@link java.lang.String String} representing the date in the
	 * required format "mmyy" .
	 * 
	 * @param date - the {@link java.lang.String String} represents the input date
	 * @return {@link java.lang.String String} - date representation of the required
	 *         format
	 * @throws InvalidCardException - if the string does not represent a valid date
	 *                              in the expected format
	 */
	private String getExpirationDate(String date) throws InvalidCardException {
		String[] strs = date.split("/");
		if (strs.length != 2) {
			throw new InvalidCardException();
		}

		String expirationDate = null;
		int month = -1;
		int year = -1;

		try {
			month = Integer.parseInt(strs[0]);
			year = Integer.parseInt(strs[1]);
			if (month < 1 || month > 12 || year < Calendar.getInstance().get(Calendar.YEAR) % 100 || year > 100) {
				throw new InvalidCardException();
			}
			expirationDate = strs[0] + strs[1];

		} catch (Exception ex) {
			throw new InvalidCardException();
		}

		return expirationDate;
	}

	/**
	 * Pay order, and then return the result with a message.
	 * 
	 * @param amount         - the amount to pay
	 * @param contents       - the transaction contents
	 * @param cardNumber     - the card number
	 * @param cardHolderName - the card holder name
	 * @param expirationDate - the expiration date in the format "mm/yy"
	 * @param securityCode   - the cvv/cvc code of the credit card
	 * @return {@link java.util.Map Map} represent the payment result with a
	 *         message.
	 */
	public Map<String, String> payOrder(int amount, String contents, String cardNumber, String cardHolderName,
			String expirationDate, String securityCode) {
		Map<String, String> result = new Hashtable<String, String>();
		result.put("RESULT", "PAYMENT FAILED!");
		
		try {
			System.out.println("0");
			this.card = new CreditCard(cardNumber, cardHolderName, Integer.parseInt(securityCode),
					getExpirationDate(expirationDate));
			
//			this.interbank = new InterbankSubsystem();
//			try {
//				System.out.println("1");
//				PaymentTransaction transaction = interbank.payOrder(card, amount, contents);
//				System.out.println("2");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//			System.out.println("chay đen try");
			result.put("RESULT", "PAYMENT SUCCESSFUL!");
			result.put("MESSAGE", "Congratulations Succesffully payment!");
			
		} catch (PaymentException | UnrecognizedException ex) {
			System.out.println("chay den payment controller catch: "+ex.getMessage() );
			result.put("MESSAGE", ex.getMessage());
			ex.printStackTrace();
		}catch(Exception e1) {
			e1.printStackTrace();
		}
		return result;
	}
	
	public void updateRentBike(bike bikeRent) throws SQLException {
	    String sql = "insert into rent_bike (bikeId,status) values( "
	    		      +bikeRent.getId()+ ",1);";
		Statement stm = EcoDB.getConnetttion().createStatement();
		int res = stm.executeUpdate(sql);
		System.out.println(res);
    }
	
	public void updateRentBikeBack() throws SQLException {
		 String sql = "update rent_bike set status = 0 where status = 1";
		 Statement stm = EcoDB.getConnetttion().createStatement();
	     int res = stm.executeUpdate(sql);
	}

}