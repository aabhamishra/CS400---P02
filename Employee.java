
public class Employee {
	public static String name; 
	public static int time;
	
	public Employee(String name, int time) {
		this.name = name;
		this.time = time;
	}
	
	public static int getTime() {
		return time;
	}
	
	public static String getName() {
		return name;
	}
	
	public int compareTo(Employee e2) {
		if (this.time == e2.time) {
			return 0;
		} else if (this.time > e2.time) {
			return 1;
		} else { 
			return -1;
		}
	}
}
 