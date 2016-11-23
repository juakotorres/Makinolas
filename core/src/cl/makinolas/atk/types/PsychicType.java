package cl.makinolas.atk.types;

public class PsychicType extends AbstractType implements IType {

	private static PsychicType instance = null;
	
	private PsychicType(){
		this.isPsychic = true;
	}
	
	public static PsychicType getInstance(){
		if (instance == null){
			instance = new PsychicType();
		}
		return instance;
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
