package cl.makinolas.atk.types;

public class FairyType extends AbstractType implements IType {

	@Override
	public double attackFromType(IType type) {
		return this.fairyPokemonAttacks(type);
	}

	@Override
	public double attackToType(IType type) {
		return 0;
	}

	@Override
	public double fairyPokemonAttacks(IType type) {
		return 1.5;
	}

}
