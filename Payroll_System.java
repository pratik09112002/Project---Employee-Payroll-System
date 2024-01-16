package Employee_Payroll_System;

import java.util.*;

public class Payroll_System 
{
	private List<Employee> employees;

    private static final List<String> JOB_POSITIONS;

    static 
    {
        JOB_POSITIONS = new ArrayList<>(Arrays.asList(
                "Software Developer/Engineer",
                "System Administrator",
                "Network Engineer",
                "Database Administrator (DBA)",
                "Quality Assurance (QA) Engineer/Tester",
                "IT Security Specialist",
                "Business Analyst",
                "Project Manager",
                "Data Scientist",
                "UI/UX Designer",
                "DevOps Engineer"
        ));
    }

    public Payroll_System() 
    {
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) 
    {
        employees.add(employee);
    }

    public List<Employee> getAllEmployees() 
    {
        return new ArrayList<>(employees);
    }
}

