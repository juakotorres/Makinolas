package cl.makinolas.atk.types;

public class FireType extends AbstractType implements IType {

	@Override
	public double attackFromType(IType type) {
		return this.firePokemonAttacks(type);
	}

	@Override
	public double attackToType(IType type) {
		return 0;
	}

	@Override
	public double firePokemonAttacks(IType type) {
		return 1.5;
	}

}
