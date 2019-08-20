import java.util.Queue;
import java.util.LinkedList;

public class CommandsQueue {
	private static CommandsQueue singleton;
	private Queue<Command> commandsQueue;

	private CommandsQueue() {
		commandsQueue = new LinkedList<Command>();
	}
	public static synchronized CommandsQueue getInstance() {
		if (singleton == null) {
			singleton = new CommandsQueue();
		}
		return singleton;
	}
	public Queue<Command> getQueue() {
		return commandsQueue;
	}
}