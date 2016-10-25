package cl.makinolas.atk.types;

public class WaterType extends AbstractType implements IType {

	@Override
	public double attackFromType(IType type) {
		return this.waterPokemonAttacks(type);
	}

	@Override
	public double attackToType(IType type) {
		return 0;
	}

	@Override
	public double waterPokemonAttacks(IType type) {
		return 1.5;
	}

}
