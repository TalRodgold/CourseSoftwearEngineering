package targil_0;

public class Payroll {
    public static void main(String[] args)
    {
        Employee[] employeesArr = new Employee[3];
        employeesArr[0] = new HourlyEmployee("Moti", "Cohen", 12345, 120, 32);
        employeesArr[1] = new CommissionEmployee("Haim", "Levi", 56789, 110, 32);
        employeesArr[2] = new BasePlusCommissionEmployee("Yossi", "Buzaglo", 45678, 20, 32, 44);
    for (int i = 0; i < employeesArr.length; i++)
    {
        System.out.println(employeesArr[i].toString());
        if (i != 2)
        {
            System.out.println("salary: ");
            System.out.printf("%.2f", employeesArr[i].earnings() * 7);
            System.out.println("\n");
        }
        else
        {
            System.out.println("salary after bonus: ");
            System.out.printf("%.2f", (employeesArr[i].earnings() * 7)*1.1);
            System.out.println("\n");

        }
    }
    }
}
