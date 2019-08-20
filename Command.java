import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Command {
	private String name;
	private Product product;
	private int amount;
	private int coast;
	private Date date;
	private boolean status;

	public Product getProduct() {
		return product;
	}
	public int getAmount() {
		return amount;
	}
	public String getName() {
		return name;
	}
	public int getCoast() {
		return coast;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public void setCoast(int coast) {
		this.coast = coast;
	}
	public void setDate(String date) throws ParseException {
		this.date = new SimpleDateFormat("dd.MM.yyyy").parse(date);
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public void printObj() {
		System.out.print("Name: " + name + "\n");
		System.out.print("Product: ");
		System.out.print(product.getName() + "\n");
		System.out.print("Amount: " + amount + "\n");
		System.out.print("Coast: " + coast + "\n");
		if (date != null)
			System.out.print("Date: " + new SimpleDateFormat("dd.MM.yyyy").format(date) + "\n");
		System.out.print("Status: " + status + "\n");
	}
}