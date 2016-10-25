package cl.makinolas.atk.types;

public class GroundType extends AbstractType implements IType {

	@Override
	public double attackFromType(IType type) {
		return type.groundPokemonAttacks(this);
	}

	@Override
	public double attackToType(IType type) {
		return type.monsterHasAttackedFromGrass();
	}

	@Override
	public double groundPokemonAttacks(IType type) {
		return 1.5;
	}

}
