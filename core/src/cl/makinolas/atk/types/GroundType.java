package cl.makinolas.atk.types;

public class GroundType extends AbstractType implements IType {

	@Override
	public double attackFromType(IType type) {
		return this.groundPokemonAttacks(type);
	}

	@Override
	public double attackToType(IType type) {
		return 0;
	}

	@Override
	public double groundPokemonAttacks(IType type) {
		return 1.5;
	}

}
