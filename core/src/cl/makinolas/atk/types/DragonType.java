package cl.makinolas.atk.types;

public class DragonType extends AbstractType implements IType {

	public DragonType(){
		this.isDragon = true;
	}
	
	@Override
	public double attackFromType(IType type) {
		return type.dragonPokemonAttacks(this);
	}

	@Override
	public double attackToType(IType type) {
		return type.monsterHasAttackedFromDragon();
	}

	@Override
	public double dragonPokemonAttacks(IType type) {
		return 1.5;
	}
	
	@Override
	public double monsterHasAttackedFromDragon() {
		return 2;
	}

	@Override
	public double monsterHasAttackedFromElectric() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromFairy() {
		return 2;
	}

	@Override
	public double monsterHasAttackedFromFire() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromGrass() {
		return 0.5;
	}
	
	@Override
	public double monsterHasAttackedFromIce() {
		return 2;
	}

	@Override
	public double monsterHasAttackedFromWater() {
		return 0.5;
	}

}
