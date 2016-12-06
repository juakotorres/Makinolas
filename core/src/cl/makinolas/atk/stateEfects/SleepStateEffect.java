package cl.makinolas.atk.stateEfects;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.friend.Friend;

public class SleepStateEffect extends AbstractStateEfects {
	
	Friend friend;
	
	public SleepStateEffect(Monsters monster){
		float value =((float) Math.random()*10+5);
		this.monster = monster;
		this.friend = monster.getMyself() ;
		this.drawEfects = new DrawStateEfects("StateImages/dormir.png", 64, 64, 5, 25, value, 2f,4, this);
	}

	@Override
	public void affectMonsters() {
		monster.sleep();
	}

	@Override
	public void destroy() {
		monster.Awake();
		super.destroy();
	}
	
}
