// --== CS400 File Header Information ==--
// Name: Brock Schwieters
// Email: bschwieters@wisc.edu
// Team: HF
// Role: BackEndDeveloper
// TA: Na Li
// Lecturer: Florian
// Notes to Grader: <optional extra notes>

public class BackEndDevelop {
  private RedBlackTree<Employee> workTree = new RedBlackTree<Employee>();

  /*
   * Adds an employee when they arrive
   * 
   * @param name - the name of the employee
   * 
   * @param time - the time the employee arrived
   */
  public void arrive(String name, int time) {
    Employee newEmployee = new Employee(name, time);// create a new employee node
    this.workTree.insert(newEmployee); // add node to the tree
  }

  /*
   * this will get the employee that got there the earliest
   * 
   * @return String with the employee name
   */
  public String getMin() {
    return getMinHelper(workTree.root).data.getName();
  }

  /*
   * Recursive way to get the min value
   * 
   * @param the current node of the tree
   * 
   * @return the node of the min employee
   */
  private RedBlackTree.Node<Employee> getMinHelper(RedBlackTree.Node<Employee> current) {
    if (current.leftChild == null) {// if there are no more left children
      return current; // then we found the min employee
    }
    return getMinHelper(current.leftChild);// recursive call with the left child
  }

  /*
   * this will get the employee that got there most recently
   * 
   * @return the string name of employee
   */
  public String getMax() {
    return getMaxHelper(workTree.root).data.getName();
  }

  /*
   * this is a recursive helper method to get the far right root
   * 
   * @param current - the current node of the tree
   * 
   * @return employee node for max value
   */
  private RedBlackTree.Node<Employee> getMaxHelper(RedBlackTree.Node<Employee> current) {
    if (current.rightChild == null) { // if there are no more right children
      return current; // we found the max employee
    }
    return getMaxHelper(current.rightChild); // recursive call with the right child
  }

  /*
   * This searches the RBT for a specific employee
   * 
   * @param the time that they are searching for
   * 
   * @return A string with the employee name
   */
  public String searchTime(int time) throws IllegalArgumentException {
    String returnValue;
    try {
      returnValue = searchTimeHelper(this.workTree.root, time).data.getName();// call
                                                                              // searchTimeHelper to
                                                                              // search
    } catch (IllegalArgumentException IAE) {
      throw IAE;
    }
    return returnValue;
  }

  /*
   * helper method that searches the tree recursively for employee at that time
   */
  private RedBlackTree.Node<Employee> searchTimeHelper(RedBlackTree.Node<Employee> current,
      int time) throws IllegalArgumentException {
    if (current == null) { // we reached the end of the tree without finding it
      throw new IllegalArgumentException("that employee doesn't exist");
    }
    if (current.data.getTime() == time) { // return current node if we found it
      return current;
    }
    if (current.data.getTime() > time) {
      return searchTimeHelper(current.leftChild, time); // if node too big go left
    }
    return searchTimeHelper(current.rightChild, time); // if the node is too low go right
  }

}
