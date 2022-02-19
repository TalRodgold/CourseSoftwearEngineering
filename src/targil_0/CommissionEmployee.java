package targil_0;

import java.util.Objects;

public class CommissionEmployee extends Employee // Commission employee which inherits from employee
{
    private float grossSales; //gross sales
    private int commission; //commission

    public CommissionEmployee(String firstName, String lastName, int id, float grossSales, int commission)
    {
        super(firstName, lastName, id);
        try
        {
            if (grossSales < 0) // if invalid input
            {
                throw new IllegalArgumentException("grossSales can not be negative"); // throw
            }
            if (commission < 0) // if invalid input
            {
                throw new IllegalArgumentException("commission can not be negative"); // throw
            }
            this.grossSales = grossSales;
            this.commission = commission;
        }
        catch (IllegalArgumentException e) // catch
        {
            System.out.println(e.getMessage()); // print
        }

    }

    public CommissionEmployee()
    {
        this.grossSales = 0;
        this.commission = 0;
    }

    public float getGrossSales() {
        return grossSales;
    }

    public void setGrossSales(float grossSales)
    {
        try
        {
            if (grossSales < 0) // if invalid input
            {
                throw new IllegalArgumentException("grossSales can not be negative"); // throw
            }
            this.grossSales = grossSales;
        }
        catch (IllegalArgumentException e) // catch
        {
            System.out.println(e.getMessage()); // print
        }
    }

    public int getCommission() {
        return commission;
    }

    public void setCommission(int commission)
    {
        try
        {
            if (commission < 0) // if invalid input
            {
                throw new IllegalArgumentException("commission can not be negative"); // throw
            }
            this.commission = commission;
        }
        catch (IllegalArgumentException e) // catch
        {
            System.out.println(e.getMessage()); // print
        }
    }

    @Override
    public float earnings() {
        return (float)commission/100*grossSales; // return salary
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CommissionEmployee that = (CommissionEmployee) o;
        return Float.compare(that.grossSales, grossSales) == 0 && commission == that.commission;
    }

    @Override
    public String toString() {
        return "CommissionEmployee{" +
                "grossSales=" + grossSales +
                ", commission=" + commission +
                '}';
    }
}
