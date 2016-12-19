package com.sapient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Comprable
{
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Song a = new Song();
		a.setArtist("viv");
		a.setName("name1");
		a.setRanking("1");
		a.setYear("1997");
		
		Song b = new Song();
		b.setArtist("tyagi");
		b.setName("name2");
		b.setRanking("2");
		b.setYear("1998");
		
		List<Song> songList =new ArrayList<Song>();
		songList.add(a);
		songList.add(b);
		
		Collections.sort(songList);
		
		System.out.println(songList);
		
		// Creating Inner Class
		class ArtisticComprator implements  Comparator<Song>
		{
			public int compare(Song a , Song b)                // Return type int
			{
				return a.getArtist().compareTo(b.getArtist());
			}
		}
		
Collections.sort(songList,new ArtisticComprator());
		
		System.out.println(songList);
		
		
	}
	
}
