package cl.makinolas.atk.types;

public class SteelType extends AbstractType implements IType {

	public SteelType() {
		this.isSteel=true;
	}

	@Override
	public double attackFromType(IType type) {
		return type.steelPokemonAttacks(this);
	}

	@Override
	public double attackToType(IType type) {
		return type.monsterHasAttackedFromSteel();
	}

	@Override
	public double steelPokemonAttacks(IType type) {
		return 1.5;
	}
	
	@Override
	public double monsterHasAttackedFromBug() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromDragon() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromFairy() {
		return 0.5;
	}
	
	@Override
	public double monsterHasAttackedFromFight() {
		return 2;
	}

	@Override
	public double monsterHasAttackedFromFire() {
		return 2;
	}

	@Override
	public double monsterHasAttackedFromFlying() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromGrass() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromGround() {
		return 2;
	}
	
	@Override
	public double monsterHasAttackedFromIce() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromNormal() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromPoison() {
		return 0;
	}

	@Override
	public double monsterHasAttackedFromPsychic() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromRock() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromSteel() {
		return 0.5;
	}

}
