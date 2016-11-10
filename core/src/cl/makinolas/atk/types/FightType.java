package cl.makinolas.atk.types;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class FightType extends AbstractType implements IType {

	@Override
	public double attackFromType(IType type) {
		return type.fightPokemonAttacks(this);
	}

	@Override
	public double attackToType(IType type) {
		return type.monsterHasAttackedFromFight();
	}
	
	@Override
	public double fightPokemonAttacks(IType type) {
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
	public double monsterHasAttackedFromFairy() {
		return 2;
	}

	@Override
	public double monsterHasAttackedFromFlying() {
		return 2;
	}

	@Override
	public double monsterHasAttackedFromPsychic() {
		return 2;
	}

	@Override
	public double monsterHasAttackedFromRock() {
		return 0.5;
	}

}
