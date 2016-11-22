package cl.makinolas.atk.stateEfects;

import cl.makinolas.atk.actors.Monsters;

public class CriticalHit extends AbstractStateEfects {


	public CriticalHit(Monsters monster){
		this.drawEfects = new DrawStateEfects("StateImages/Critical.png", 64, 64, 0.8f, 0.8f, 1, this);
		this.monster = monster;
	}

	@Override
	public void affectMonsters() {}

}