package entity.bike;

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
    
}
