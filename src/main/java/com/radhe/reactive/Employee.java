package com.radhe.reactive;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("employee")
public class Employee
{

	String id;
	String name;
	double salary;
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public double getSalary()
	{
		return salary;
	}
	public void setSalary(double salary)
	{
		this.salary = salary;
	}
}
