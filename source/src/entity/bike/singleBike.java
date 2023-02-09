package entity.bike;

public class singleBike extends bike {
	private String name;

	public singleBike() {
		super();
		// TODO Auto-generated constructor stub
	}

	public singleBike(int id, int idParking, int price, int status, String img) {
		super(id, idParking, price, status, img);
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
