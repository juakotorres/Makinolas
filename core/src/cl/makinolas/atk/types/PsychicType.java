package cl.makinolas.atk.types;

public class PsychicType extends AbstractType implements IType {

	@Override
	public double attackFromType(IType type) {
		return type.psychicPokemonAttacks(this);
	}

	@Override
	public double attackToType(IType type) {
		return type.monsterHasAttackedFromPsychic();
	}

	@Override
	public double psychicPokemonAttacks(IType type) {
		return 1.5;
	}

}
