import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class PurchaseDemand extends Command {
	private int amount;
	private int coast;
	private Date date;

	public int getAmount() {
		return amount;
	}
	public int getCoast() {
		return coast;
	}
	public Date getDate() {
		return date;
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

	@Override
	public String toString() {
	    String result = "";
	    result += "Name: " + name + "\n";
	    result += "Product: " + product.getName() + "\n";
	    result += "Amount: " + amount + "\n";
	    result += "Coast: " + coast + "\n";
	    if (date != null) {
	    	result += "Date: " + new SimpleDateFormat("dd.MM.yyyy").format(date) + "\n";
	    }
	    result += "Status: " + status + "\n";
	    return result;
	}
}