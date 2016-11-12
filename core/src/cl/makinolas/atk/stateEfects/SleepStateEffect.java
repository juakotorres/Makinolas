package cl.makinolas.atk.stateEfects;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.friend.Friend;

public class SleepStateEffect extends AbstractStateEfects {
	
	Friend friend;
	
	public SleepStateEffect(Monsters monster){
		float value =((float) Math.random()*10+5);
		this.drawEfects = new DrawStateEfects("StateImages/dormir.png", 64, 64, value, 4, this);
		this.monster = monster;
		this.friend = monster.getMyself() ;
	}

	@Override
	public void affectMonsters() {
		monster.sleep();
	}

	@Override
	public void destroy() {
		monster.unSleep();
		super.destroy();
	}
	
}
