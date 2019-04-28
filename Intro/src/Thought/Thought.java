package Thought;

public class Thought 
{
	boolean linking;
	Noun subject;
	Noun object;
	Verb verb;
	public Thought(Noun n, Verb v, Noun o) 
	{
		subject=n;
		verb = v;
		object=o;
	}
	
	/*
	 * aboard about above, across after against, among along around, at before behind, below beneath beside between beyond
	 */
	
	/*
	 * So the fun thing about thoughts is that it gives us a way to define words. Instead of 
	 * hardcoding a word for disinterest, we can just say that "disinterest" is "not interest".
	 * I don't know how far I can cary this, but hopefully, we'll get to the point where complex ideas
	 * can be formed in other ways than manually.
	 */
	
	
	 /*	
	  *	Let's start with birth and death. Obviously, i'm not going to try to simulate evey complexity of intelligence,
	  * but I want to draw a parrallel showing that communities are just a larger individual. Our cells are a community 
	  * that defines us. We are a community that defines the earth. Maybe I'm wrong, but I would love to put this
	  * little chunk of my theology in the game. For this reason, living things in this game will be defined by their
	  * constant increase in viewed complexity. A NPC will see a friend as alive, since they see that their friend is
	  * more complex every day, but they will not believe that a computer is alive, because they don't encounter new 
	  * layers in their computer often enough. A society would't really be alive to a normal person, but maybe to a sociologist or politician,
	  * it would be. For this reason,
	  * 	How alive something is to another person is determined by how much information is exchanged from that person per day, or
	  * 	Life = d(info)
	  * 	Birth happens when information is able to move between two peoples, and death is the severance of that communication.
	  * 	Birth = (info == 0 && d(info)>0)
	  * 	new Thought()
	  * 	Death = (dinfo==0 && dinfo will never increase)
	  */
}
