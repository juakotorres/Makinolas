package cl.makinolas.atk.modifiers;

import cl.makinolas.atk.actors.Monsters;

public abstract class AbstractModifier implements IModifier {

	@Override
	public void affect(Monsters monsters, int prob) {
		double val = 100*Math.random();
		if(val<prob){
			this.affectMonsters(monsters);
		}
	}

}
