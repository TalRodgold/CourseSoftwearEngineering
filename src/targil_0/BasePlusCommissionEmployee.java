package targil_0;

import java.util.Objects;

public class BasePlusCommissionEmployee extends CommissionEmployee
{
    private float baseSalary;

    public BasePlusCommissionEmployee(String firstName, String lastName, int id, float grossSales, int commission, float baseSalary) {
        super(firstName, lastName, id, grossSales, commission);
        try
        {
            if (baseSalary < 0) // if invalid input
            {
                throw new IllegalArgumentException("baseSalary can not be negative"); // throw
            }
            this.baseSalary = baseSalary;
        }
        catch (IllegalArgumentException e) // catch
        {
            System.out.println(e.getMessage()); // print
        }
    }

    public BasePlusCommissionEmployee() {
        super();
        this.baseSalary = 0;
    }

    public float getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(float baseSalary)
    {
        try
        {
            if (baseSalary < 0) // if invalid input
            {
                throw new IllegalArgumentException("baseSalary can not be negative"); // throw
            }
            this.baseSalary = baseSalary;
        }
        catch (IllegalArgumentException e) // catch
        {
            System.out.println(e.getMessage()); // print
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BasePlusCommissionEmployee that = (BasePlusCommissionEmployee) o;
        return Float.compare(that.baseSalary, baseSalary) == 0;
    }
    @Override
    public float earnings() // override earnings
    {
        return baseSalary + super.earnings(); // return salary
    }
    @Override
    public String toString() {
        return "BasePlusCommissionEmployee{" +
                "baseSalary=" + baseSalary +
                '}';
    }
}
