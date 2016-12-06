package cl.makinolas.atk.types;

public class PsychicType extends AbstractType implements IType {

	public PsychicType(){
		this.isPsychic = true;
		System.out.println("PsychicCreated");
	}

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

	@Override
	public double monsterHasAttackedFromBug() {
		return 2;
	}

	@Override
	public double monsterHasAttackedFromDark() {
		return 2;
	}
	
	@Override
	public double monsterHasAttackedFromFight() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromGhost() {
		return 2;
	}

	@Override
	public double monsterHasAttackedFromPsychic() {
		return 0.5;
	}
	
}
