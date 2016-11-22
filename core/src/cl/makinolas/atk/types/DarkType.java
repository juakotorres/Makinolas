package cl.makinolas.atk.types;

public class DarkType extends AbstractType implements IType {

	private static DarkType instance = null;

	private DarkType(){
		this.isDark = true;
	}

	public static DarkType getInstance(){
		if (instance == null){
			instance = new DarkType();
		}
		return instance;
	}

	@Override
	public double attackFromType(IType type) {
		return type.darkPokemonAttacks(this);
	}

	@Override
	public double attackToType(IType type) {
		return type.monsterHasAttackedFromDark();
	}

	@Override
	public double darkPokemonAttacks(IType type) {
		return 1.5;
	}

	@Override
	public double monsterHasAttackedFromBug() {
		return 2;
	}

	@Override
	public double monsterHasAttackedFromDark() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromFairy() {
		return 2;
	}

	@Override
	public double monsterHasAttackedFromGhost() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromPsychic() {
		return 0;
	}

}