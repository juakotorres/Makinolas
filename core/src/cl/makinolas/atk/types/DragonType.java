package cl.makinolas.atk.types;

public class DragonType extends AbstractType implements IType {

	@Override
	public double attackFromType(IType type) {
		return this.dragonPokemonAttacks(type);
	}

	@Override
	public double attackToType(IType type) {
		return 0;
	}

	@Override
	public double dragonPokemonAttacks(IType type) {
		return 1.5;
	}

}
