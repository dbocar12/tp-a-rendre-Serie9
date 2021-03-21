package org.hakimbocar;

import java.io.Serializable;

public class Employee extends Person implements Serializable{
	
	private double salary;

	public Employee() {
	}

	public Employee(double salary) {
		super();
		this.salary = salary;
	}
	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [salary=" + salary + "]";
	}
	
	
	

}
