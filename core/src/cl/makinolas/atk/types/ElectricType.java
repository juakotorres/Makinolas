package cl.makinolas.atk.types;

public class ElectricType extends AbstractType implements IType {

	public ElectricType(){
		this.isElectric = true;
	}
	
	@Override
	public double attackFromType(IType type) {
		return type.electricPokemonAttacks(this);
	}

	@Override
	public double attackToType(IType type) {
		return type.monsterHasAttackedFromElectric();
	}

	@Override
	public double electricPokemonAttacks(IType type) {
		return 1.5;
	}
	
	@Override
	public double monsterHasAttackedFromElectric() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromFlying() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromGround() {
		return 2;
	}

	@Override
	public double monsterHasAttackedFromSteel() {
		return 0.5;
	}

}
