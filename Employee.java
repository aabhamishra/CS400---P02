
public class Employee implements Comparable<Employee>{
	public String name; 
	public int time;
	
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
 
