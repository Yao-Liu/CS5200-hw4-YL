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

public class CommentManager
{
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet results = null;
	DataSource ds;

	String createCommentSql = "INSERT INTO COMMENT (COMMENTID, MOVIEID, USERID, COMMENT, DATE) VALUES (?, ?, ?, ?, ?);";
	String readAllCommentsSql = "SELECT * FROM COMMENT;";
	String readAllCommentsForUsernameSql = "SELECT * FROM COMMENT WHERE USERNAME = ?;";
	String readAllCommentsForMovieSql = "SELECT * FROM COMMENT WHERE MOVIEID = ?";
	String readComment = "SELECT * FROM COMMENT WHERE COMMENTID=?;";
	String updateCommentSql = "UPDATE COMMENT SET COMMENT=? WHERE COMMENTID=?;";
	String deleteCommentSql = "DELETE FROM COMMENT WHERE COMMENTID=?";

	public void createComment(Comment newComment)
	{
		try
		{
			connection = ds.getConnection();
			statement = connection.prepareStatement(createCommentSql);
			statement.setString(1, newComment.getCommentId());
			statement.setString(2, newComment.getMovieID());
			statement.setString(3, newComment.getUserid());
			statement.setString(4, newComment.getComment());
			statement.setDate(5, newComment.getDate());
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

	public List<Comment> readAllComments()
	{
		List<Comment> comments = new ArrayList<Comment>();
		try
		{
			connection = ds.getConnection();
			statement = connection.prepareStatement(readAllCommentsSql);
			results = statement.executeQuery();
			while (results.next())
			{
				Comment comment = new Comment(results.getString("commmentId"),
						results.getString("movieID"),
						results.getString("userid”),
						results.getString("comment"),                         
                                                results.getDate("date"));
				comments.add(comment);
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
		return comments;
	}

	public List<Comment> readAllCommentsForUsername(String username)
	{
		List<Comment> comments = new ArrayList<Comment>();
		try
		{
			connection = ds.getConnection();
			statement = connection.prepareStatement(readComment);
			statement.setString(1, username);
			results = statement.executeQuery();
			while (results.next())
			{
				Comment comment = new Comment(results.getString("commmentId"),
						results.getString("movieID"),
						results.getString("username"),
						results.getString("comment"),
                                                results.getDate("date"));
				comments.add(comment);
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
		return comments;
	}

	public List<Comment> readAllCommentsForMovie(String movieId)
	{
		List<Comment> comments = new ArrayList<Comment>();
		try
		{
			connection = ds.getConnection();
			statement = connection.prepareStatement(readComment);
			statement.setString(1, movieId);
			results = statement.executeQuery();
			while (results.next())
			{
				Comment comment = new Comment(results.getString("commmentId"),
						results.getString("movieID"),
						results.getString("userid”),
						results.getString("comment"), results.getDate("date"));
				comments.add(comment);
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
		return comments;
	}

	public Comment readCommentForId(String commentId)
	{
		try
		{
			connection = ds.getConnection();
			statement = connection.prepareStatement(readComment);
			statement.setString(1, commentId);
			results = statement.executeQuery();
			if (results.next())
			{
				Comment comment = new Comment(results.getString("commmentId"),
						results.getString("movieID"),
						results.getString("userid”),
						results.getString("comment"), results.getDate("date"));
				return comment;
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

	public void updateComment(String commentId, Comment newComment)
	{
		try
		{
			connection = ds.getConnection();
			statement = connection.prepareStatement(updateCommentSql);
			statement.setString(1, newComment.getComment());
			statement.setString(2, commentId);
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

	public void deleteComment(String commentId)
	{
		try
		{
			connection = ds.getConnection();
			statement = connection.prepareStatement(deleteCommentSql);
			statement.setString(1, commentId);
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

	public CommentManager()
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
