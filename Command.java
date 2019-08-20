//import java.util.Date;
//import java.text.SimpleDateFormat;
//import java.text.ParseException;

public abstract class Command {
	protected String name;
	protected Product product;
	protected String status;

	public Product getProduct() {
		return product;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	/*public void printObj() {
		System.out.print("Name: " + name + "\n");
		System.out.print("Product: ");
		System.out.print(product.getName() + "\n");
		System.out.print("Amount: " + amount + "\n");
		System.out.print("Coast: " + coast + "\n");
		if (date != null)
			System.out.print("Date: " + new SimpleDateFormat("dd.MM.yyyy").format(date) + "\n");
		System.out.print("Status: " + status + "\n");
	}*/
}