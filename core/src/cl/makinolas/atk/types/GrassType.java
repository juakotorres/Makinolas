package cl.makinolas.atk.types;

public class GrassType extends AbstractType implements IType {

	public GrassType(){
		this.isGrass = true;
		System.out.println("GrassCreated");
	}

	@Override
	public double attackFromType(IType type) {
		return type.grassPokemonAttacks(this);
	}

	@Override
	public double attackToType(IType type) {
		return type.monsterHasAttackedFromGrass();
	}

	@Override
	public double grassPokemonAttacks(IType type) {
		return 1.5;
	}
	
	@Override
	public double monsterHasAttackedFromBug() {
		return 2	;
	}

	@Override
	public double monsterHasAttackedFromElectric() {
		return 0.5	;
	}

	@Override
	public double monsterHasAttackedFromFire() {
		return 2;
	}

	@Override
	public double monsterHasAttackedFromFlying() {
		return 2;
	}

	@Override
	public double monsterHasAttackedFromGrass() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromGround() {
		return 0.5;
	}
	
	@Override
	public double monsterHasAttackedFromIce() {
		return 2;
	}

	@Override
	public double monsterHasAttackedFromPoison() {
		return 2;
	}

	@Override
	public double monsterHasAttackedFromWater() {
		return 0.5;
	}

}
