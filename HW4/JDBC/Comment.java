package DAO;

import java.sql.Date;

public class Comment
{
	protected String commentId;
	protected String movieID;
	protected String userid;
	protected String comment;
	protected Date date;
	
	public Comment(){};
	public Comment(String commentId, String movieID, String userid, String comment, Date date)
	{
		super();
		this.commentId = commentId;
		this.movieID = movieID;
		this.userid = userid;
		this.comment = comment;
		this.date = date;
	}
	
	public String getCommentId()
	{
		return commentId;
	}
	public void setCommentId(String commentId)
	{
		this.commentId = commentId;
	}
	public String getMovieID()
	{
		return movieID;
	}
	public void setMovieID(String movieID)
	{
		this.movieID = movieID;
	}
	public String getUserid()
	{
		return userid;
	}
	public void setUserid(String userid)
	{
		this.userid = userid;
	}
	public String getComment()
	{
		return comment;
	}
	public void setComment(String comment)
	{
		this.comment = comment;
	}
	public Date getDate()
	{
		return date;
	}
	public void setDate(Date date)
	{
		this.date = date;
	}
	


}
