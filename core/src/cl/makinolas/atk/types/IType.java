package cl.makinolas.atk.types;

public interface IType {
	
	double attackFromType(IType type);

	double attackToType(IType type);	
	
	public double bugPokemonAttacks(IType type);
	public double darkPokemonAttacks(IType type);
	public double dragonPokemonAttacks(IType type) ;
	public double electricPokemonAttacks(IType type) ;
	public double fairyPokemonAttacks(IType type) ;
	public double fightPokemonAttacks(IType type) ;
	public double firePokemonAttacks(IType type);
	public double flyingPokemonAttacks(IType type);
	public double ghostPokemonAttacks(IType type);
	public double grassPokemonAttacks(IType type) ;
	public double groundPokemonAttacks(IType type) ;
	public double icePokemonAttacks(IType type) ;
	public double normalPokemonAttacks(IType type);
	public double poisonPokemonAttacks(IType type);
	public double psychicPokemonAttacks(IType type) ;
	public double rockPokemonAttacks(IType type) ;
	public double steelPokemonAttacks(IType type) ;
	public double waterPokemonAttacks(IType type);

	public double monsterHasAttackedFromBug();
	public double monsterHasAttackedFromDark();
	public double monsterHasAttackedFromDragon();
	public double monsterHasAttackedFromElectric();
	public double monsterHasAttackedFromFairy();
	public double monsterHasAttackedFromFight();
	public double monsterHasAttackedFromFire();
	public double monsterHasAttackedFromFlying();
	public double monsterHasAttackedFromGhost();
	public double monsterHasAttackedFromGrass();
	public double monsterHasAttackedFromGround();
	public double monsterHasAttackedFromIce();
	public double monsterHasAttackedFromNormal();
	public double monsterHasAttackedFromPoison();
	public double monsterHasAttackedFromPsychic();
	public double monsterHasAttackedFromRock();
	public double monsterHasAttackedFromSteel();
	public double monsterHasAttackedFromWater();
	
	
	
	
}
