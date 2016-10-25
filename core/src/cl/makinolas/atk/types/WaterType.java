package cl.makinolas.atk.types;

public class WaterType extends AbstractType implements IType {

	@Override
	public double attackFromType(IType type) {
		return type.waterPokemonAttacks(this);
	}

	@Override
	public double attackToType(IType type) {
		return type.monsterHasAttackedFromWater();
	}

	@Override
	public double waterPokemonAttacks(IType type) {
		return 1.5;
	}

}
