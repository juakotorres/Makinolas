package cl.makinolas.atk.types;

public class FairyType extends AbstractType implements IType {
	
	public FairyType(){
		this.isFairy = true;
		System.out.println("FairyCreated");
	}

	@Override
	public double attackFromType(IType type) {
		return type.fairyPokemonAttacks(this);
	}

	@Override
	public double attackToType(IType type) {
		return type.monsterHasAttackedFromFairy();
	}

	@Override
	public double fairyPokemonAttacks(IType type) {
		return 1.5;
	}
	
	@Override
	public double monsterHasAttackedFromBug() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromDark() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromDragon() {
		return 0;
	}

	@Override
	public double monsterHasAttackedFromFight() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromPoison() {
		return 2;
	}

	@Override
	public double monsterHasAttackedFromSteel() {
		return 2;
	}

}
