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

public class ActorManager
{
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet results = null;
	DataSource ds;

	String createActorSql = "INSERT INTO ACTOR (ID, FIRSTNAME, LASTNAME, DATEOFBIRTH) VALUES (?, ?, ?, ?);";
	String readAllActorsSql = "SELECT * FROM ACTOR;";
	String readActorSql = "SELECT * FROM ACTOR WHERE ID=?;";
	String updateActorSql = "UPDATE ACTOR SET FIRSTNAME=? WHERE ID=?;";
	String deleteActorSql = "DELETE FROM ACTOR WHERE ID=?";
	
	public void createActor(Actor actor)
	{
		try
		{
			connection = ds.getConnection();
			statement = connection.prepareStatement(createActorSql);
			statement.setString(1, actor.getID());
			statement.setString(2, actor.getFirstName());
			statement.setString(3, actor.getLastName());
			statement.setDate(4, actor.getDateOfBirth());
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

	public List<Actor> readAllActors()
	{
		List<Actor> actors = new ArrayList<Actor>();
		try
		{
			connection = ds.getConnection();
			statement = connection.prepareStatement(readAllActorsSql);
			results = statement.executeQuery();
			while (results.next())
			{
				Actor actor = new Actor(results.getString("ID"),
						results.getString("firstName"),
						results.getString("lastName"),
						results.getDate("dateOfBirth"));
				actors.add(actor);
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
		return actors;
	}

	public Actor readActor(String actorID)
	{
		try
		{
			connection = ds.getConnection();
			statement = connection.prepareStatement(readActorSql);
			statement.setString(1, actorID);
			results = statement.executeQuery();
			if (results.next())
			{
				Actor actor = new Actor(results.getString("ID"),
						results.getString("firstName"),
						results.getString("lastName"),
						results.getDate("dateOfBirth"));
				return actor;
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

	public void updateActor(String actorID, Actor actor)
	{
		try
		{
			connection = ds.getConnection();
			statement = connection.prepareStatement(updateActorSql);
			statement.setString(1, actor.getFirstName());
			statement.setString(2, actorID);
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

	public void deleteActor(String actorID)
	{
		try
		{
			connection = ds.getConnection();
			statement = connection.prepareStatement(deleteActorSql);
			statement.setString(1, actorID);
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

	public ActorManager()
	{
		try
		{
			Context jndi = new InitialContext();
			ds = (DataSource) jndi.lookup("java:comp/env/jdbc/YLDB”);
		} catch (NamingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
