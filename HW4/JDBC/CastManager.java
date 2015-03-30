
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

public class CastManager
{
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet results = null;
	DataSource ds;

	String createCastSql = "INSERT INTO CAST (CASTID, ACTORID, MOVIEID, CHARACTERNAME) VALUES (?, ?, ?, ?);";
	String readAllCastsSql = "SELECT * FROM CAST;";
	String readAllCastsForActorSql = "SELECT * FROM CAST WHERE ACTORID = ?;";
	String readAllCastsForMovieSql = "SELECT * FROM CAST WHERE MOVIEID = ?";
	String readCastForIdSql = "SELECT * FROM CAST WHERE CASTID=?;";
	String updateCastSql = "UPDATE CAST SET CHARACTERNAME=? WHERE CASTID=?;";
	String deleteCastSql = "DELETE FROM CAST WHERE COMMENTID=?";

	public void createCast(Cast newCast)
	{
		try
		{
			connection = ds.getConnection();
			statement = connection.prepareStatement(createCastSql);
			statement.setString(1, newCast.getCastID());
			statement.setString(2, newCast.getActorID());
			statement.setString(3, newCast.getMovieID());
			statement.setString(4, newCast.getCharacterName());
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

	public List<Cast> readAllComments()
	{
		List<Cast> casts = new ArrayList<Cast>();
		try
		{
			connection = ds.getConnection();
			statement = connection.prepareStatement(readAllCastsSql);
			results = statement.executeQuery();
			while (results.next())
			{
				Cast cast = new Cast(results.getString("castID"),
						results.getString("movieID"),
						results.getString("actorID"),
						results.getString("characterName"));
				casts.add(cast);
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
		return casts;
	}

	public List<Cast> readAllCastsForActor(String actorId)
	{
		List<Cast> casts = new ArrayList<Cast>();
		try
		{
			connection = ds.getConnection();
			statement = connection.prepareStatement(readAllCastsForActorSql);
			statement.setString(1, actorId);
			results = statement.executeQuery();
			while (results.next())
			{
				Cast cast = new Cast(results.getString("castID"),
						results.getString("movieID"),
						results.getString("actorID"),
						results.getString("characterName"));
				casts.add(cast);
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
		return casts;
	}

	public List<Cast> readAllCastsForMovie(String movieId)
	{
		List<Cast> casts = new ArrayList<Cast>();
		try
		{
			connection = ds.getConnection();
			statement = connection.prepareStatement(readAllCastsForMovieSql);
			statement.setString(1, movieId);
			results = statement.executeQuery();
			while (results.next())
			{
				Cast cast = new Cast(results.getString("castID"),
						results.getString("movieID"),
						results.getString("actorID"),
						results.getString("characterName"));
				casts.add(cast);
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
		return casts;
	}

	public Cast readCastForId(String castId)
	{
		try
		{
			connection = ds.getConnection();
			statement = connection.prepareStatement(readCastForIdSql);
			statement.setString(1, castId);
			results = statement.executeQuery();
			if (results.next())
			{
				Cast cast = new Cast(results.getString("castID"),
						results.getString("movieID"),
						results.getString("actorID"),
						results.getString("characterName"));
				return cast;
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

	public void updateCast(String castId, Cast newCast)
	{
		try
		{
			connection = ds.getConnection();
			statement = connection.prepareStatement(updateCastSql);
			statement.setString(1, newCast.getCharacterName());
			statement.setString(2, castId);
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

	public void deleteCast(String castId)
	{
		try
		{
			connection = ds.getConnection();
			statement = connection.prepareStatement(deleteCastSql);
			statement.setString(1, castId);
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

	public CastManager()
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
