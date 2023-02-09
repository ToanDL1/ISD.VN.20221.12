package entity.bike;

public class electricBike extends bike {
   private String name;
   private int battery;
public electricBike(int id, int idParking, int price, int status, String img, String name, int battery) {
	super(id, idParking, price, status, img);
	this.name = name;
	this.battery = battery;
}
public electricBike(int id, int idParking, int price, int status, String img) {
	super(id, idParking, price, status, img);
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getBattery() {
	return battery;
}
public void setBattery(int battery) {
	this.battery = battery;
}
   
}
