package NotDefault;

public class Mixture
{
	String name;
	Chemical[] chemicals;
	double[] moles; //moles of each chemical
	
	public Mixture(String name, Chemical[] _chemicals, double[] _moles) 
	{
		this.name = name;
		chemicals = new Chemical[_chemicals.length];
		for(int i=0; i<chemicals.length; i++)
		{
			chemicals[i] = _chemicals[i];
			moles = _moles;
		}
	}
	
	public void addMixture(Mixture m)
	{	
		for(int i=0; i<m.chemicals.length; i++)
		{
			boolean chemicalFound = false;
			for(int j=0; j<chemicals.length; j++)
			{
				if(chemicals[j]==m.chemicals[i])
				{
					moles[j]+=m.moles[i];
					chemicalFound = true;
				}
			}
			if(!chemicalFound)
			{
				Chemical[] tempCs = new Chemical[chemicals.length+1];
				double[] tempMs = new double[chemicals.length+1];
				for(int j=0; j<chemicals.length; j++)
				{
					tempCs[j] = chemicals[j];
					tempMs[j] = moles[j];
				}
				tempCs[chemicals.length] = m.chemicals[i];
				tempMs[chemicals.length] = m.moles[i];
				chemicals = tempCs;
				moles = tempMs;
			}
		}
	}
	
	public String toString()
	{
		String returned = "";
		returned = name + "\n";
		for(int i=0; i<chemicals.length; i++)
			returned = returned+"\t"+chemicals[i]+": "+moles[i];
		return returned;
	}

}
