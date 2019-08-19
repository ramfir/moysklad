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
	public static void main(String[] args) throws ParseException  {
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
	public static void parseCommand(String inputCommand) {
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
			/*Command comm = new Command();
			comm.setName(s[0]);
			comm.setProduct(product);
			comm.setAmount(Integer.parseInt(s[2]));
			comm.setCoast(Integer.parseInt(s[3]));
			comm.setDate(s[4]);
			comm.printObj();*/
		}
	}
	public static void printQueue() {
		while (!commandsQueue.isEmpty()) {
			Command command = commandsQueue.remove();
			command.printObj();
		}
	}
	public static void printSet() {
		Iterator<Product> it = productsSet.iterator();
		while (it.hasNext()) {
			Product product = it.next();
			System.out.println(product.getName());
		}
	}
}