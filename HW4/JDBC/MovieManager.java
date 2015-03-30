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

public class MovieManager
{
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet results = null;
	DataSource ds;

	String createMovieSql = "INSERT INTO USER (ID, TITLE, POSTERIMAGE, REALEASEDATE) VALUES (?, ?, ?, ?);";
	String readAllMoviesSql = "SELECT * FROM MOVIE;";
	String readMovie = "SELECT * FROM MOVIE WHERE ID=?;";
	String updateMovieSql = "UPDATE MOVIE SET TITLE=? WHERE ID=?;";
	String deleteMovieSql = "DELETE FROM MOVIE WHERE ID=?";

	public void createMoive(Movie movie)
	{
		try
		{
			connection = ds.getConnection();
			statement = connection.prepareStatement(createMovieSql);
			statement.setString(1, movie.getID());
			statement.setString(2, movie.getTitle());
			statement.setString(3, movie.getPosterImage());
			statement.setDate(4, movie.getReleaseDate());
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

	public List<Movie> readAllMovies()
	{
		List<Movie> movies = new ArrayList<Movie>();
		try
		{
			connection = ds.getConnection();
			statement = connection.prepareStatement(readAllMoviesSql);
			results = statement.executeQuery();
			while (results.next())
			{
				Movie movie = new Movie(results.getString("ID"),
						results.getString("title"),
						results.getString("posterImage"),
						results.getDate("releaseDate"));
				movies.add(movie);
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
		return movies;
	}

	public Movie readMovie(String movieID)
	{
		try
		{
			connection = ds.getConnection();
			statement = connection.prepareStatement(readMovie);
			statement.setString(1, movieID);
			results = statement.executeQuery();
			if (results.next())
			{
				Movie movie = new Movie(results.getString("ID"),
						results.getString("title"),
						results.getString("posterImage"),
						results.getDate("releaseDate"));
				return movie;
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

	public void updateMovie(String movieID, Movie movie)
	{
		try
		{
			connection = ds.getConnection();
			statement = connection.prepareStatement(updateMovieSql);
			statement.setString(1, movie.getTitle());
			statement.setString(2, movieID);
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

	public void deleteMovie(String movieID)
	{
		try
		{
			connection = ds.getConnection();
			statement = connection.prepareStatement(deleteMovieSql);
			statement.setString(1, movieID);
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

	public MovieManager()
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
