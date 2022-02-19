package targil_0;

public class HourlyEmployee extends Employee // hourly employee which inherits from employee
{
    private int hours; // hours
    private float wage; // wage

    public HourlyEmployee(String firstName, String lastName, int id, int hours, float wage) //constructor
    {
        super(firstName, lastName, id);
        try
        {
            if (hours < 0) // if invalid input
            {
                throw new IllegalArgumentException("hours can not be negative"); // throw
            }
            if (wage < 0) // if invalid input
            {
                throw new IllegalArgumentException("wage can not be negative"); // throw
            }
            this.hours = hours;
            this.wage = wage;
        }
        catch (IllegalArgumentException e) // catch
        {
            System.out.println(e.getMessage()); // print
        }
    }

    public HourlyEmployee() // default constructor
    {
        super();
        this.hours = 0;
        this.wage = 0;
    }

    public int getHours() {return hours;} // get hours
    public void setHours(int hours) //set hours
    {
        try
        {
            if (hours < 0) // if invalid input
            {
                throw new IllegalArgumentException("hours can not be negative"); // throw
            }
            this.hours = hours;
        }
        catch (IllegalArgumentException e) // catch
        {
            System.out.println(e.getMessage()); // print
        }
    }

    public float getWage() {return wage;} // get wage
    public void setWage(float wage) // set wage
    {
        try
        {
            if (wage < 0) // if invalid input
            {
                throw new IllegalArgumentException("wage can not be negative"); // throw
            }
            this.wage = wage;
        }
        catch (IllegalArgumentException e) // catch
        {
            System.out.println(e.getMessage()); // print
        }
    }

    @Override
    public boolean equals(Object o) //override equals
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        HourlyEmployee that = (HourlyEmployee) o;
        return hours == that.hours && Float.compare(that.wage, wage) == 0;
    }
    @Override
    public float earnings() // override earnings
    {
        return (this.hours * this.wage); // return salary
    }

    @Override
    public String toString() // override to string
    {
        return "HourlyEmployee{" +
                "hours=" + hours +
                ", wage=" + wage +
                '}';
    }
}
