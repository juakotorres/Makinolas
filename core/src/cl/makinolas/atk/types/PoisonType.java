package cl.makinolas.atk.types;

public class PoisonType extends AbstractType implements IType {

	@Override
	public double attackFromType(IType type) {
		return type.poisonPokemonAttacks(this);
	}

	@Override
	public double attackToType(IType type) {
		return type.monsterHasAttackedFromPoison();
	}

	@Override
	public double poisonPokemonAttacks(IType type) {
		return 1.5;
	}

}
