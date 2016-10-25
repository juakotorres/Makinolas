package cl.makinolas.atk.types;

public class ElectricType extends AbstractType implements IType {

	@Override
	public double attackFromType(IType type) {
		return type.electricPokemonAttacks(this);
	}

	@Override
	public double attackToType(IType type) {
		return type.monsterHasAttackedFromElectric();
	}

	@Override
	public double electricPokemonAttacks(IType type) {
		return 1.5;
	}

}
