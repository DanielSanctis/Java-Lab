class Employee {
    int employeeId;
    String employeeName;
    String designation;

    // Constructor for initializing employee attributes
    Employee(int employeeId, String employeeName,String designation)
    {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.designation = designation;
    }
    // Method to calculate bonus (default for base class)
    double calculateBonus() 
    {
        return 0.0; 
    }
}
class HourlyEmployee extends Employee
{
    double hourlyRate;
    int hoursWorked;
    double earnings;

    // Constructor to initialize hourly employee attributes
    HourlyEmployee(int employeeId, String employeeName,String designation,double hourlyRate, int hoursWorked)
    {
        super(employeeId, employeeName, designation); // Invoke base class constructor
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }
    double weekearning() // Method to calculate weekly earnings
    {
        earnings = hourlyRate * hoursWorked;
        return earnings;
    }
    double calculateBonus()  // Override calculateBonus method to give 10% bonus of weekly earnings
    {
        return earnings * 0.1; // 10% of weekly earnings as bonus
    }
    double annualEarnings() // Method to calculate annual earnings including the weekly bonus
    {
        return weekearning() * 52 + calculateBonus();
    }
    void display()
    {
        System.out.println("Employee Id: " + employeeId);
        System.out.println("Employee Name: " + employeeName);
        System.out.println("Employee Designation: " + designation);
        System.out.println("Hourly Rate: " + hourlyRate); 
        System.out.println("Hours Workerd: " + hoursWorked);
        System.out.println("Weekly Earnings: " + earnings);
        System.out.println("Bonus: " + calculateBonus());
        System.out.println("Annual Earnings: " + annualEarnings());
        System.out.println();
    }
}

// Subclass for salaried employees who are paid a fixed monthly salary
class SalariedEmployee extends Employee
{
    double monthlySalary,earnings;

    // Constructor to initialize salaried employee attributes
    SalariedEmployee(int employeeId, String employeeName,String designation,double monthlySalary)
    {
        super(employeeId, employeeName, designation); // Invoke base class constructor using super keyword
        this.monthlySalary = monthlySalary;
    }

    double weekearning()
    {
        earnings = monthlySalary / 4; // Method to calculate weekly earnings from monthly salary
        return earnings;
    }
    double calculateBonus()  // Override calculateBonus method to give 5% bonus of monthly salary
    {
        return monthlySalary * 0.05; // 5% of monthly salary as bonus
    }
    double annualEarnings()  // Method to calculate annual earnings including the monthly bonus
    {
        return monthlySalary * 12 + calculateBonus();
    }
    void display()
    {
        System.out.println("Employee Id: " + employeeId);
        System.out.println("Employee Name: " + employeeName);
        System.out.println("Employee Designation: " + designation);
        System.out.println("Monthly Salary: " + monthlySalary); 
        System.out.println("Weekly Earnings: " + earnings);
        System.out.println("Bonus: " + calculateBonus());
        System.out.println("Annual Earnings: " + annualEarnings());
        System.out.println();
    }
}

// Subclass for executive employees who receive an additional annual bonus percentage
class ExecutiveEmployee extends SalariedEmployee {
    double bonusPercentage;

    // Constructor to initialize executive employee attributes
    ExecutiveEmployee(int employeeId, String employeeName, String designation, double monthlySalary, double bonusPercentage) 
    {
        super(employeeId, employeeName, designation, monthlySalary); // Invoke base class constructor
        this.bonusPercentage = bonusPercentage;
    }

    // Override calculateBonus method to include additional bonus percentage on top of the base bonus
    double calculateBonus() 
    {
        double baseBonus = super.calculateBonus(); // Invoke base class method
        return baseBonus + (monthlySalary * 12 * (bonusPercentage / 100)); // Add annual bonus
    }
    double annualEarnings() 
    {
        return super.annualEarnings(); // Annual earnings with bonus included
    }

    void display() {
        System.out.println("Employee Id: " + employeeId);
        System.out.println("Employee Name: " + employeeName);
        System.out.println("Employee Designation: " + designation);
        System.out.println("Monthly Salary: " + monthlySalary);
        System.out.println("Bonus Percentage: " + bonusPercentage);
        System.out.println("Bonus: " + calculateBonus());
        System.out.println("Annual Earnings: " + annualEarnings());
        System.out.println();
    }
}

//Main Class
public class MainEmp
{
    static double totalPayroll = 0.0;  // Variable to keep track of total payroll for all employees
    public static void main(String[] args)
    {
        // Create an HourlyEmployee instance and display its details
        HourlyEmployee obj1 = new HourlyEmployee(1,"Daniel Sanctis","CEO",50,5);
        obj1.weekearning();
        obj1.display();
        totalPayroll += obj1.annualEarnings();

        // Create a SalariedEmployee instance and display its details        
        SalariedEmployee obj2 = new SalariedEmployee(2,"Mervin Sanctis","CTO",50000);
        obj2.weekearning();
        obj2.display();
        totalPayroll += obj2.annualEarnings();

        // Create an ExecutiveEmployee instance and display its details
        ExecutiveEmployee obj3 = new ExecutiveEmployee(3, "Gilbert Sanctis", "CEO", 100000, 20);
        obj3.display();
        totalPayroll += obj3.annualEarnings();

        // Display total payroll for all employees
        System.out.println("Total Payroll for All Employees: " + totalPayroll);
    }
}