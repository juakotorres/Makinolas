package cl.makinolas.atk.types;

public class SteelType extends AbstractType implements IType {

	@Override
	public double attackFromType(IType type) {
		return this.steelPokemonAttacks(type);
	}

	@Override
	public double attackToType(IType type) {
		return 0;
	}

	@Override
	public double steelPokemonAttacks(IType type) {
		return 1.5;
	}

}
