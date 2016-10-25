package cl.makinolas.atk.types;

public class FairyType extends AbstractType implements IType {

	@Override
	public double attackFromType(IType type) {
		return type.fairyPokemonAttacks(this);
	}

	@Override
	public double attackToType(IType type) {
		return type.monsterHasAttackedFromFairy();
	}

	@Override
	public double fairyPokemonAttacks(IType type) {
		return 1.5;
	}

}
