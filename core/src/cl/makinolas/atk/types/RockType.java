package cl.makinolas.atk.types;

public class RockType extends AbstractType implements IType {

	@Override
	public double attackFromType(IType type) {
		return type.rockPokemonAttacks(this);
	}

	@Override
	public double attackToType(IType type) {
		return type.monsterHasAttackedFromRock();
	}

	@Override
	public double rockPokemonAttacks(IType type) {
		return 1.5;
	}
	
	@Override
	public double monsterHasAttackedFromFight() {
		return 2;
	}

	@Override
	public double monsterHasAttackedFromFire() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromFlying() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromGrass() {
		return 2;
	}

	@Override
	public double monsterHasAttackedFromGround() {
		return 2;
	}

	@Override
	public double monsterHasAttackedFromNormal() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromPoison() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromSteel() {
		return 2;
	}

	@Override
	public double monsterHasAttackedFromWater() {
		return 2;
	}
	
}
