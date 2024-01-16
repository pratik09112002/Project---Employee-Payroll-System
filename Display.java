package Employee_Payroll_System;

class Display 
{
    public static void displayDeductionDetails(double taxDeduction, double holidaysDeduction, double overtimeAmount,
                                               double providentFundDeduction, double extraHolidayDeduction) 
    {
        System.out.println("Tax Deduction: " + taxDeduction);
        System.out.println("Holidays Deduction: " + holidaysDeduction);
        System.out.println("Overtime Amount (Not more than 10 Hrs): " + overtimeAmount);
        System.out.println("Provident Fund Deduction: " + providentFundDeduction);
        System.out.println("Extra Holiday Deduction: " + extraHolidayDeduction);
    }

    public static void displayReducedHoursDeduction(double reducedHoursDeduction) 
    {
        System.out.println("Reduced Hours Deduction: " + reducedHoursDeduction);
    }
}