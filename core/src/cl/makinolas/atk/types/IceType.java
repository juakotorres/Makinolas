package cl.makinolas.atk.types;

public class IceType extends AbstractType implements IType {

	public IceType(){
		this.isIce = true;
		System.out.println("IceCreated");
	}

	@Override
	public double attackFromType(IType type) {
		return type.icePokemonAttacks(this);
	}

	@Override
	public double attackToType(IType type) {
		return type.monsterHasAttackedFromIce();
	}
	
	@Override
	public double icePokemonAttacks(IType type) {
		return 1.5;
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
	public double monsterHasAttackedFromIce() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromRock() {
		return 2;
	}

	@Override
	public double monsterHasAttackedFromSteel() {
		return 2;
	}
	
}
