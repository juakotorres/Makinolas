package cl.makinolas.atk.types;

public class PoisonType extends AbstractType implements IType {

	private static PoisonType instance = null;
	
	private PoisonType(){
		this.isPoison = true;
	}
	
	public static PoisonType getInstance(){
		if (instance == null){
			instance = new PoisonType();
		}
		return instance;
	}

	@Override
	public double attackFromType(IType type) {
		return type.poisonPokemonAttacks(this);
	}

	@Override
	public double attackToType(IType type) {
		return type.monsterHasAttackedFromPoison();
	}

	@Override
	public double poisonPokemonAttacks(IType type) {
		return 1.5;
	}
	
	@Override
	public double monsterHasAttackedFromBug() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromFairy() {
		return 0.5;
	}
	
	@Override
	public double monsterHasAttackedFromFight() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromGrass() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromGround() {
		return 2;
	}

	@Override
	public double monsterHasAttackedFromPoison() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromPsychic() {
		return 2;
	}

}
