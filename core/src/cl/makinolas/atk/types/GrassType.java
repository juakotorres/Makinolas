package cl.makinolas.atk.types;

public class GrassType extends AbstractType implements IType {

	@Override
	public double attackFromType(IType type) {
		return this.grassPokemonAttacks(type);
	}

	@Override
	public double attackToType(IType type) {
		return 0;
	}

	@Override
	public double grassPokemonAttacks(IType type) {
		return 1.5;
	}

}
