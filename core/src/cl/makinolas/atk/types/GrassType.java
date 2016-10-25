package cl.makinolas.atk.types;

public class GrassType extends AbstractType implements IType {

	@Override
	public double attackFromType(IType type) {
		return type.grassPokemonAttacks(this);
	}

	@Override
	public double attackToType(IType type) {
		return type.monsterHasAttackedFromGrass();
	}

	@Override
	public double grassPokemonAttacks(IType type) {
		return 1.5;
	}

}
