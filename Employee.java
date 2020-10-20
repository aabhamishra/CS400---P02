public class Employee implements Comparable<Employee>{
	public String name; 
	public int time;
	
	public static int inputToInt(String time) {

		if (time.contains(":")) {
			String temp = time;
			time = time.substring(0, time.indexOf(":"));
			temp = temp.substring(temp.indexOf(":") + 1);
			if (Integer.parseInt(time) > 24 || Integer.parseInt(time) < 0) {
				System.out.println("Not a valid time. Please restart and try again.");
				return -1;
			}
			if (Integer.parseInt(temp) > 59 || Integer.parseInt(temp) < 0) {
				System.out.println("Not a valid time. Please restart and try again.");
				return -1;
			}
			time += temp;
		}
		if (time.length() > 4 || time.length() < 3) {
			System.out.println("Not a valid time. Please restart and try again.");
			return -1;
		}
		try {
			return Integer.parseInt(time);
		} catch (NumberFormatException e) {
			System.out.println("Not a valid time. Please restart and try again.");
			return -1;
		}
	}
	public Employee(String name, int time) {
		this.name = name;
		this.time = time;
	}
	
	public int getTime() {
		return time;
	}
	
	public String getName() {
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
