package cl.makinolas.atk.types;

public class GroundType extends AbstractType implements IType {

	private static GroundType instance = null;

	private GroundType(){
		this.isGround = true;
	}

	public static GroundType getInstance(){
		if (instance == null){
			instance = new GroundType();
		}
		return instance;
	}

	@Override
	public double attackFromType(IType type) {
		return type.groundPokemonAttacks(this);
	}

	@Override
	public double attackToType(IType type) {
		return type.monsterHasAttackedFromGrass();
	}

	@Override
	public double groundPokemonAttacks(IType type) {
		return 1.5;
	}

	@Override
	public double monsterHasAttackedFromElectric() {
		return 0;
	}

	@Override
	public double monsterHasAttackedFromGrass() {
		return 2;
	}

	@Override
	public double monsterHasAttackedFromIce() {
		return 2;
	}

	@Override
	public double monsterHasAttackedFromPoison() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromRock() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromWater() {
		return 2;
	}

}