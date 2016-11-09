package cl.makinolas.atk.stateEfects;

import java.lang.reflect.Modifier;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.friend.Friend;

public class StateEfectsCriticRate extends Modifier {

	public void affectMonsters(Monsters monsters, long duration) {
		Friend friend = monsters.getMyself() ;
		int  critic = friend.getCriticModificator();
		friend.setCriticModificator(critic +1);

		// WorkingPRogress
	}

}
