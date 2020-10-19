import java.util.Scanner;

public class ProjectTwoCode {
	
	public static int inputToInt(String time) {
		
		if (time.contains(":")) {
			String temp = time;
			time = time.substring(0, time.indexOf(":"));
			temp = temp.substring(temp.indexOf(":")+1);
			time += temp;
		}
		
		return Integer.parseInt(time);
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter a name: ");
		String name = in.nextLine();
		
		System.out.println("Enter a time: ");
		String time = in.nextLine();
		
		Employee e = new Employee(name, inputToInt(time));
		
		System.out.println(e.getName());
		System.out.println(e.getTime());
		
		RedBlackTree tree = new RedBlackTree(); 
		tree.insert((Comparable) e);
		
		
	}
	
}
