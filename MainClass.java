import java.util.Scanner;
import java.text.ParseException;
import java.util.Set;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Iterator;

public class MainClass {
	private static ProductsSet productsSet = ProductsSet.getInstance();
	private static CommandsQueue commandsQueue = CommandsQueue.getInstance();

	public static void main(String[] args) throws ParseException   {
		Scanner scanner = new Scanner(System.in);

		while (scanner.hasNext()) {
			String inputCommand = scanner.nextLine();
			if (inputCommand.equals("end"))
				break;
			parseCommand(inputCommand);
		}
		printResults();
		/*printQueue();
		System.out.println("--");
		printSet();*/
	}

	public static void parseCommand(String inputCommand) throws ParseException {
		String[] s = inputCommand.split(" ");
		if (s.length == 2) {
			Command command = new NewProduct();
			command.setName(s[0]);
			Product product = new Product(s[1]);
			command.setProduct(product);
			if (productsSet.getSet().add(product)) {
				command.setStatus("OK");
			} else {
				command.setStatus("ERROR");
			}
			commandsQueue.getQueue().add(command);
		} else if (s.length == 5) {
			PurchaseDemand command = new PurchaseDemand();
			command.setName(s[0]);
			command.setProduct(isInSet(s[1]));
			command.setAmount(Integer.parseInt(s[2]));
			if (command.getName().equals("PURCHASE")) {
				if (command.getProduct() != null) {
					command.getProduct().setAmount(command.getProduct().getAmount()+command.getAmount());
					command.setStatus("OK");
				} else {
					command.setStatus("ERROR");
				}
			} else if (command.getName().equals("DEMAND")) {
				if (command.getProduct() != null && command.getProduct().getAmount() >= command.getAmount()) {
					command.getProduct().setAmount(command.getProduct().getAmount()-command.getAmount());
					command.setStatus("OK");
				} else {
					command.setStatus("ERROR");
				}
			}
			command.setCoast(Integer.parseInt(s[3]));
			command.setDate(s[4]);
			commandsQueue.getQueue().add(command);
		} else if (s.length == 3) {
			SalesReport command = new SalesReport();
			command.setName(s[0]);
			command.setProduct(isInSet(s[1]));
			command.setDate(s[2]);
			if (command.getProduct() != null) {
				int proAmount = 0;
				int wholePribil = 0;
				int sebesto = 0;
				for (Command c : commandsQueue.getQueue()) {
					if (c.getStatus().equals("OK") && 
						c.getName().equals("DEMAND") && 
						c.getProduct().equals(command.getProduct()) && 
						command.getDate().compareTo(((PurchaseDemand)c).getDate()) >= 0) {

						proAmount += ((PurchaseDemand)c).getAmount();
						wholePribil += ((PurchaseDemand)c).getAmount()*((PurchaseDemand)c).getCoast();
					}
				}
				for (Command c : commandsQueue.getQueue()) {
					if (proAmount == 0) {
						break;
					}
					if (c.getStatus().equals("OK") && 
						c.getName().equals("PURCHASE") && 
						c.getProduct().equals(command.getProduct())) {

						if (proAmount > ((PurchaseDemand)c).getAmount()) {
							proAmount -= ((PurchaseDemand)c).getAmount();
							sebesto += ((PurchaseDemand)c).getAmount()*((PurchaseDemand)c).getCoast();
						} else {
							sebesto += proAmount*((PurchaseDemand)c).getCoast();
							proAmount = 0;
						}
					}
				}
				command.setStatus(Integer.toString(wholePribil - sebesto));
			} else {
				command.setStatus("ERROR");
			}
			commandsQueue.getQueue().add(command);
		}
	}
	public static Product isInSet(String s) {
		Product current_product = new Product(s);
		Iterator<Product> it = productsSet.getSet().iterator();
		while (it.hasNext()) {
			Product product = it.next();
			if (product.equals(current_product)) {
				return product;
			}
		}
		return null;
	}
	public static void printResults() {
		for (Command command : commandsQueue.getQueue())
			System.out.println(command.getStatus());
	}
	public static void printQueue() {
		for (Command command : commandsQueue.getQueue()) {
			System.out.println(command);
			System.out.println("----------");
		}
	}
	public static void printSet() {
		Iterator<Product> it = productsSet.getSet().iterator();
		while (it.hasNext()) {
			Product product = it.next();
			System.out.println("Pro Name: " + product.getName());
			System.out.println("Pro amount: " + product.getAmount());
		}
	}
}