package cl.makinolas.atk.stateEfects;

import cl.makinolas.atk.actors.Monsters;

public interface IStateEfects {

	void affect(Monsters monsters, int prob);
	void affectMonsters(Monsters monsters);

}
