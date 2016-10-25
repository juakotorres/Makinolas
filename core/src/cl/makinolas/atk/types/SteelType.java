package cl.makinolas.atk.types;

public class SteelType extends AbstractType implements IType {

	@Override
	public double attackFromType(IType type) {
		return type.steelPokemonAttacks(this);
	}

	@Override
	public double attackToType(IType type) {
		return type.monsterHasAttackedFromSteel();
	}

	@Override
	public double steelPokemonAttacks(IType type) {
		return 1.5;
	}

}
