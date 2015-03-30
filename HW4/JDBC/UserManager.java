package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserManager
{
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet results = null;
	DataSource ds;

	String createUserSql = "INSERT INTO USER (USERID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, DATEOFBIRTH) VALUES (?, ?, ?, ?, ?, ?, ?);";
        String updateUserSql = "UPDATE USER SET PASSWORD=? WHERE USERNAME=?;";
	String deleteUserSql = "DELETE FROM USER WHERE USERNAME=?";

	public void createUser(User user)
	{
		try
		{
			connection = ds.getConnection();
			statement = connection.prepareStatement(createUserSql);
			statement.setString(1, user.getUserid());
                        statement.setString(2, user.getUsername());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getFirstName());
			statement.setString(5, user.getLastName());
			statement.setString(6, user.getEmail());
			statement.setDate(7, user.getDateOfBirth());
			statement.executeUpdate();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			try
			{
				connection.close();
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public List<User> readAllUsers()
	{
		List<User> users = new ArrayList<User>();
		try
		{
			connection = ds.getConnection();
			statement = connection.prepareStatement(readAllUsersSql);
			results = statement.executeQuery();
			while (results.next())
			{
				User user = new User(results.getString("userid"),
                                                results.getString("username"),
						results.getString("password"),
						results.getString("firstName"),
						results.getString("lastName"),
						results.getString("email"),
						results.getDate("dateOfBirth"));
				users.add(user);
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			try
			{
				connection.close();
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return users;
	}

	public User readUser(String username)
	{
		String readUser = "SELECT * FROM USER WHERE USERNAME=?;";
                try
		{
			connection = ds.getConnection();
			statement = connection.prepareStatement(readUser);
			statement.setString(1, username);
			results = statement.executeQuery();
			if (results.next())
			{
				User user = new User(results.getString("userid"),
                                                results.getString("username"),
						results.getString("password"),
						results.getString("firstName"),
						results.getString("lastName"),
						results.getString("email"),
						results.getDate("dateOfBirth"));
				return user;
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			try
			{
				connection.close();
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public void updateUser(String username, User user)
	{
		try
		{
			connection = ds.getConnection();
			statement = connection.prepareStatement(updateUserSql);
			statement.setString(1, user.getUserid());
                        statement.setString(2, user.getUsername());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getFirstName());
			statement.setString(5, user.getLastName());
			statement.setString(6, user.getEmail());
			statement.setDate(7, user.getDateOfBirth());
			statement.executeUpdate();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			try
			{
				connection.close();
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void deleteUser(String username)
	{
		try
		{
			connection = ds.getConnection();
			statement = connection.prepareStatement(deleteUserSql);
			statement.setString(1, username);
			statement.executeUpdate();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			try
			{
				connection.close();
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public UserManager()
	{
		try
		{
			Context jndi = new InitialContext();
			ds = (DataSource) jndi.lookup("java:comp/env/jdbc/YLDB");
		} catch (NamingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
