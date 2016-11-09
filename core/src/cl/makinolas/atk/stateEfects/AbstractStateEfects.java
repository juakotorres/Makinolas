package cl.makinolas.atk.stateEfects;

import cl.makinolas.atk.actors.Monsters;

public abstract class AbstractStateEfects implements IStateEfects {

	@Override
	public void affect(Monsters monsters, int prob) {
		double val = 100*Math.random();
		if(val<prob){
			this.affectMonsters(monsters);
		}
	}

}
