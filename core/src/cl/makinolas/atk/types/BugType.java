package cl.makinolas.atk.types;

public class BugType extends AbstractType implements IType {

	private static BugType instance = null;
	
	private BugType(){
		this.isBug = true;
	}
	
	public static BugType getInstance(){
		if (instance == null){
			instance = new BugType();
		}
		return instance;
	}
	
	@Override
	public double attackFromType(IType type) {
		return type.bugPokemonAttacks(this);
	}

	@Override
	public double attackToType(IType type) {
		return type.monsterHasAttackedFromBug();
	}

	@Override
	public double bugPokemonAttacks(IType type) {
		return 1.5;
	}
	
	@Override
	public double monsterHasAttackedFromFight() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromFire() {
		return 2;
	}

	@Override
	public double monsterHasAttackedFromFlying() {
		return 2;
	}

	@Override
	public double monsterHasAttackedFromGrass() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromGround() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromRock() {
		return 2;
	}
	
}
