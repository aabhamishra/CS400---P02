// --== CS400 File Header Information ==--
// Name: Francis Choi
// Email: fchoi@wisc.edu
// Team: HF
// Role: FrontEndDeveloper
// TA: Na Li
// Lecturer: Florian
// Notes to Grader: <>
import java.util.Scanner;

public class EmployeeLogDriver {
	static BackEndDevelop log = new BackEndDevelop();

	public static void main(String[] args) {
		System.out.println("Welcome to the Employee Log. Please enter a single letter command below:");
		boolean stop = false;
		
		//While loop that keeps the program looping until the user wants to quit
		while (!stop) {
			commands();
			Scanner scnr = new Scanner(System.in);
			//Stores the user's single letter command into a string variable
			String input = scnr.next();
			//Checks if the user's input is 'A' or 'a'
			if (input.equals("A") || input.equals("a")) {
				System.out.println("Enter only your first name please");
				//Store the name in a string variable
				String name = scnr.next();
				System.out.println("Enter the time you arrived at using army time please");
				String time = scnr.next();
				//Store the time in a string variable, then parse it into an int variable
				int intTime = Employee.inputToInt(time);
				if (intTime == -1)
					continue;
				//Adds the employee to the tree
				log.arrive(name, intTime);
				//Checks if the user's input is 'S' or 's'
			} else if (input.equals("S") || input.equals("s")) {
				System.out.println("Please enter a single letter command below:");
				boolean quit = false;
				//While loop to show the commands continuously until the user wants to quit
				while (!quit) {
					commands2();
					String secondaryInput = scnr.next();
					//Checks if the user's input is 'L' or 'l'
					if (secondaryInput.equals("L") || secondaryInput.equals("l")) {
						//Stores the name of the employee in the left most part of the tree, then prints the name out
						String firstEmployee = log.getMin();
						if (firstEmployee != null)
							System.out.println("This is the employee that has clocked in first: " + firstEmployee);
						//Checks if the user's input is 'S' or 's'
					} else if (secondaryInput.equals("S") || secondaryInput.equals("s")) {
						//Stores the name of the employee in the right most part of the tree, then prints the name out
						String lastEmployee = log.getMax();
						if (lastEmployee != null)
							System.out.println("This is the employee that has stayed here the shortest: " + log.getMax());
						//Checks if the user's input is 'T' or 't'
					} else if (secondaryInput.equals("T") || secondaryInput.equals("t")) {
						System.out.println("Please input the time you would like to search up an employee with:");
						//Stores the time the user inputted into a string variable, then parse it into an int variable
						String secondaryTime = scnr.next();
						int timeInt = Employee.inputToInt(secondaryTime);
						//If an employee is found with the time the user inputted, it prints out the String. If no employee
						// was found with the given time, it prints out that error message.
						try {
							System.out.println(
									"Here is the employee that you have searched up: " + log.searchTime(timeInt));
						} catch (IllegalArgumentException e) {
							System.out.println("No employee found with given time");
						}
						//Checks if the user's input is 'Q' or 'q'
					} else if (secondaryInput.equals("Q") || secondaryInput.equals("q")) {
						//Quits the current selection
						quit = true;
					} else {
						//Error message if the user inputted an invalid command
						System.out.println("Please enter a valid command");
					}
				}
			}
			//Checks if the user's input is 'Q' or 'q'
			else if (input.equals("Q") || input.equals("q")) {
				//Stops the program and prints out a farewell message
				stop = true;
				scnr.close();
				System.out.println("Goodbye!");
			} else {
				//Error message if the user inputted an invalid command
				System.out.println("Not a valid command. Please input a valid command shown below.");
			}
		}
	}
	
	/**
	 * Private helper method that prints out the first set of commands
	 */
	private static void commands() {
		System.out.println("A - Arrive");
		System.out.println("S - Search");
		System.out.println("Q - Quit");
	}
	/**
	 * Private helper method that prints out the second set of commands
	 */
	private static void commands2() {
		System.out.println("L - Check who's been clocked first");
		System.out.println("S - Check who's been clocked in the shortest");
		System.out.println("T - Search up a specific employee with the time that they clocked in");
		System.out.println("Q - Quit this selection");
	}
}
