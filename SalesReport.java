import java.util.Scanner;
import java.text.ParseException;

public class SalesReport {
	public static void main(String[] args) throws ParseException  {
		Scanner scanner = new Scanner(System.in);
		System.out.println("" + scanner.hasNext());

		while (scanner.hasNext()) {
			String s1 = scanner.nextLine();
			if (s1.equals("end"))
				break;
			System.out.println(s1);
		}
		System.out.println("ura");
		String com = scanner.nextLine();
		String[] s = com.split(" ");
		Product product = new Product();
		product.setName(s[1]);
		s = scanner.nextLine().split(" ");
		Command comm = new Command();
		comm.setName(s[0]);
		comm.setProduct(product);
		comm.setAmount(Integer.parseInt(s[2]));
		comm.setCoast(Integer.parseInt(s[3]));
		comm.setDate(s[4]);
		comm.printObj();

	}
}