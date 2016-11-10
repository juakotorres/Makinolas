package cl.makinolas.atk.stateEfects;

import java.util.ArrayList;

import cl.makinolas.atk.actors.Monsters;

public interface IStateEfects {

	void affect(Monsters monsters, int prob, ArrayList<IStateEfects> states);
	void affectMonsters();
	void destroy();
	void act(float delta);
	DrawStateEfects getDrawStateEfects();

}
