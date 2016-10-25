package cl.makinolas.atk.types;

public class NormalType extends AbstractType implements IType {

	@Override
	public double attackFromType(IType type) {
		return this.normalPokemonAttacks(type);
	}

	@Override
	public double attackToType(IType type) {
		return 0;
	}

	@Override
	public double normalPokemonAttacks(IType type) {
		return 1.5;
	}
}
