package DAO;

import java.sql.Date;

public class Actor
{
	protected String ID;
	protected String firstName;
	protected String lastName;
	protected Date dateOfBirth;
	
	public Actor(){};
	public Actor(String ID, String firstName, String lastName, Date dateOfBirth)
	{
		super();
		this.ID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getID()
	{
		return ID;
	}
	public void setID(String ID)
	{
		this.ID = ID;
	}
	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	public Date getDateOfBirth()
	{
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}
	
}
