package cl.makinolas.atk.stateEfects;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.friend.Friend;

public class StateEfectsCriticRate extends AbstractStateEfects {
	
	private int basecritic;
	private Friend friend;

	
	public StateEfectsCriticRate(Monsters monster){
		this.drawEfects = new DrawStateEfects("StateImages/Poisoned.png", 64, 64, 2f, 8, this);
		this.monster = monster;
		this.friend = monster.getMyself() ;
		this.basecritic = friend.getCriticModificator();
	}

	@Override
	public void affectMonsters() {
		friend.setCriticModificator(basecritic +1);		
	}
	
	@Override
	public void destroy() {
		friend.setCriticModificator(basecritic);		
		super.destroy();
	}


}
