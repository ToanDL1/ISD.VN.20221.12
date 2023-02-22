package entity.Invoice;

public class Invoice {
       private entity.bike.bike bike;
       private int deposit;
       public Invoice(){

       }

       public Invoice(entity.bike.bike bike){
           this.bike = bike;
       }

       public entity.bike.bike getBike() {
           return bike;
       }

       public void setDesposit(int amount) {
           this.deposit = (int) (bike.getPrice() *0.4);
       }

       public int getDeposit() {
           return this.deposit;
       }

       public void saveInvoice(){
           
       }

	
}
