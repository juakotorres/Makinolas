package cl.makinolas.atk.types;

public class GhostType extends AbstractType implements IType {

	public GhostType(){
		this.isGhost = true;
	}

	@Override
	public double attackFromType(IType type) {
		return type.ghostPokemonAttacks(this);
	}

	@Override
	public double attackToType(IType type) {
		return type.monsterHasAttackedFromGhost();
	}

	@Override
	public double ghostPokemonAttacks(IType type) {
		return 1.5;
	}
	
	@Override
	public double monsterHasAttackedFromBug() {
		return 0.5;
	}

	@Override
	public double monsterHasAttackedFromDark() {
		return 2;
	}

	@Override
	public double monsterHasAttackedFromFight() {
		return 0;
	}
	
	@Override
	public double monsterHasAttackedFromGhost() {
		return 2;
	}

	@Override
	public double monsterHasAttackedFromNormal() {
		return 0;
	}

	@Override
	public double monsterHasAttackedFromPoison() {
		return 0.5;
	}

}
