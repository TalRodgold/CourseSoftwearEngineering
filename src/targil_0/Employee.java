package targil_0;

import java.util.Objects;

public abstract class Employee // abstract class for employee
{
   private String  firstName; // first name
   private String lastName; // last name
   private int id; // id number

    public Employee(String firstName, String lastName, int id) // constructor
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }
    public Employee() // default constructor
    {
        this.firstName = "plony";
        this.lastName = "almony";
        this.id = 0;
    }

    public String getFirstName() {return firstName;} // get first name
    public void setFirstName(String firstName) {this.firstName = firstName;} //set first name

    public String getLastName() {return lastName;} //get last name
    public void setLastName(String lastName) {this.lastName = lastName;} //set last name

    public int getId() {return id;} //get Id
    public void setId(int id) //set Id
    {
        try
        {
            if (id < 0) // if invalid input
            {
                throw new IllegalArgumentException("id can not be negative"); // throw
            }
            this.id = id;
        }
        catch (IllegalArgumentException e) // catch
        {
            System.out.println(e.getMessage()); // print
        }
    }

    public abstract float earnings(); // abstract func earnings

    @Override
    public boolean equals(Object o) //override equals
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public String toString() // override to string
    {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id=" + id +
                '}';
    }
}

