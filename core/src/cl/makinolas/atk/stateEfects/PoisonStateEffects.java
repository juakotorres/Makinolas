package cl.makinolas.atk.stateEfects;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.friend.Friend;
import cl.makinolas.atk.types.IType;
import cl.makinolas.atk.types.PoisonType;
import cl.makinolas.atk.types.SteelType;

public class PoisonStateEffects extends AbstractStateEfects {

	private Friend friend;
	private int damage;
	private float localTime = 0;
	private boolean noEfect = false;
	
	public PoisonStateEffects(Monsters monster){
		this.monster = monster;
		this.friend = monster.getMyself();
		for(IType type: monster.getMyself().getType()){
			if((type.attackFromType(new PoisonType()) == 0) || (type.attackFromType(new SteelType()) == 0)){
				noEfect = true;
				this.drawEfects = new DrawStateEfects("StateImages/Poisoned.png", 64, 64,0f , 8, this);
			}
		}
		double rand = 2 + Math.random()*2;
		this.drawEfects = new DrawStateEfects("StateImages/Poisoned.png", 64, 64,(float)rand , 8, this);
	}
	
	@Override
	public void affectMonsters() {
		damage = friend.getMaxHealth()/16;	
	}
	
	@Override
	public void act(float delta){
		if(this.noEfect){
			return;
		}
		super.act(delta);
		localTime+=delta;
		if(this.localTime>1){
			this.localTime--;
			this.friend.setHealth(friend.getHealth()-damage);
			
		}
		
	}

}
