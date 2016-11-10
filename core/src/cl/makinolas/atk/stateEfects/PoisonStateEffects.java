package cl.makinolas.atk.stateEfects;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.friend.Friend;
import cl.makinolas.atk.types.IType;

public class PoisonStateEffects extends AbstractStateEfects {

	private Friend friend;
	private int damage;
	private float localTime = 0;
	private boolean noEfect = false;
	
	public PoisonStateEffects(Monsters monster){
		this.monster = monster;
		this.friend = monster.getMyself();
		for(IType type: monster.getMyself().getType()){
			if(type.isPoison() || type.isSteel()){
				noEfect = true;
				this.drawEfects = new DrawStateEfects("StateImages/Poisoned.png", 64, 64,0f , 8, this);
				return;
			}
		}
		double rand = 4 + Math.random()*4;
		this.drawEfects = new DrawStateEfects("StateImages/Poisoned.png", 64, 64,(float)rand , 8, this);
	}
	
	@Override
	public void affectMonsters() {
		damage = friend.getMaxHealth()/16;	
	}
	
	@Override
	public void act(float delta){
		super.act(delta);
		if(this.noEfect){
			return;
		}
		localTime+=delta;
		if(this.localTime>2){
			this.localTime=-2;
			this.friend.setHealth(friend.getHealth()-damage);
			
		}
		
	}

}
