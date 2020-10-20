import java.util.Scanner;

public class EmplyeeLogDriver {
	
	static BackEndDevelop log = new BackEndDevelop();

	public static void main(String[] args) {
		
		System.out.println("Welcome to the Employee Log. Please enter a single letter command below:");
		boolean stop = false;

		while (!stop) {
			commands();
			Scanner scnr = new Scanner(System.in);
			String input = scnr.next();
			if (input.equals("A") || input.equals("a")) {
				System.out.println("Enter only your first name please");
				String name = scnr.next();
				System.out.println("Enter the time you arrived at using army time please");
				String time = scnr.next();
				int intTime = Employee.inputToInt(time);
				if (intTime == -1)
					continue;
				log.arrive(name, intTime);
			} else if (input.equals("S") || input.equals("s")) {
				System.out.println("Please enter a single letter command below:");
				boolean quit = false;
				while (!quit) {
					commands2();
					String secondaryInput = scnr.next();
					if (secondaryInput.equals("L") || secondaryInput.equals("l")) {
						String firstEmployee = log.getMin();
						if (firstEmployee != null)
							System.out.println("This is the employee that has clocked in first: " + firstEmployee);
					} else if (secondaryInput.equals("S") || secondaryInput.equals("s")) {
						String lastEmployee = log.getMax();
						if (lastEmployee != null)
							System.out.println("This is the employee that has stayed here the shortest: " + log.getMax());
					} else if (secondaryInput.equals("T") || secondaryInput.equals("t")) {
						System.out.println("Please input the time you would like to search up an employee with:");
						String secondaryTime = scnr.next();
						int timeInt = Employee.inputToInt(secondaryTime);
						try {
							System.out.println(
									"Here is the employee that you have searched up: " + log.searchTime(timeInt));
						} catch (IllegalArgumentException e) {
							System.out.println("No employee found with given time");
						}
					} else if (secondaryInput.equals("Q") || secondaryInput.equals("q")) {
						quit = true;
					} else {
						System.out.println("Please enter a valid command");
					}
				}
			}

			else if (input.equals("Q") || input.equals("q")) {
				stop = true;
				scnr.close();
				System.out.println("Goodbye!");
			} else {
				System.out.println("Not a valid command. Please input a valid command shown below.");
			}
		}
	}

	private static void commands() {
		System.out.println("A - Arrive");
		System.out.println("S - Search");
		System.out.println("Q - Quit");
	}

	private static void commands2() {
		System.out.println("L - Check who's been clocked first");
		System.out.println("S - Check who's been clocked in the shortest");
		System.out.println("T - Search up a specific employee with the time that they clocked in");
		System.out.println("Q - Quit this selection");
	}
}

