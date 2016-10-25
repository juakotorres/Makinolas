package cl.makinolas.atk.types;

public class BugType extends AbstractType implements IType {

	@Override
	public double attackFromType(IType type) {
		return this.bugPokemonAttacks(type);
	}

	@Override
	public double attackToType(IType type) {
		return 0;
	}

	@Override
	public double bugPokemonAttacks(IType type) {
		return 1.5;
	}
	
}
