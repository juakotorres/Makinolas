package cl.makinolas.atk.types;

public class FireType extends AbstractType implements IType {

	@Override
	public double attackFromType(IType type) {
		return type.firePokemonAttacks(this);
	}

	@Override
	public double attackToType(IType type) {
		return type.monsterHasAttackedFromFire();
	}

	@Override
	public double firePokemonAttacks(IType type) {
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
	public double monsterHasAttackedFromFire() {
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
	public double monsterHasAttackedFromIce() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromRock() {
		return 2;
	}

	@Override
	public double monsterHasAttackedFromSteel() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromWater() {
		return  2;
	}

}
