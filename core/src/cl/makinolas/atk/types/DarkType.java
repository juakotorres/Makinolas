package cl.makinolas.atk.types;

public class DarkType extends AbstractType implements IType {

	@Override
	public double attackFromType(IType type) {
		return this.darkPokemonAttacks(type);
	}

	@Override
	public double attackToType(IType type) {
		return 0;
	}

	@Override
	public double darkPokemonAttacks(IType type) {
		return 1.5;
	}

}
