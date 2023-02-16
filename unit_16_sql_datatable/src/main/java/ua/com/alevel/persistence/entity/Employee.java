package ua.com.alevel.persistence.entity;

public class Employee extends BaseEntity {

    private String firstName;
    private String lastName;
    private int age;

    public Employee() {
        super();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + getId() + '\'' +
                "created='" + getCreated() + '\'' +
                "updated='" + getUpdated() + '\'' +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                "} ";
    }
}
