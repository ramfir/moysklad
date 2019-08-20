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
			if (!s[0].toUpperCase().equals("NEWPRODUCT")) {
				System.out.println("Wrong input data");
				return;
			}
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
			if (!(s[0].toUpperCase().equals("PURCHASE") || s[0].toUpperCase().equals("DEMAND"))) {
				System.out.println("Wrong input data");
				return;
			}
			PurchaseDemand command = new PurchaseDemand();
			command.setName(s[0]);
			command.setProduct(isInSet(s[1]));
			command.setAmount(s[2]);
			if (command.getName().equals("PURCHASE") && command.getProduct() != null) {
				command.getProduct().setAmount(command.getProduct().getAmount()+command.getAmount());
			} else if (command.getName().equals("DEMAND") && command.getProduct() != null) {
				command.getProduct().setAmount(command.getProduct().getAmount()-command.getAmount());
			}
			command.setCost(s[3]);
			command.setDate(s[4]);
			if (command.getProduct() == null || command.getAmount() <= 0 || command.getCost() == 0)
				command.setStatus("ERROR");
			else
				command.setStatus("OK");
			commandsQueue.getQueue().add(command);
		} else if (s.length == 3) {
			if (!s[0].toUpperCase().equals("SALESREPORT")) {
				System.out.println("Wrong input data");
				return;
			}
			SalesReport command = new SalesReport();
			command.setName(s[0]);
			command.setProduct(isInSet(s[1]));
			command.setDate(s[2]);
			if (command.getProduct() != null) {
				int soldAmount = 0;
				long totalMoney = 0;
				long costPrice = 0;
				for (Command c : commandsQueue.getQueue()) {
					if (c.getStatus().equals("OK") && 
						c.getName().equals("DEMAND") && 
						c.getProduct().equals(command.getProduct()) && 
						command.getDate().compareTo(((PurchaseDemand)c).getDate()) >= 0) {

						soldAmount += ((PurchaseDemand)c).getAmount();
						totalMoney += ((PurchaseDemand)c).getAmount()*((PurchaseDemand)c).getCost();
					}
				}
				for (Command c : commandsQueue.getQueue()) {
					if (soldAmount == 0) {
						break;
					}
					if (c.getStatus().equals("OK") && 
						c.getName().equals("PURCHASE") && 
						c.getProduct().equals(command.getProduct()) &&
						command.getDate().compareTo(((PurchaseDemand)c).getDate()) >= 0) {

						if (soldAmount > ((PurchaseDemand)c).getAmount()) {
							soldAmount -= ((PurchaseDemand)c).getAmount();
							costPrice += ((PurchaseDemand)c).getAmount()*((PurchaseDemand)c).getCost();
						} else {
							costPrice += soldAmount*((PurchaseDemand)c).getCost();
							soldAmount = 0;
						}
					}
				}
				command.setStatus(Long.toString(totalMoney - costPrice));
			} else {
				command.setStatus("ERROR");
			}
			commandsQueue.getQueue().add(command);
		} else {
			System.out.println("Wrong input data");
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