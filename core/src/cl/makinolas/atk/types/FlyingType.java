package cl.makinolas.atk.types;

public class FlyingType extends AbstractType implements IType {

	@Override
	public double attackFromType(IType type) {
		return type.flyingPokemonAttacks(this);
	}

	@Override
	public double attackToType(IType type) {
		return type.monsterHasAttackedFromFlying();
	}

	@Override
	public double flyingPokemonAttacks(IType type) {
		return 1.5;
	}
	
	@Override
	public double monsterHasAttackedFromBug() {
		return 0.5;
	}
	
	@Override
	public double monsterHasAttackedFromElectric() {
		return 2;
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
		return 0;
	}
	
	@Override
	public double monsterHasAttackedFromIce() {
		return 2;
	}
	
	@Override
	public double monsterHasAttackedFromRock() {
		return 2;
	}

}
