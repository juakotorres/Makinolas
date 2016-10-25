package cl.makinolas.atk.types;

public class PsychicType extends AbstractType implements IType {

	@Override
	public double attackFromType(IType type) {
		return this.psychicPokemonAttacks(type);
	}

	@Override
	public double attackToType(IType type) {
		return 0;
	}

	@Override
	public double psychicPokemonAttacks(IType type) {
		return 1.5;
	}

}
