package entity.bike;

public class doubleBike extends bike{
	private String name;

	public doubleBike() {
		super();
		// TODO Auto-generated constructor stub
	}

	public doubleBike(int id, int idParking, int price, int status, String img) {
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
