package DAO;

import java.sql.Date;

public class Movie
{
	protected String ID;
	protected String title;
	protected String posterImage;
	protected Date releaseDate;

	public Movie(){}
	public Movie(String ID, String title, String posterImage, Date releaseDate)
	{
		super();
		this.ID = ID;
		this.title = title;
		this.posterImage = posterImage;
		this.releaseDate = releaseDate;
	}
	
	public String getID()
	{
		return ID;
	}
	public void setID(String ID)
	{
		this.ID = ID;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getPosterImage()
	{
		return posterImage;
	}
	public void setPosterImage(String posterImage)
	{
		this.posterImage = posterImage;
	}
	public Date getReleaseDate()
	{
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate)
	{
		this.releaseDate = releaseDate;
	}
	
}
