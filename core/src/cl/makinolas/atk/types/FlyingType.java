package cl.makinolas.atk.types;

public class FlyingType extends AbstractType implements IType {

	@Override
	public double attackFromType(IType type) {
		return this.flyingPokemonAttacks(type);
	}

	@Override
	public double attackToType(IType type) {
		return 0;
	}

	@Override
	public double flyingPokemonAttacks(IType type) {
		return 1.5;
	}

}
