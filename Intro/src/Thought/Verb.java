package Thought;


public class Verb extends Word
{
	boolean linking;
	//Action action;
	
	public Verb(String _lit, String _pronounce, boolean _linking) 
	{
		super(_lit,_pronounce);
		linking=_linking;
	}
	

}
