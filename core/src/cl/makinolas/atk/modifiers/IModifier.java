package cl.makinolas.atk.modifiers;

import cl.makinolas.atk.actors.Monsters;

public interface IModifier {

	void affect(Monsters monsters, int prob);
	void affectMonsters(Monsters monsters);

}
