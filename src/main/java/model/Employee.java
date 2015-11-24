package model;

import java.sql.Date;

/**
 * Created by Dima on 22.11.2015.
 */
public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date hireDate;
    private double salary;
    private String departmentName;
    private String jobName;
    private String city;

    public Employee(int id) {
        this.id = id;
    }

    public Employee(){}

    public Employee(String firstName, String lastName, String email, String phoneNumber,
                    Date hireDate, double salary, String departmentName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.salary = salary;
        this.departmentName = departmentName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "ID = " + id + "\nFirst name = " + firstName + "\nLast name = " + lastName +
                "\nEmail = " + email + "\nPhone number = " + phoneNumber +
                "\nHire date = " + hireDate + "\nSalary = " + salary +
                "\nDepartment name = " + departmentName + "\nJob title = " + jobName +
                "\nLocation = " + city + '\n';

    }
}
