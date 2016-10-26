package cl.makinolas.atk.types;

public class GhostType extends AbstractType implements IType {

	@Override
	public double attackFromType(IType type) {
		return type.ghostPokemonAttacks(this);
	}

	@Override
	public double attackToType(IType type) {
		return type.monsterHasAttackedFromGhost();
	}

	@Override
	public double ghostPokemonAttacks(IType type) {
		return 1.5;
	}

}
