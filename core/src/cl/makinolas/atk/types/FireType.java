package cl.makinolas.atk.types;

public class FireType extends AbstractType implements IType {

	@Override
	public double attackFromType(IType type) {
		return type.firePokemonAttacks(this);
	}

	@Override
	public double attackToType(IType type) {
		return type.monsterHasAttackedFromFire();
	}

	@Override
	public double firePokemonAttacks(IType type) {
		return 1.5;
	}

}
