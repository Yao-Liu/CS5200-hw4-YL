package DAO;

public class Cast
{
	protected String castID;
	protected String actorID;
	protected String movieID;
	protected String characterName;
	
	public Cast(){}
	public Cast(String castID, String actorID, String movieID, String characterName)
	{
		super();
		this.castID = castID;
		this.actorID = actorID;
		this.movieID = movieID;
		this.characterName = characterName;	
	}
	
	
	public String getCastID()
	{
		return castID;
	}
	public void setCastID(String castID)
	{
		this.castID = castID;
	}
	public String getActorID()
	{
		return actorID;
	}
	public void setActorID(String actorID)
	{
		this.actorID = actorID;
	}
	public String getMovieID()
	{
		return movieID;
	}
	public void setMovieID(String movieID)
	{
		this.movieID = movieID;
	}
	public String getCharacterName()
	{
		return characterName;
	}
	public void setCharacterName(String characterName)
	{
		this.characterName = characterName;
	}

}
