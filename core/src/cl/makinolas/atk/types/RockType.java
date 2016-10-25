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
}
