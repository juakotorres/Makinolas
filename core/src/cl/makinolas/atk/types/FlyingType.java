package cl.makinolas.atk.types;

public class FlyingType extends AbstractType implements IType {

	@Override
	public double attackFromType(IType type) {
		return type.flyingPokemonAttacks(this);
	}

	@Override
	public double attackToType(IType type) {
		return type.monsterHasAttackedFromFlying();
	}

	@Override
	public double flyingPokemonAttacks(IType type) {
		return 1.5;
	}

}
