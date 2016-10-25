package cl.makinolas.atk.types;

public class ElectricType extends AbstractType implements IType {

	@Override
	public double attackFromType(IType type) {
		return this.electricPokemonAttacks(type);
	}

	@Override
	public double attackToType(IType type) {
		return 0;
	}

	@Override
	public double electricPokemonAttacks(IType type) {
		return 1.5;
	}

}
