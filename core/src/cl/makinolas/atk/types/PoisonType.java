package cl.makinolas.atk.types;

public class PoisonType extends AbstractType implements IType {

	@Override
	public double attackFromType(IType type) {
		return this.poisonPokemonAttacks(type);
	}

	@Override
	public double attackToType(IType type) {
		return 0;
	}

	@Override
	public double poisonPokemonAttacks(IType type) {
		return 1.5;
	}

}
