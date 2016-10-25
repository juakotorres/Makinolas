package cl.makinolas.atk.types;

public interface IType {
	
	double attackFromType(IType type);

	double attackToType(IType type);	
	
	public double bugPokemonAttacks(IType type);
	public double darkPokemonAttacks(IType type);
	public double dragonPokemonAttacks(IType type) ;
	public double electricPokemonAttacks(IType type) ;
	public double fairyPokemonAttacks(IType type) ;
	public double firePokemonAttacks(IType type);
	public double flyingPokemonAttacks(IType type);
	public double ghostPokemonAttacks(IType type);
	public double grassPokemonAttacks(IType type) ;
	public double groundPokemonAttacks(IType type) ;
	public double normalPokemonAttacks(IType type);
	public double poisonPokemonAttacks(IType type);
	public double psychicPokemonAttacks(IType type) ;
	public double rockPokemonAttacks(IType type) ;
	public double steelPokemonAttacks(IType type) ;
	public double waterPokemonAttacks(IType type);
	
	
}
