package cl.makinolas.atk.types;

public class WaterType extends AbstractType implements IType {

	public WaterType() {
		this.isWater=true;
	}

	@Override
	public double attackFromType(IType type) {
		return type.waterPokemonAttacks(this);
	}

	@Override
	public double attackToType(IType type) {
		return type.monsterHasAttackedFromWater();
	}

	@Override
	public double waterPokemonAttacks(IType type) {
		return 1.5;
	}

	@Override
	public double monsterHasAttackedFromElectric() {
		return 2;
	}

	@Override
	public double monsterHasAttackedFromFire() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromGrass() {
		return 2;
	}

	@Override
	public double monsterHasAttackedFromIce() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromSteel() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromWater() {
		return 0.5;
	}

}
