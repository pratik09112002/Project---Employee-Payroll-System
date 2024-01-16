package Employee_Payroll_System;

import java.util.*;

class Employee 
{
    private static final double TAX_RATE = 0.1; // 10% tax rate
    private static final double OVERTIME_HOUR_RATE = 500.0; // Overtime rate per hour
    private static final double PROVIDENT_FUND_RATE = 0.12; // 12% provident fund rate
    private static final double EXTRA_HOLIDAY_DEDUCTION_RATE = 0.035; // 3.5% deduction rate for extra holidays
    private static final int STANDARD_MONTHLY_HOURS = 176; // Standard monthly working hours
    private static int employeeIdCounter = 1;

    private int employeeId;
    private String name;
    private String position;
    private double baseSalary;
    private double travelingAllowance;
    private List<Double> deductions;
    private int additionalHolidays;
    private int monthlyWorkedHours;
    private double providentFund;

    private static final Map<String, Double> FIXED_SALARIES = new HashMap<>();

    static 
    {
        FIXED_SALARIES.put("Software Developer/Engineer", 70000.0);
        FIXED_SALARIES.put("System Administrator", 60000.0);
        FIXED_SALARIES.put("Network Engineer", 65000.0);
        FIXED_SALARIES.put("Database Administrator (DBA)", 75000.0);
        FIXED_SALARIES.put("Quality Assurance(QA) Engineer/Tester", 65000.0);
        FIXED_SALARIES.put("IT Security Specialist", 80000.0);
        FIXED_SALARIES.put("Business Analyst", 75000.0);
        FIXED_SALARIES.put("Project Manager", 90000.0);
        FIXED_SALARIES.put("Data Scientist", 85000.0);
        FIXED_SALARIES.put("UI/UX Designer", 70000.0);
        FIXED_SALARIES.put("DevOps Engineer", 75000.0);
    }

    public Employee(int employeeId, String name, String position) 
    {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.baseSalary = getFixedSalary(position);
        this.travelingAllowance = 0.0;
        this.deductions = new ArrayList<>();
        this.additionalHolidays = 0;
        this.monthlyWorkedHours = 0;
        this.providentFund = 0.0;
    }

    // Getters and setters for attributes

    public String getName() 
    {
        return name;
    }

    public double getTravelingAllowance() 
    {
        return travelingAllowance;
    }

    public void setTravelingAllowance(double travelingAllowance) 
    {
        this.travelingAllowance = travelingAllowance;
    }

    public List<Double> getDeductions() 
    {
        return deductions;
    }

    public int getAdditionalHolidays() 
    {
        return additionalHolidays;
    }

    public void setAdditionalHolidays(int additionalHolidays) 
    {
        this.additionalHolidays = additionalHolidays;
    }

    public int getMonthlyWorkedHours() 
    {
        return monthlyWorkedHours;
    }

    public void setMonthlyWorkedHours(int monthlyWorkedHours) 
    {
        this.monthlyWorkedHours = monthlyWorkedHours;
    }

    public double getProvidentFund() 
    {
        return providentFund;
    }

    public void setProvidentFund(double providentFund) 
    {
        this.providentFund = providentFund;
    }

    public double calculateGrossSalary() 
    {
        double totalAllowances = travelingAllowance;
        double overtimeAmount = calculateOvertimeAmount();
        return baseSalary + totalAllowances + overtimeAmount;
    }

    public double calculateNetSalary() 
    {
        double totalDeductions = deductions.stream().mapToDouble(Double::doubleValue).sum();
        double taxDeduction = calculateTaxDeduction();
        double overtimeAmount = calculateOvertimeAmount();
        double providentFundDeduction = calculateProvidentFundDeduction();
        double extraHolidayDeduction = calculateExtraHolidayDeduction();
        totalDeductions += taxDeduction + overtimeAmount + providentFundDeduction + extraHolidayDeduction;

        displayDeductionDetails(taxDeduction, overtimeAmount, providentFundDeduction, extraHolidayDeduction);

        if (monthlyWorkedHours < STANDARD_MONTHLY_HOURS) 
        {
            double reducedHoursDeduction = calculateReducedHoursDeduction();
            System.out.println("Reduced Hours Deduction: " + reducedHoursDeduction);
            totalDeductions += reducedHoursDeduction;
        }

        return calculateGrossSalary() - totalDeductions;
    }

    private double calculateTaxDeduction() 
    {
        return calculateGrossSalary() * TAX_RATE;
    }

    private double calculateOvertimeAmount() 
    {
        // Calculate overtime only if the monthly worked hours exceed the standard hours
        int overtimeHours = Math.max(monthlyWorkedHours - STANDARD_MONTHLY_HOURS, 0);

        if (overtimeHours > 0) 
        {
            // Limit overtime hours to 10
            overtimeHours = Math.min(overtimeHours, 10);

            double overtimeRate = OVERTIME_HOUR_RATE; // Overtime rate per hour
            return overtimeHours * overtimeRate;
        } 
        else 
        {
            return 0.0; // No overtime if monthly worked hours are within standard hours
        }
    }

    private double calculateProvidentFundDeduction() 
    {
        return calculateGrossSalary() * PROVIDENT_FUND_RATE;
    }

    private double calculateExtraHolidayDeduction() 
    {
        // Check for reduced deduction rate for extra holidays
        double extraHolidayDeductionRate = getExtraHolidayDeductionRate();
        return baseSalary * extraHolidayDeductionRate * additionalHolidays;
    }

    private double calculateReducedHoursDeduction() 
    {
        // Calculate deduction for reduced hours based on the overtime rate
        int hoursBelowStandard = STANDARD_MONTHLY_HOURS - monthlyWorkedHours;
        return hoursBelowStandard * (OVERTIME_HOUR_RATE / 4); // Adjusting for monthly hours
    }

    private double getFixedSalary(String position) 
    {
        return FIXED_SALARIES.getOrDefault(position, 0.0);
    }

    private double getExtraHolidayDeductionRate() 
    {
        // Check for reduced deduction rate for extra holidays
        if (additionalHolidays > 5) {
            return 0.035; // Reduced rate of 3.5% for more than 5 additional holidays
        } 
        else 
        {
            return EXTRA_HOLIDAY_DEDUCTION_RATE;
        }
    }

    private void displayDeductionDetails(double taxDeduction, double overtimeAmount,
            							double providentFundDeduction, double extraHolidayDeduction) 
    {
    	System.out.println("Tax Deduction: " + taxDeduction);
		System.out.println("Overtime Amount: " + overtimeAmount);
		System.out.println("Provident Fund Deduction: " + providentFundDeduction);
		System.out.println("Extra Holiday Deduction: " + extraHolidayDeduction);
	}

    public static Employee createEmployee(Scanner scanner) 
    {
        System.out.println("Enter details for the next Employee");
        System.out.print("Name: ");
        String name = scanner.nextLine();

        // Display job positions for selection
        List<String> JOB_POSITIONS = Arrays.asList(
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
        );

        System.out.println("Select a Job Position:");
        for (int j = 0; j < JOB_POSITIONS.size(); j++) 
        {
            System.out.println((j + 1) + ". " + JOB_POSITIONS.get(j));
        }

        // Validate user input for job position
        int positionChoice;
        do 
        {
            System.out.print("Enter the number corresponding to the job position: ");
            positionChoice = scanner.nextInt();
        } 
        while (positionChoice < 1 || positionChoice > JOB_POSITIONS.size());

        String position = JOB_POSITIONS.get(positionChoice - 1);

        // Clear the buffer
        scanner.nextLine();

        Employee employee = new Employee(employeeIdCounter++, name, position);

        System.out.print("Enter the number of additional holidays in a month: ");
        int additionalHolidays = scanner.nextInt();
        employee.setAdditionalHolidays(additionalHolidays);

        System.out.print("Enter the monthly(30Days) worked hours i.e 176 Hrs: ");
        int monthlyWorkedHours = scanner.nextInt();
        employee.setMonthlyWorkedHours(monthlyWorkedHours);

        System.out.print("Enter the traveling allowance for the employee: ");
        double travelingAllowance = scanner.nextDouble();
        employee.setTravelingAllowance(travelingAllowance);

        // Clear the buffer
        scanner.nextLine();

        return employee;
    }
}
