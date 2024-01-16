package Employee_Payroll_System;

import java.util.*;

public class EPS 
{
    public static void main(String[] args) 
    {
        Payroll_System payrollSystem = new Payroll_System();
        Scanner scanner = new Scanner(System.in);

        boolean addMoreEmployees = true;

        while (addMoreEmployees) 
        {
            payrollSystem.addEmployee(Employee.createEmployee(scanner));

            System.out.print("Do you want to add more employees? (yes/no): ");
            String addMoreChoice = scanner.nextLine().toLowerCase();
            addMoreEmployees = addMoreChoice.equals("yes");
        }

        List<Employee> allEmployees = payrollSystem.getAllEmployees();
        for (Employee employee : allEmployees) 
        {
            System.out.println("-------------------------------------------------------");
            System.out.println("Employee: " + employee.getName());
            System.out.println("Gross Salary: " + employee.calculateGrossSalary());
            System.out.println("Net Salary: " + employee.calculateNetSalary());
            System.out.println("-------------------------------------------------------");
        }

        scanner.close();
    }
}
