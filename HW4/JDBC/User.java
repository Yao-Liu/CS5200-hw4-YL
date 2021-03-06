package DAO;

import java.sql.Date;

public class User
{
	protected Int userid;
        protected String username;
	protected String password;
	protected String firstName;
	protected String lastName;
	protected String email;
	protected Date dateOfBirth;

	
	public User(){}
	public User(Int userid, String username, String password, String firstName, String lastName, String email, Date dateOfBirth)
	{
		super();
		this.userid=userid;
                this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getUserid()
	{
		return userid;
	}
	public void setUserid(Int userid)
	{
		this.userid = userid;
	}
        public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
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
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
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
