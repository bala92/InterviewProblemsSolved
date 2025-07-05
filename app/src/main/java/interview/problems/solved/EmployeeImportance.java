package interview.problems.solved;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
Problem: Employee Importance

You are given a list of employees, where each employee has:
    - A unique integer ID.
    - An importance value (also an integer).
    - A list of IDs representing their direct subordinates.

Given the ID of a particular employee, you need to calculate the total importance value for that employee and all of their subordinates (direct and indirect).

Example:
Suppose you have the following employees:
    Employee 1: importance = 5, subordinates = [2, 3]
    Employee 2: importance = 3, subordinates = []
    Employee 3: importance = 3, subordinates = []

If you are given the ID 1, the total importance is 5 + 3 + 3 = 11.

Write a function:
    int getImportance(List<Employee> employees, int id)
that returns the total importance value for the given employee and all their subordinates.

Approach:
- Use a map to quickly look up employees by their ID.
- Use BFS (queue) to traverse the employee hierarchy starting from the given ID.
- Accumulate the importance values as you visit each employee.

*/

// Class representing an Employee
class Employee {
    public int id; // Unique ID of the employee
    public int importance; // Importance value of the employee
    public List<Integer> subordinates; // List of IDs of direct subordinates
}

public class EmployeeImportance {
    /**
     * Calculates the total importance value for the given employee and all their subordinates.
     *
     * @param employees List of all employees
     * @param id The ID of the employee whose total importance is to be calculated
     * @return Total importance value
     */
    public int getImportance(List<Employee> employees, int id) {
        // Create a map for quick lookup of employees by ID
        Map<Integer, Employee> employeeMap = new HashMap<>();
        for (Employee e : employees) {
            employeeMap.put(e.id, e);
        }

        // Use a queue for BFS traversal
        Queue<Employee> queue = new LinkedList<>();
        queue.add(employeeMap.get(id));

        int totalImportance = 0;

        // Perform BFS to calculate total importance
        while (!queue.isEmpty()) {
            Employee current = queue.poll();
            totalImportance += current.importance;
            // Add all direct subordinates to the queue
            for (int subId : current.subordinates) {
                queue.add(employeeMap.get(subId));
            }
        }

        return totalImportance;
    }

    public static void main(String[] args) {
        // Example usage

        // Create employee 1: id=1, importance=5, subordinates=[2,3]
        Employee e1 = new Employee();
        e1.id = 1;
        e1.importance = 5;
        e1.subordinates = Arrays.asList(2, 3);

        // Create employee 2: id=2, importance=3, subordinates=[]
        Employee e2 = new Employee();
        e2.id = 2;
        e2.importance = 3;
        e2.subordinates = Collections.emptyList();

        // Create employee 3: id=3, importance=3, subordinates=[]
        Employee e3 = new Employee();
        e3.id = 3;
        e3.importance = 3;
        e3.subordinates = Collections.emptyList();

        // Add all employees to the list
        List<Employee> employees = Arrays.asList(e1, e2, e3);

        // Create solution instance and calculate total importance for employee with id=1
        EmployeeImportance solution = new EmployeeImportance();
        System.out.println("Total Importance: " + solution.getImportance(employees, 1)); // Output: 11
    }
}
