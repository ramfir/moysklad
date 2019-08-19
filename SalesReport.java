import java.util.Scanner;
import java.text.ParseException;
import java.util.Set;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Iterator;

public class SalesReport {
	private static Set<Product> productsSet = new HashSet<Product>();
	private static Queue<Command> commandsQueue = new LinkedList<Command>();
	public static void main(String[] args) throws ParseException   {
		Scanner scanner = new Scanner(System.in);

		while (scanner.hasNext()) {
			String inputCommand = scanner.nextLine();
			if (inputCommand.equals("end"))
				break;
			parseCommand(inputCommand);
		}
		printQueue();
		System.out.println("--");
		printSet();
		//String com = scanner.nextLine();
		
	}
	public static void parseCommand(String inputCommand) throws ParseException {
		String[] s = inputCommand.split(" ");
		if (s.length == 2) {
			Command command = new Command();
			command.setName(s[0]);
			Product product = new Product(s[1]);
			command.setProduct(product);
			if (productsSet.add(product)) {
				command.setStatus(true);
				//System.out.println("ura");
			} else {
				//System.out.println("no");
				command.setStatus(false);
			}
			commandsQueue.add(command);
			//System.out.println(productsSet.size());

		} else if (s.length == 5) {
			Command command = new Command();
			command.setName(s[0]);
			Product current_product = new Product(s[1]);
			Iterator<Product> it = productsSet.iterator();
			while (it.hasNext()) {
				Product product = it.next();
				if (product.equals(current_product)) {
					command.setProduct(product);
					break;
				}
				//System.out.println(product.getName());
			}
			//comm.setProduct(product);
			command.setAmount(Integer.parseInt(s[2]));
			if (s[0].equals("PURCHASE") && command.getProduct() != null) {
				command.getProduct().setAmount(command.getProduct().getAmount()+command.getAmount());
				command.setStatus(true);
			} else if (s[0].equals("DEMAND") && command.getProduct() != null && command.getProduct().getAmount() >= command.getAmount()) {
				command.getProduct().setAmount(command.getProduct().getAmount()-command.getAmount());
				command.setStatus(true);
			}
			command.setCoast(Integer.parseInt(s[3]));
			command.setDate(s[4]);
			commandsQueue.add(command);
			//command.printObj();
		} else if (s.length == 3) {
			Command command = new Command();
			command.setName(s[0]);
			Product current_product = new Product(s[1]);
			Iterator<Product> it = productsSet.iterator();
			while (it.hasNext()) {
				Product product = it.next();
				if (product.equals(current_product)) {
					command.setProduct(product);
					break;
				}
				//System.out.println(product.getName());
			}
			command.setDate(s[2]);
			int proAmount = 0;
			int wholePribil = 0;
			int sebesto = 0;
			for (Command c : commandsQueue) {
				if (c.getName().equals("DEMAND")) {
					proAmount = c.getAmount();
					wholePribil = proAmount*c.getCoast();
				}
			}
			for (Command c : commandsQueue) {
				if (proAmount == 0) {
					break;
				}
				if (c.getName().equals("PURCHASE") && c.getProduct().equals(command.getProduct())) {
					if (proAmount > c.getAmount()) {
						proAmount -= c.getAmount();
						sebesto += c.getAmount()*c.getCoast();
					} else {
						sebesto += proAmount*c.getCoast();
						proAmount = 0;
					}
				}
			}
			System.out.println("Pribil: " + (wholePribil - sebesto));
		}
	}
	public static void printQueue() {
		/*while (!commandsQueue.isEmpty()) {
			Command command = commandsQueue.remove();
			command.printObj();
			System.out.println("----------");
		}*/
		for (Command command : commandsQueue) {
			command.printObj();
			System.out.println("----------");
		}
	}
	public static void printSet() {
		Iterator<Product> it = productsSet.iterator();
		while (it.hasNext()) {
			Product product = it.next();
			System.out.println("Pro Name: " + product.getName());
			System.out.println("Pro amount: " + product.getAmount());
		}
	}
}