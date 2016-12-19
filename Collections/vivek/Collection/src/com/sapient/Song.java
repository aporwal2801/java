package com.sapient;

public class Song implements Comparable<Song>
{
	
	private String name;
	private String artist;
	private String year;
	private String ranking;
	
	public boolean  equals(Song s)					//return type boolean
	{
		return name.equals(s.name);
	}
	
	public int hashcode()							//return type int   no input
	{
		
		return name.hashCode();
	}
	
	
	public int compareTo(Song song)					//return type int
	{
		return name.compareTo(song.name);
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getArtist()
	{
		return artist;
	}
	public void setArtist(String artist)
	{
		this.artist = artist;
	}
	public String getYear()
	{
		return year;
	}
	public void setYear(String year)
	{
		this.year = year;
	}
	public String getRanking()
	{
		return ranking;
	}
	public void setRanking(String ranking)
	{
		this.ranking = ranking;
	}
	
	
	public String toString ()
	{
		return name+":"+artist+":"+year+":"+ranking;
	}
	
}
