package cl.makinolas.atk.types;

public class GhostType extends AbstractType implements IType {

	@Override
	public double attackFromType(IType type) {
		return this.ghostPokemonAttacks(type);
	}

	@Override
	public double attackToType(IType type) {
		return 0;
	}

	@Override
	public double ghostPokemonAttacks(IType type) {
		return 1.5;
	}

}
