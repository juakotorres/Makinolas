package cl.makinolas.atk.types;

public class RockType extends AbstractType implements IType {

	@Override
	public double attackFromType(IType type) {
		return this.rockPokemonAttacks(type);
	}

	@Override
	public double attackToType(IType type) {
		return 0;
	}

	@Override
	public double rockPokemonAttacks(IType type) {
		return 1.5;
	}
}
