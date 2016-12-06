package cl.makinolas.atk.types;

public class DarkType extends AbstractType implements IType {

	public DarkType(){
		this.isDark = true;
		System.out.println("DarkCreated");
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
