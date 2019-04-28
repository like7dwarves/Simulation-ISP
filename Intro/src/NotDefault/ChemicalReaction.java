package NotDefault;

public class ChemicalReaction 
{

	Mixture reactants; // a normal mixture, but all number of moles should be integer values
	Mixture products; // another normal mixture, but all number of moles should be integer values
	double dH; // change in energy of the reaction.
	
	public ChemicalReaction(Mixture _reactants, Mixture _products, double _dH) 
	{
		reactants = _reactants;
		products = _products;
		dH = _dH;
	}

}
